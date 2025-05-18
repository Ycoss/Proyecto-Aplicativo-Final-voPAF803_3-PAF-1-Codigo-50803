package states;

import java.awt.Graphics;
import java.util.ArrayList;

import gameObjects.Constants;
import gameObjects.Constants.DifficultyLevel;
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

public class DifficultyState extends State {

    private ArrayList<Button> buttons;

    public DifficultyState() {
        buttons = new ArrayList<Button>();

        // Botón MODO FÁCIL
        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyBtn.getHeight() * 3, // Posicionado encima de Medio
                "EASY MODE",
                new Action() {
                    @Override
                    public void doAction() {
                        // Establecer dificultad a Fácil
                        Constants.setDifficulty(DifficultyLevel.EASY);
                        State.changeState(new GameState());
                    }
                }
        ));

        // Botón MODO MEDIO
        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyBtn.getHeight(), // Posicionado en el medio
                "MEDIUM MODE",
                new Action() {
                    @Override
                    public void doAction() {
                        // Establecer dificultad a Medio
                        Constants.setDifficulty(DifficultyLevel.MEDIUM);
                        State.changeState(new GameState());
                    }
                }
        ));

        // Botón MODO HARDCORE
        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 + Assets.greyBtn.getHeight(), // Posicionado debajo de Medio
                "HARDCORE MODE",
                new Action() {
                    @Override
                    public void doAction() {
                        // Establecer dificultad a Hardcore
                        Constants.setDifficulty(DifficultyLevel.HARDCORE);
                        State.changeState(new GameState());
                    }
                }
        ));

        // Botón VOLVER
        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                Assets.greyBtn.getHeight(), // X: Align to left with margin
                Constants.HEIGHT - Assets.greyBtn.getHeight() * 2, // Y: Align to bottom with margin
                Constants.RETURN, // Reusar constante RETURN existente
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                }
        ));
    }

    @Override
    public void update(float dt) {
        for (Button b : buttons) {
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Button b : buttons) {
            b.draw(g);
        }
    }
} 