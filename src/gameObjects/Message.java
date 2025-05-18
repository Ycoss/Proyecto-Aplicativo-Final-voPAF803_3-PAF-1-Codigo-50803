package gameObjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import graphics.Text;
import math.Vector2D;

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

public class Message {
	private float alpha;
	private String text;
	private Vector2D position;
	private Color color;
	private boolean center;
	private boolean fade;
	private Font font;
	private final float deltaAlpha = 0.01f;
	private boolean dead;
	
	public Message(Vector2D position, boolean fade, String text, Color color,
			boolean center, Font font) {
		this.font = font;
		this.text = text;
		this.position = new Vector2D(position);
		this.fade = fade;
		this.color = color;
		this.center = center;
		this.dead = false;
		
		if(fade)
			alpha = 1;
		else
			alpha = 0;
		
	}
	
	public void draw(Graphics2D g2d) {
		
		float currentAlpha = Math.max(0, Math.min(1, alpha));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, currentAlpha));
		
		Text.drawText(g2d, text, position, center, color, font);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		position.setY(position.getY() - 1);
		
		if (fade) {
			alpha -= deltaAlpha;
			if (alpha < 0) {
				alpha = 0;
				dead = true;
			}
		} else {
			alpha += deltaAlpha;
			if (alpha > 1) {
				alpha = 1;
				fade = true;
			}
		}
	
	}
	
	public boolean isDead() {return dead;}

	
}
