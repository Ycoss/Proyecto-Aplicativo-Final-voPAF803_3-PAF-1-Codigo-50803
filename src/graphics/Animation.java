package graphics;

import java.awt.image.BufferedImage;

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

public class Animation {
	
	private BufferedImage[] frames;
	private int velocity;
	private int index;
	private boolean running;
	private Vector2D position;
	private long time;
	
	public Animation(BufferedImage[] frames, int velocity, Vector2D position){
		this.frames = frames;
		this.velocity = velocity;
		this.position = position;
		index = 0;
		running = true;
	}
	
	public void update(float dt){
		
		time += (long)dt;
		
		if(time > velocity){
			time  = 0;
			index ++;
			if(index >= frames.length){
				running = false;
				index = 0;
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

	public Vector2D getPosition() {
		return position;
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	
	
	
	
	
}
