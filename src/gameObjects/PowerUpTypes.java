package gameObjects;

import java.awt.image.BufferedImage;

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

public enum PowerUpTypes {
	SHIELD("SHIELD", Assets.shield),
	LIFE("+1 LIFE", Assets.life),
	SCORE_X2("SCORE x2", Assets.doubleScore),
	FASTER_FIRE("FAST FIRE", Assets.fastFire),
	SCORE_STACK("+1000 SCORE", Assets.star),
	DOUBLE_GUN("DOUBLE GUN", Assets.doubleGun);
	
	public String text;
	public BufferedImage texture;
	
	private PowerUpTypes(String text, BufferedImage texture){
		this.text = text;
		this.texture = texture;
	}
}
