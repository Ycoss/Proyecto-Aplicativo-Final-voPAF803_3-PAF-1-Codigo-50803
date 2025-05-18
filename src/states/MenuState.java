package states;

import java.awt.Graphics;
import java.util.ArrayList;

import gameObjects.Constants;
import graphics.Assets;
import ui.Action;
import ui.Button;

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

public class MenuState extends State{
	
	private ArrayList<Button> buttons;
	
	public MenuState() {
		buttons = new ArrayList<Button>();
		
		int buttonSpacing = (int)(Assets.greyBtn.getHeight() * 1.5);
        int centerX = Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2;
        int centerY = Constants.HEIGHT / 2;

		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				centerX,
				centerY - buttonSpacing - Assets.greyBtn.getHeight()/2, // PLAY
				Constants.PLAY,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new DifficultyState());
					}
				}
				));

        buttons.add(new Button( // CONTROLS button
                Assets.greyBtn,
                Assets.blueBtn,
                centerX,
                centerY - Assets.greyBtn.getHeight()/2, // CONTROLS
                "CONTROLS",
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ControlsState());
                    }
                }
        ));

		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				centerX,
				centerY + buttonSpacing - Assets.greyBtn.getHeight()/2, // HIGH_SCORES
				Constants.HIGH_SCORES,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new ScoreState());
					}
				}
				));

		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				centerX,
				centerY + buttonSpacing * 2 - Assets.greyBtn.getHeight()/2, // EXIT
				Constants.EXIT,
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				));
	}
	
	
	@Override
	public void update(float dt) {
		for(Button b: buttons) {
			b.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		for(Button b: buttons) {
			b.draw(g);
		}
	}

}
