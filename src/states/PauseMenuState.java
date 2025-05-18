package states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gameObjects.Constants;
import graphics.Assets;
import graphics.Text;
import math.Vector2D;
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

public class PauseMenuState extends State {

    private ArrayList<Button> buttons;
    private GameState previousGameState; // Para reanudar el juego

    public PauseMenuState(GameState gameState) {
        this.previousGameState = gameState;
        buttons = new ArrayList<Button>();

        int buttonSpacing = (int)(Assets.greyBtn.getHeight() * 1.5);
        int centerX = Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2;
        int centerY = Constants.HEIGHT / 2;

        // Botón REANUDAR
        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                centerX,
                centerY - buttonSpacing,
                "RESUME",
                new Action() {
                    @Override
                    public void doAction() {
                        if (previousGameState != null) {
                            previousGameState.setPaused(false); // Desactivar bandera interna de pausa si existe
                            State.changeState(previousGameState);
                        }
                    }
                }
        ));

        // Botón CONTROLES
        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                centerX,
                centerY, // Botón centrado
                "CONTROLS",
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ControlsState()); // El retorno de ControlsState irá a MenuState
                    }
                }
        ));

        // Botón VOLVER AL LOBBY
        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                centerX,
                centerY + buttonSpacing,
                "RETURN LOBBY",
                new Action() {
                    @Override
                    public void doAction() {
                        // Asegurar que la música de fondo de GameState se detenga si estaba sonando
                        previousGameState.stopMusic(); 
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
        // Opcional: Atenuar ligeramente la pantalla del juego anterior si es posible, o simplemente dibujar sobre ella
        // Por ahora, previousGameState.draw(g) podría llamarse aquí para un efecto de transparencia
        // previousGameState.draw(g); // Dibuja el juego pausado detrás del menú
        // g.setColor(new Color(0, 0, 0, 150)); // Superposición semitransparente
        // g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        Text.drawText(g, "GAME PAUSED",
                new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2 - 150),
                true, Color.WHITE, Assets.fontBig);

        for (Button b : buttons) {
            b.draw(g);
        }
    }
} 