package graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

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

public class Sound {
	
	private Clip clip;
	private FloatControl volume;
	
	public Sound(Clip clip) {
		this.clip = clip;
		volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop() {
		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public int getFramePosition() {
		return clip.getFramePosition();
	}
	
	public void changeVolume(float value) {
		volume.setValue(value);
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
	
}
