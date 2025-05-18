package io;

import java.text.SimpleDateFormat;
import java.util.Date;

import gameObjects.Constants; // Import for DifficultyLevel enum

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

public class ScoreData {
	
	private String date;
	private int score;
	private String difficultyMode; // New field for difficulty
	
	// Constructor to be used when a new score is achieved
	public ScoreData(int score, Constants.DifficultyLevel difficulty) {
		this.score = score;
		this.difficultyMode = difficulty.toString(); // Store enum as string
		
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.format(today);
	}
	
	// Default constructor (likely used by JSON parser)
	public ScoreData() {
		// Initialize fields to default or null if necessary for JSON parsing
        this.score = 0;
        this.date = "";
        this.difficultyMode = Constants.DifficultyLevel.EASY.toString(); // Default for old scores or if missing
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDifficultyMode() {
		return difficultyMode;
	}

	public void setDifficultyMode(String difficultyMode) {
		this.difficultyMode = difficultyMode;
	}
}
