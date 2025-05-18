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

public class ControlsState extends State {

    private ArrayList<Button> buttons;

    public ControlsState() {
        buttons = new ArrayList<Button>();

        buttons.add(new Button(
                Assets.greyBtn,
                Assets.blueBtn,
                Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 + 200, // Posición Y ajustada para más espacio para el texto
                Constants.RETURN,
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        Text.drawText(g, "CONTROLS",
                new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2 - 220), // Título un poco más arriba
                true, Color.WHITE, Assets.fontBig);

        int initialY = Constants.HEIGHT / 2 - 140; // Ajustado para más contenido
        int ySpacing = 35; // Espaciado ligeramente reducido
        int xLabel = Constants.WIDTH / 2 - 250; // Posición X para etiquetas como MOVE:
        int xKey = Constants.WIDTH / 2 - 50;  // Posición X para descripciones de teclas

        // MOVER
        Text.drawText(g, "MOVE:",
                new Vector2D(xLabel, initialY),
                false, Color.WHITE, Assets.fontMed);
        Text.drawText(g, "W, A, S, D",
                new Vector2D(xKey, initialY),
                false, Color.CYAN, Assets.fontMed);
        Text.drawText(g, "or Arrow Keys (UP, DOWN, LEFT, RIGHT):", // o Teclas de Flecha (ARRIBA, ABAJO, IZQUIERDA, DERECHA):
                new Vector2D(xKey, initialY + ySpacing),
                false, Color.CYAN, Assets.fontMed);
        Text.drawText(g, "     \u2191         \u2193         \u2190         \u2192", // Flechas Unicode
                new Vector2D(xKey, initialY + ySpacing * 2 -5), // Ajustar ligeramente Y para las flechas
                false, Color.CYAN, Assets.fontMed);
        
        // DISPARAR
        Text.drawText(g, "SHOOT:",
                new Vector2D(xLabel, initialY + ySpacing * 3),
                false, Color.WHITE, Assets.fontMed);
        Text.drawText(g, "P key or Left Mouse Click",
                new Vector2D(xKey, initialY + ySpacing * 3),
                false, Color.CYAN, Assets.fontMed);
        
        // MENÚ DE PAUSA
        Text.drawText(g, "PAUSE MENU:",
                new Vector2D(xLabel, initialY + ySpacing * 4),
                false, Color.WHITE, Assets.fontMed);
        Text.drawText(g, "SPACEBAR",
                new Vector2D(xKey, initialY + ySpacing * 4),
                false, Color.CYAN, Assets.fontMed);

        for (Button b : buttons) {
            b.draw(g);
        }
    }
} 