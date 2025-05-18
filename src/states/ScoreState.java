package states;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import gameObjects.Constants;
import graphics.Assets;
import graphics.Text;
import io.JSONParser;
import io.ScoreData;
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

public class ScoreState extends State{
	
	private Button returnButton;
	
	private PriorityQueue<ScoreData> highScores;
	
	private Comparator<ScoreData> scoreComparator;
	
	private ScoreData[] auxArray;
	
	public ScoreState() {
		returnButton = new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Assets.greyBtn.getHeight(),
				Constants.HEIGHT - Assets.greyBtn.getHeight() * 2,
				Constants.RETURN,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new MenuState());
					}
				}
			);
		
		scoreComparator = new Comparator<ScoreData>() {
			@Override
			public int compare(ScoreData e1, ScoreData e2) {
				return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
			}
		};
		
		highScores = new PriorityQueue<ScoreData>(10, scoreComparator);
		
		try {
			ArrayList<ScoreData> dataList = JSONParser.readFile();
			
			for(ScoreData d: dataList) {
				highScores.add(d);
			}
			
			while(highScores.size() > 10) {
				highScores.poll();
			}
			
		} catch (FileNotFoundException e) {
			// TODO: Bloque catch autogenerado
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(float dt) {
		returnButton.update();
	}

	@Override
	public void draw(Graphics g) {
		returnButton.draw(g);
		
		auxArray = highScores.toArray(new ScoreData[highScores.size()]);
		
		Arrays.sort(auxArray, scoreComparator);
		
		// Adjusted X positions for three columns
		Vector2D scoreHeaderPos = new Vector2D(Constants.WIDTH / 2 - 250, 100);
		Vector2D modeHeaderPos = new Vector2D(Constants.WIDTH / 2, 100); // Centered
		Vector2D dateHeaderPos = new Vector2D(Constants.WIDTH / 2 + 250, 100);
		
		Text.drawText(g, Constants.SCORE, scoreHeaderPos, true, Color.BLUE, Assets.fontBig);
		Text.drawText(g, "MODE", modeHeaderPos, true, Color.BLUE, Assets.fontBig); // New MODE header
		Text.drawText(g, Constants.DATE, dateHeaderPos, true, Color.BLUE, Assets.fontBig);
		
		// Data positions, using copy constructor
		Vector2D scoreDataPos = new Vector2D(scoreHeaderPos);
		Vector2D modeDataPos = new Vector2D(modeHeaderPos);
		Vector2D dateDataPos = new Vector2D(dateHeaderPos);

		scoreDataPos.setY(scoreDataPos.getY() + 40);
		modeDataPos.setY(modeDataPos.getY() + 40);
		dateDataPos.setY(dateDataPos.getY() + 40);
		
		for(int i = auxArray.length - 1; i > -1; i--) {
			ScoreData d = auxArray[i];
			
			Text.drawText(g, Integer.toString(d.getScore()), scoreDataPos, true, Color.WHITE, Assets.fontMed);
			// Get and draw difficulty mode
			String mode = d.getDifficultyMode() != null ? d.getDifficultyMode() : "N/A"; // Handle null just in case
			Text.drawText(g, mode.toUpperCase(), modeDataPos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, d.getDate(), dateDataPos, true, Color.WHITE, Assets.fontMed);
			
			scoreDataPos.setY(scoreDataPos.getY() + 40);
			modeDataPos.setY(modeDataPos.getY() + 40);
			dateDataPos.setY(dateDataPos.getY() + 40);
		}
	}
	
}
