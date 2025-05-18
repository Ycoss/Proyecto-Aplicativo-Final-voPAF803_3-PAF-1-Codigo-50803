package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;             // Added for explicit Font type
import java.awt.FontMetrics;       // Added for FontMetrics
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
    private State previousState; // To store the state to return to

    // Default constructor for direct access (e.g., from main menu if ever needed)
    public ControlsState() {
        this(new MenuState()); // Default to returning to MenuState
    }

    // Constructor to be used from PauseMenuState or other states
    public ControlsState(State previousState) {
        this.previousState = previousState;
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
                        if (ControlsState.this.previousState != null) {
                            State.changeState(ControlsState.this.previousState);
                        } else {
                            // Fallback if previousState is somehow null, though the constructor should prevent this
                            State.changeState(new MenuState());
                        }
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

        // Define colors and font for convenience
        Color cyanColor = Color.CYAN;
        Color whiteColor = Color.WHITE;
        Font font = Assets.fontMed;
        FontMetrics fm = g.getFontMetrics(font);
        float currentX; // To track X position for segmented text

        // MOVER
        Text.drawText(g, "MOVE:",
                new Vector2D(xLabel, initialY),
                false, whiteColor, font);
        
        // Line: "W, A, S, D"
        currentX = xKey;
        Text.drawText(g, "W", new Vector2D(currentX, initialY), false, cyanColor, font);
        currentX += fm.stringWidth("W");
        Text.drawText(g, ", ", new Vector2D(currentX, initialY), false, whiteColor, font);
        currentX += fm.stringWidth(", ");
        Text.drawText(g, "A", new Vector2D(currentX, initialY), false, cyanColor, font);
        currentX += fm.stringWidth("A");
        Text.drawText(g, ", ", new Vector2D(currentX, initialY), false, whiteColor, font);
        currentX += fm.stringWidth(", ");
        Text.drawText(g, "D", new Vector2D(currentX, initialY), false, cyanColor, font);

        // Line: "or Arrow Keys (UP, DOWN, LEFT, RIGHT):"
        currentX = xKey;
        float yPosArrowLine = initialY + ySpacing;
        Text.drawText(g, "or ", new Vector2D(currentX, yPosArrowLine), false, whiteColor, font);
        currentX += fm.stringWidth("or ");
        Text.drawText(g, "Arrow", new Vector2D(currentX, yPosArrowLine), false, cyanColor, font);
        currentX += fm.stringWidth("Arrow");
        Text.drawText(g, " Keys (", new Vector2D(currentX, yPosArrowLine), false, whiteColor, font);
        currentX += fm.stringWidth(" Keys (");
        Text.drawText(g, "UP", new Vector2D(currentX, yPosArrowLine), false, cyanColor, font);
        currentX += fm.stringWidth("UP");
        Text.drawText(g, ", ", new Vector2D(currentX, yPosArrowLine), false, whiteColor, font);
        currentX += fm.stringWidth(", ");
        Text.drawText(g, "LEFT", new Vector2D(currentX, yPosArrowLine), false, cyanColor, font);
        currentX += fm.stringWidth("LEFT");
        Text.drawText(g, ", ", new Vector2D(currentX, yPosArrowLine), false, whiteColor, font);
        currentX += fm.stringWidth(", ");
        Text.drawText(g, "RIGHT", new Vector2D(currentX, yPosArrowLine), false, cyanColor, font);
        currentX += fm.stringWidth("RIGHT");
        Text.drawText(g, "):", new Vector2D(currentX, yPosArrowLine), false, whiteColor, font);
        
        // Line: Arrow symbols (UP, LEFT, RIGHT only)
        // Using char variables for Unicode characters to ensure correct interpretation.
        char upArrowChar = '\u2191';
        char leftArrowChar = '\u2190';
        char rightArrowChar = '\u2192';
        String arrowString = "     " + upArrowChar + "         " + leftArrowChar + "         " + rightArrowChar;

        Text.drawText(g, arrowString,
                new Vector2D(xKey, initialY + ySpacing * 2 - 5),
                false, cyanColor, font);
        
        // DISPARAR
        Text.drawText(g, "SHOOT:",
                new Vector2D(xLabel, initialY + ySpacing * 3),
                false, whiteColor, font);
        
        currentX = xKey;
        float yPosShootLine = initialY + ySpacing * 3;
        Text.drawText(g, "P", new Vector2D(currentX, yPosShootLine), false, cyanColor, font);
        currentX += fm.stringWidth("P");
        Text.drawText(g, " key or ", new Vector2D(currentX, yPosShootLine), false, whiteColor, font);
        currentX += fm.stringWidth(" key or ");
        Text.drawText(g, "Left Mouse Click", new Vector2D(currentX, yPosShootLine), false, cyanColor, font);
        
        // MENÚ DE PAUSA
        Text.drawText(g, "PAUSE MENU:",
                new Vector2D(xLabel, initialY + ySpacing * 4),
                false, whiteColor, font);
        Text.drawText(g, "SPACEBAR",
                new Vector2D(xKey, initialY + ySpacing * 4),
                false, cyanColor, font); // SPACEBAR in CYAN

        for (Button b : buttons) {
            b.draw(g);
        }
    }
} 