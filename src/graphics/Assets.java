package graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

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

public class Assets {
	
	public static boolean loaded = false;
	public static float count = 0;
	public static float MAX_COUNT = 60;
	
	
	public static BufferedImage player;
	public static BufferedImage playerShipGreen;
	public static BufferedImage playerShipRed;
	public static BufferedImage doubleGunPlayer;
	
	// Texturas actuales a ser usadas por el juego, seleccionadas según la dificultad
	public static BufferedImage currentPlayerShipTexture;
	public static BufferedImage currentDoubleGunPlayerTexture;
	public static BufferedImage currentPlayerLaserTexture;
	
	// efectos
	
	public static BufferedImage speed;
	
	public static BufferedImage[] shieldEffect = new BufferedImage[3];
	
	// explosión
	
	public static BufferedImage[] exp = new BufferedImage[9];
	
	// láseres
	
	public static BufferedImage blueLaser, greenLaser, redLaser;
	
	// Meteoritos
	
	public static BufferedImage[] bigs = new BufferedImage[4];
	public static BufferedImage[] meds = new BufferedImage[2];
	public static BufferedImage[] smalls = new BufferedImage[2];
	public static BufferedImage[] tinies = new BufferedImage[2];
	
	// ovni
	
	public static BufferedImage ufo;
	
	// números
	
	public static BufferedImage[] numbers = new BufferedImage[11];
	
	public static BufferedImage life;
	
	// fuentes
	
	public static Font fontBig;
	public static Font fontMed;
	
	public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot, powerUp;
	
	// ui
	
	public static BufferedImage blueBtn;
	public static BufferedImage greyBtn;
	
	// power-ups
	
	public static BufferedImage orb, doubleScore, doubleGun, fastFire, shield, star;
	
	public static void init()
	{
		player = loadImage("/ships/player.png");
		playerShipGreen = loadImage("/ships/playerShip3_green.png");
		playerShipRed = loadImage("/ships/playerShip2_red.png");
		doubleGunPlayer = loadImage("/ships/doubleGunPlayer.png");
		
		speed = loadImage("/effects/fire08.png");
		
		blueLaser = loadImage("/lasers/laserBlue01.png");
		greenLaser = loadImage("/lasers/laserGreen11.png");
		redLaser = loadImage("/lasers/laserRed16.png");
		
		ufo = loadImage("/ships/ufo.png");
		
		life = loadImage("/others/life.png");
		
		fontBig = loadFont("/fonts/futureFont.ttf", 42);
		
		fontMed = loadFont("/fonts/futureFont.ttf", 20);
		
		for(int i = 0; i < 3; i++)
			shieldEffect[i] = loadImage("/effects/shield" + (i + 1) +".png"); 
		
		for(int i = 0; i < bigs.length; i++)
			bigs[i] = loadImage("/meteors/big"+(i+1)+".png");
		
		for(int i = 0; i < meds.length; i++)
			meds[i] = loadImage("/meteors/med"+(i+1)+".png");
		
		for(int i = 0; i < smalls.length; i++)
			smalls[i] = loadImage("/meteors/small"+(i+1)+".png");
		
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = loadImage("/meteors/tiny"+(i+1)+".png");
		
		for(int i = 0; i < exp.length; i++)
			exp[i] = loadImage("/explosion/"+i+".png");
		
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = loadImage("/numbers/"+i+".png");
		
		backgroundMusic = loadSound("/sounds/backgroundMusic.wav");
		explosion = loadSound("/sounds/explosion.wav");
		playerLoose = loadSound("/sounds/playerLoose.wav");
		playerShoot = loadSound("/sounds/playerShoot.wav");
		ufoShoot = loadSound("/sounds/ufoShoot.wav");
		powerUp = loadSound("/sounds/powerUp.wav");
		
		greyBtn = loadImage("/ui/grey_button.png");
		blueBtn = loadImage("/ui/blue_button.png");
		
		orb = loadImage("/powers/orb.png");
		doubleScore = loadImage("/powers/doubleScore.png");
		doubleGun = loadImage("/powers/doubleGun.png");
		fastFire = loadImage("/powers/fastFire.png");
		star = loadImage("/powers/star.png");
		shield = loadImage("/powers/shield.png");
		
		// Inicializar texturas actuales a las por defecto (modo Fácil)
		currentPlayerShipTexture = player;
		currentDoubleGunPlayerTexture = doubleGunPlayer;
		currentPlayerLaserTexture = blueLaser;
		
		// ===========================================================
		
		loaded = true;
		
	}

	public static BufferedImage loadImage(String path) {
		count ++;
		return Loader.ImageLoader(path);
	}
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.loadFont(path, size);
	}
	public static Clip loadSound(String path) {
		count ++;
		return Loader.loadSound(path);
	}
	
}
