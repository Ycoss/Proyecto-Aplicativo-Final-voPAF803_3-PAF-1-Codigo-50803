package gameObjects;

import javax.swing.filechooser.FileSystemView;
import graphics.Assets;

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

public class Constants {
	
	// Dimensiones del frame
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	// Propiedades del jugador
	
	public static final int FIRERATE = 400;
	public static final double DELTAANGLE = 0.1;
	public static final double ACC = 0.2;
	public static final double PLAYER_MAX_VEL = 7.0;
	public static final long FLICKER_TIME = 200;
	public static final long SPAWNING_TIME = 3000;
	public static final long GAME_OVER_TIME = 3000;
	
	// Propiedades del láser
	
	public static final double LASER_VEL = 15.0;
	
	// Propiedades de los meteoritos - VALORES BASE
	
	public static final double BASE_METEOR_INIT_VEL = 1.0;
	public static final double BASE_METEOR_MAX_VEL = 3.0;
	public static final int METEOR_SCORE = 20;
	public static final int SHIELD_DISTANCE = 150;
	
	// Propiedades del OVNI - VALORES BASE
	
	public static final int NODE_RADIUS = 160;
	public static final double UFO_MASS = 60;
	public static final int BASE_UFO_MAX_VEL = 18;
	public static final long BASE_UFO_FIRE_RATE = 9000L;
	public static final double UFO_ANGLE_RANGE = Math.PI / 2;
	public static final int UFO_SCORE = 40;
	public static final long UFO_SPAWN_RATE = 10000;
	
	// Valores ajustados por dificultad (que serán establecidos en setDifficulty)
	
	public static double METEOR_INIT_VEL;
	public static double METEOR_MAX_VEL;
	public static int UFO_MAX_VEL;
	public static long UFO_FIRE_RATE;
	public static double UFO_LASER_SPEED;

	// Enum de dificultad y configuración actual
	
	public enum DifficultyLevel {
		EASY,
		MEDIUM,
		HARDCORE
	}
	public static DifficultyLevel currentDifficulty = DifficultyLevel.EASY;
	
	// Método para establecer la dificultad y actualizar las constantes dinámicas
	
	public static void setDifficulty(DifficultyLevel difficulty) {
		currentDifficulty = difficulty;

		METEOR_INIT_VEL = BASE_METEOR_INIT_VEL;
		METEOR_MAX_VEL = BASE_METEOR_MAX_VEL;

		switch (difficulty) {
			case EASY:
				UFO_MAX_VEL = 3;
				UFO_FIRE_RATE = 5000L;
				UFO_LASER_SPEED = 21.0;

				Assets.currentPlayerShipTexture = Assets.player;
				Assets.currentDoubleGunPlayerTexture = Assets.doubleGunPlayer;
				Assets.currentPlayerLaserTexture = Assets.blueLaser;
				break;
			case MEDIUM:
				UFO_MAX_VEL = 3;
				UFO_FIRE_RATE = 1000L;
				UFO_LASER_SPEED = 15.0;

				METEOR_INIT_VEL = BASE_METEOR_INIT_VEL * 1.5;
				METEOR_MAX_VEL = BASE_METEOR_MAX_VEL * 1.5;

				Assets.currentPlayerShipTexture = Assets.playerShipGreen;
				Assets.currentDoubleGunPlayerTexture = Assets.playerShipGreen;
				Assets.currentPlayerLaserTexture = Assets.greenLaser;
				break;
			case HARDCORE:
				UFO_MAX_VEL = 10;
				UFO_FIRE_RATE = 750L;
				UFO_LASER_SPEED = PLAYER_MAX_VEL * 1.5;

				Assets.currentPlayerShipTexture = Assets.playerShipRed;
				Assets.currentDoubleGunPlayerTexture = Assets.playerShipRed;
				Assets.currentPlayerLaserTexture = Assets.redLaser;
				break;
		}
	}
	
	// Cadenas de texto y rutas para la interfaz de usuario
	
	public static final String PLAY = "PLAY";
	public static final String EXIT = "EXIT";
	public static final int LOADING_BAR_WIDTH = 500;
	public static final int LOADING_BAR_HEIGHT = 50;
	public static final String RETURN = "RETURN";
	public static final String HIGH_SCORES = "HIGHEST SCORES";
	public static final String SCORE = "SCORE";
	public static final String DATE = "DATE";
	public static final String SCORE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
			"\\Space_Ship_Game\\data.json"; // data.xml si se usara XMLParser
	
	// Estas variables eran requeridas si se usaba XMLParser (actualmente no se usa)
	
	// public static final String PLAYER = "PLAYER";
	// public static final String PLAYERS = "PLAYERS";
	
	// =============================================
	
	public static final long POWER_UP_DURATION = 30000L;
	public static final long POWER_UP_SPAWN_TIME = 8000L;
	
	public static final long SHIELD_TIME = 36000L;
	public static final long DOUBLE_SCORE_TIME = 30000L;
	public static final long FAST_FIRE_TIME = 42000L;
	public static final long DOUBLE_GUN_TIME = 36000L;
	
	public static final int SCORE_STACK = 1000;
	
}
