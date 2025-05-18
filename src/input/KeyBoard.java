package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import input.MouseInput;

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

public class KeyBoard implements KeyListener{
	
	private boolean[] keys = new boolean[256];
	
	public static boolean UP, LEFT, RIGHT, SHOOT, PAUSE;
	
	public KeyBoard()
	{
		UP = false;
		LEFT = false;
		RIGHT = false;
		SHOOT = false;
		PAUSE = false;
	}
	
	public void update()
	{
		// Guardamos el estado anterior para saber qué cambió
		boolean wasUp = UP;
		boolean wasLeft = LEFT;
		boolean wasRight = RIGHT;
		boolean wasShoot = SHOOT;
		boolean wasPause = PAUSE;
		
		// Actualizar teclas
		boolean upArrow = keys[KeyEvent.VK_UP];
		boolean leftArrow = keys[KeyEvent.VK_LEFT];
		boolean rightArrow = keys[KeyEvent.VK_RIGHT];
		boolean wKey = keys[KeyEvent.VK_W];
		boolean aKey = keys[KeyEvent.VK_A];
		boolean dKey = keys[KeyEvent.VK_D];
		boolean pKey = keys[KeyEvent.VK_P];
		boolean spaceKey = keys[KeyEvent.VK_SPACE];
		
		UP = upArrow || wKey;
		LEFT = leftArrow || aKey;
		RIGHT = rightArrow || dKey;
		SHOOT = pKey;
		PAUSE = spaceKey;
		
		// Mensajes para movimiento
		if (UP && !wasUp) {
			// if (upArrow)
			//	System.out.println("Moviendo hacia ARRIBA con FLECHA ARRIBA");
			// if (wKey)
			//	System.out.println("Moviendo hacia ARRIBA con tecla W");
		}
		
		if (LEFT && !wasLeft) {
			// if (leftArrow)
			//	System.out.println("Girando a la IZQUIERDA con FLECHA IZQUIERDA");
			// if (aKey)
			//	System.out.println("Girando a la IZQUIERDA con tecla A");
		}
		
		if (RIGHT && !wasRight) {
			// if (rightArrow)
			//	System.out.println("Girando a la DERECHA con FLECHA DERECHA");
			// if (dKey)
			//	System.out.println("Girando a la DERECHA con tecla D");
		}
		
		// Mensaje para disparo
		if (SHOOT && !wasShoot) {
			// if (pKey)
			//	System.out.println("Disparando con tecla P");
			// if (MouseInput.MLB)
			//	System.out.println("Disparando con CLIC IZQUIERDO");
		}

		// Mensaje para Pausa (opcional, comentado)
		// if (PAUSE && !wasPause) {
		//     System.out.println("Juego PAUSADO/REANUDADO con BARRA ESPACIADORA");
		// }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < keys.length) {
			keys[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < keys.length) {
			keys[keyCode] = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
