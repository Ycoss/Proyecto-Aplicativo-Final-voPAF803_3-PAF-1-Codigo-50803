package states;

import java.awt.Graphics;

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
public abstract class State {
	
	private static State currentState = null;
	
	public static State getCurrentState() {return currentState;}
	public static void changeState(State newState) {
		currentState = newState;
	}
	
	
	public abstract void update(float dt);
	public abstract void draw(Graphics g);
	
}
