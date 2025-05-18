package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import gameObjects.Constants;
import gameObjects.Message;
import gameObjects.Meteor;
import gameObjects.MovingObject;
import gameObjects.Player;
import gameObjects.PowerUp;
import gameObjects.PowerUpTypes;
import gameObjects.Size;
import gameObjects.Ufo;
import graphics.Animation;
import graphics.Assets;
import graphics.Sound;
import input.KeyBoard;
import io.JSONParser;
import io.ScoreData;
import math.Vector2D;
import ui.Action;

/**
 * Autores:
 * Yadi Alejandro Landa Cossio
 * Id: 545958
 *
 * Santiago Elin Mandujano Aguilar
 * Id: 564640
 *
 * Armando Díaz Castillo
 * ID: 00562897
 *
 * Jorge Carlos Zapata Villanueva
 * Id: 543478
 *
 * Materia: Lenguaje orientado a objetos
 * Fecha de entrega: 20 de mayo del 2025 a las 13:00
 */
public class GameState extends State{
	public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
			Constants.HEIGHT/2 - Assets.player.getHeight()/2);
	
	private Player player;
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
	private ArrayList<Animation> explosions = new ArrayList<Animation>();
	private ArrayList<Message> messages = new ArrayList<Message>();
	
	private int score = 0;
	private int lives = 3;
	
	private int meteors;
	private int waves = 1;
	
	private Sound backgroundMusic;
	private long gameOverTimer;
	private boolean gameOver;
	
	private long ufoSpawner;
	private long powerUpSpawner;

	private boolean isPaused = false; // Estado de pausa interno
    private boolean pauseKeyPressedLastFrame = false; // Para detectar una sola pulsación
	
	@SuppressWarnings("this-escape") // Suprimir advertencia para la inicialización de player
	public GameState()
	{
		// player = new Player(PLAYER_START_POSITION, new Vector2D(),
		// 		Constants.PLAYER_MAX_VEL, Assets.currentPlayerShipTexture, this);
		// Se mueve la inicializacion del jugador al final del constructor
		
		gameOver = false;
		// movingObjects.add(player); // Se movera junto con la inicializacion del jugador
		
		meteors = 1;
		
		backgroundMusic = new Sound(Assets.backgroundMusic);
		backgroundMusic.loop();
		backgroundMusic.changeVolume(-10.0f);
		
		gameOverTimer = 0;
		ufoSpawner = 0;
		powerUpSpawner = 0;
		
		// gameOver = false; // Ya se establecio arriba

		// Inicializacion del jugador y añadirlo a movingObjects se hace despues de otras inicializaciones de GameState.
		player = new Player(PLAYER_START_POSITION, new Vector2D(),
				Constants.PLAYER_MAX_VEL, Assets.currentPlayerShipTexture, this);
		movingObjects.add(player);

		startWave(); // startWave podria depender de que player exista si se modifica en el futuro
		
	}

    // Método para establecer el estado de pausa interno (ej. al reanudar desde PauseMenuState)
    public void setPaused(boolean paused) {
        this.isPaused = paused;
        // if (!paused) {
        //     // Opcional: Re-enfocar entrada si es necesario, o reinicializar temporizadores
        //     pauseKeyPressedLastFrame = true; // Prevenir re-pausa inmediata si se mantiene espacio presionado
        // }
    }

    // Método para detener la música de fondo al volver al lobby
    public void stopMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
	
	public void addScore(int value, Vector2D position) {
		
		Color c = Color.WHITE;
		String text = "+" + value + " score";
		if(player.isDoubleScoreOn()) {
			c = Color.YELLOW;
			value = value * 2;
			text = "+" + value + " score" + " (X2)";
		}
		
		score += value;
		messages.add(new Message(position, true, text, c, false, Assets.fontMed));
	}
	
	public void divideMeteor(Meteor meteor){
		
		Size size = meteor.getSize();
		
		BufferedImage[] textures = size.textures;
		
		Size newSize = null;
		
		switch(size){
		case BIG:
			newSize =  Size.MED;
			break;
		case MED:
			newSize = Size.SMALL;
			break;
		case SMALL:
			newSize = Size.TINY;
			break;
		default:
			return;
		}
		
		for(int i = 0; i < size.quantity; i++){
			movingObjects.add(new Meteor(
					meteor.getPosition(),
					new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
					Constants.METEOR_INIT_VEL*Math.random() + 1,
					textures[(int)(Math.random()*textures.length)],
					this,
					newSize
					));
		}
	}
	
	
	private void startWave(){
		
		messages.add(new Message(new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), false,
				"WAVE "+waves, Color.WHITE, true, Assets.fontBig));
		
		double x, y;
		
		for(int i = 0; i < meteors; i++){
			 
			x = i % 2 == 0 ? Math.random()*Constants.WIDTH : 0;
			y = i % 2 == 0 ? 0 : Math.random()*Constants.HEIGHT;
			
			BufferedImage texture = Assets.bigs[(int)(Math.random()*Assets.bigs.length)];
			
			movingObjects.add(new Meteor(
					new Vector2D(x, y),
					new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
					Constants.METEOR_INIT_VEL*Math.random() + 1,
					texture,
					this,
					Size.BIG
					));
			
		}
		meteors ++;
		// Asegurar que el estado de la tecla de pausa se reinicie cuando comienza una nueva oleada (si es necesario)
        pauseKeyPressedLastFrame = true; 
	}
	
	public void playExplosion(Vector2D position){
		explosions.add(new Animation(
				Assets.exp,
				50,
				position.subtract(new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))
				));
	}
	
	private void spawnUfo(){
		
		int rand = (int) (Math.random()*2);
		
		double x = rand == 0 ? (Math.random()*Constants.WIDTH): Constants.WIDTH;
		double y = rand == 0 ? Constants.HEIGHT : (Math.random()*Constants.HEIGHT);
		
		ArrayList<Vector2D> path = new ArrayList<Vector2D>();
		
		double posX, posY;
		
		posX = Math.random()*Constants.WIDTH/2;
		posY = Math.random()*Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));

		posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
		posY = Math.random()*Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));
		
		posX = Math.random()*Constants.WIDTH/2;
		posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));
		
		posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
		posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));
		
		movingObjects.add(new Ufo(
				new Vector2D(x, y),
				new Vector2D(),
				Constants.UFO_MAX_VEL,
				Assets.ufo,
				path,
				this
				));
		
	}

	private void spawnPowerUp() {
		
		final int x = (int) ((Constants.WIDTH - Assets.orb.getWidth()) * Math.random());
		final int y = (int) ((Constants.HEIGHT - Assets.orb.getHeight()) * Math.random());
		
		int index = (int) (Math.random() * (PowerUpTypes.values().length));
		
		PowerUpTypes p = PowerUpTypes.values()[index];
		
		final String text = p.text;
		Action action = null;
		Vector2D position = new Vector2D(x , y);
		
		switch(p) {
		case LIFE:
			action = new Action() {
				@Override
				public void doAction() {
					
					lives ++;
					messages.add(new Message(
							position,
							false,
							text,
							Color.GREEN,
							false,
							Assets.fontMed
							));
				}
			};
			break;
		case SHIELD:
			action = new Action() {
				@Override
				public void doAction() {
					player.setShield();
					messages.add(new Message(
							position,
							false,
							text,
							Color.DARK_GRAY,
							false,
							Assets.fontMed
							));
				}
			};
			break;
		case SCORE_X2:
			action = new Action() {
				@Override
				public void doAction() {
					player.setDoubleScore();
					messages.add(new Message(
							position,
							false,
							text,
							Color.YELLOW,
							false,
							Assets.fontMed
							));
				}
			};
			break;
		case FASTER_FIRE:
			action = new Action() {
				@Override
				public void doAction() {
					player.setFastFire();
					messages.add(new Message(
							position,
							false,
							text,
							Color.BLUE,
							false,
							Assets.fontMed
							));
				}
			};
			break;
		case SCORE_STACK:
			action = new Action() {
				@Override
				public void doAction() {
					score += 1000;
					messages.add(new Message(
							position,
							false,
							text,
							Color.MAGENTA,
							false,
							Assets.fontMed
							));
				}
			};
			break;
		case DOUBLE_GUN:
			action = new Action() {
				@Override
				public void doAction() {
					player.setDoubleGun();
					messages.add(new Message(
							position,
							false,
							text,
							Color.CYAN,
							false,
							Assets.fontMed
							));
				}
			};
			break;
		default:
			break;
		}
		this.movingObjects.add(new PowerUp(
				position,
				p.texture,
				action,
				this
				));
		
	}
	
	public void update(float dt)
	{
        // Handle Pause Input
        boolean pauseKeyDown = KeyBoard.PAUSE;
        if (pauseKeyDown && !pauseKeyPressedLastFrame) {
            isPaused = !isPaused; // Toggle pause state
            if (isPaused) {
                State.changeState(new PauseMenuState(this));
                // No further updates in GameState if paused via menu
                pauseKeyPressedLastFrame = true; // prevent immediate re-trigger from held key
                return; 
            }
        }
        pauseKeyPressedLastFrame = pauseKeyDown;

        // If game is paused by changing state to PauseMenuState, this update won't run further.
        // If we were using an internal isPaused flag to halt updates, it would be checked here.

		if(gameOver)
			gameOverTimer += (long)dt;
		
		powerUpSpawner += (long)dt;
		ufoSpawner += (long)dt;
		
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).update(dt);
		
		for(int i = 0; i < explosions.size(); i++){
			Animation anim = explosions.get(i);
			anim.update(dt);
			if(!anim.isRunning()){
				explosions.remove(i);
				i--;
			}
		}
		
		if(gameOver && gameOverTimer > Constants.GAME_OVER_TIME) {
			
			try {
				ArrayList<ScoreData> dataList = JSONParser.readFile();
				dataList.add(new ScoreData(score, Constants.currentDifficulty));
				dataList.sort((d1, d2) -> Integer.compare(d2.getScore(), d1.getScore()));
				JSONParser.writeFile(dataList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			backgroundMusic.stop();
			State.changeState(new MenuState());
			return;
		}
		
		if(powerUpSpawner > Constants.POWER_UP_SPAWN_TIME) {
			spawnPowerUp();
			powerUpSpawner = 0;
		}
		
		if(ufoSpawner > Constants.UFO_SPAWN_RATE && !gameOver) {
			spawnUfo();
			ufoSpawner = 0;
		}
		
		for(int i = 0; i < movingObjects.size(); i++)
			if(movingObjects.get(i).isDead())
				movingObjects.remove(i);
		
		boolean areMeteorsLeft = false;
		for(int i = 0; i < movingObjects.size(); i++) {
			if(movingObjects.get(i) instanceof Meteor) {
				areMeteorsLeft = true;
				break;
			}
		}
		
		if(!areMeteorsLeft && !gameOver) {
			waves++;
			startWave();
		}
		
	}
	
	public void draw(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for(int i = 0; i < messages.size(); i++)
			messages.get(i).draw(g2d);
		
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).draw(g);
		
		for(int i = 0; i < explosions.size(); i++){
			Animation anim = explosions.get(i);
			g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),
					null);
		}
		
		drawScore(g);
		drawLives(g);
		
	}
	
	private void drawScore(Graphics g) {
		
		Vector2D pos = new Vector2D(850, 25);
		
		String scoreToString = Integer.toString(score);
		
		for(int i = 0; i < scoreToString.length(); i++) {
			
			g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))], 
					(int)pos.getX() + i*20, (int)pos.getY(), null);
		}
	}
	
	private void drawLives(Graphics g){
		
		if(gameOver) return;
		
		Vector2D livePosition = new Vector2D(25, 25);
		
		g.drawImage(Assets.life, (int)livePosition.getX(), (int)livePosition.getY(), null);
		
		g.drawImage(Assets.numbers[10], (int)livePosition.getX() + 40, 
				(int)livePosition.getY() + 5, null);
		
		String livesToString = Integer.toString(lives);
		
		Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());
		
		for(int i = 0; i < livesToString.length(); i ++) {
			int number = Integer.parseInt(livesToString.substring(i, i+1));
			if(number <= 0) break;
			g.drawImage(Assets.numbers[number], 
					(int)pos.getX() + 60 + i*20, (int)pos.getY() + 5, null);
		}
		
	}
	
	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean subtractLife(Vector2D position) {
		lives --;
		
		Message lifeLost = new Message(
				position,
				false,
				"-1 LIFE",
				Color.RED,
				false,
				Assets.fontMed
				);
		messages.add(lifeLost);
		
		return lives > 0;
	}
	
	public void gameOver() {
		Message gameOverMsg = new Message(
				PLAYER_START_POSITION.add(new Vector2D(0, -100)),
				true,
				"GAME OVER",
				Color.WHITE,
				true,
				Assets.fontBig);
		
		this.messages.add(gameOverMsg);
		gameOver = true;
	}
	
}
