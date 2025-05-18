package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class MouseInput extends MouseAdapter{
	
	public static int X, Y;
	public static boolean MLB;
	
	public MouseInput() {
		MLB = false;
	}
	
	// Método update que se llama desde Window
	public void update() {
		// Contenido eliminado
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			MLB = true;
			// clicked = true;  // Eliminado
			// System.out.println("Mouse botón izquierdo presionado");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			MLB = false;
			// System.out.println("Mouse botón izquierdo liberado");
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		X = e.getX();
		Y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		X = e.getX();
		Y = e.getY();
	}
	
}
