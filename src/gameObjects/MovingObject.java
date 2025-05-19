package gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;

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

public abstract class MovingObject extends GameObject{
	
	protected Vector2D velocity;
	protected AffineTransform at;
	protected double angle;
	protected double maxVel;
	protected int width;
	protected int height;
	protected GameState gameState;
	
	private Sound explosion;
	
	protected boolean Dead;
	
	public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, texture);
		this.velocity = velocity;
		this.maxVel = maxVel;
		this.gameState = gameState;
		width = texture.getWidth();
		height = texture.getHeight();
		angle = 0;
		explosion = new Sound(Assets.explosion);
		Dead = false;
	}
	
	protected void collidesWith(){
		
		ArrayList<MovingObject> movingObjects = gameState.getMovingObjects(); 
		
		for(int i = 0; i < movingObjects.size(); i++){
			
			MovingObject m  = movingObjects.get(i);
			
			if(m.equals(this))
				continue;
			
			double distance = m.getCenter().subtract(getCenter()).getMagnitude();
			
			if(distance < m.width/2 + width/2 && movingObjects.contains(this) && !m.Dead && !Dead){
				objectCollision(this, m);
			}
		}
	}
	
	private void objectCollision(MovingObject a, MovingObject b) {
		
		Player p = null;
		Laser l = null;

		boolean aIsPlayer = a instanceof Player;
		boolean bIsPlayer = b instanceof Player;
		boolean aIsLaser = a instanceof Laser;
		boolean bIsLaser = b instanceof Laser;

		if(aIsPlayer)
			p = (Player)a;
		else if(bIsPlayer)
			p = (Player)b;

		if(aIsLaser)
		    l = (Laser)a;
		else if(bIsLaser)
		    l = (Laser)b;
		
		if(p != null && p.isSpawning()) {
			return;
		}
		
		if(a instanceof Meteor && b instanceof Meteor) {
			return;
		}

		if(p != null && l != null){ // Si un jugador y un láser están involucrados
			if(!l.isEnemy()) { // Si el láser NO es enemigo (es del jugador)
				return; // Ignorar colisión del jugador con su propio láser
			} else { // Si el láser ES enemigo
				p.Destroy();
				l.Destroy();
				return;
			}
		}
		
		if (aIsLaser && bIsLaser) {
			return;
		}
		
		if(p != null){ // p es el jugador involucrado
			if(aIsPlayer && b instanceof PowerUp){
				((PowerUp)b).executeAction();
				b.Destroy();
				return;
			} else if(bIsPlayer && a instanceof PowerUp){
				((PowerUp)a).executeAction();
				a.Destroy();
				return;
			}
		}
		
		if (a instanceof PowerUp || b instanceof PowerUp) {
            return; // Evita que los PowerUps se destruyan con objetos que no sean el Jugador
        }

		a.Destroy(); 
		b.Destroy(); 
		
	}
	
	protected void Destroy(){
		Dead = true;
		if(!(this instanceof Laser) && !(this instanceof PowerUp))
			explosion.play();
	}
	
	protected Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + height/2);
	}
	
	public boolean isDead() {return Dead;}
	
}

