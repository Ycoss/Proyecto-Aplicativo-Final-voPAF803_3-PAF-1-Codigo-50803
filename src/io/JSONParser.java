package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import gameObjects.Constants;

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

public class JSONParser {
	
	public static ArrayList<ScoreData> readFile() throws FileNotFoundException{
		ArrayList<ScoreData> dataList = new ArrayList<ScoreData>();
		
		File file = new File(Constants.SCORE_PATH);
		
		if(!file.exists() || file.length() == 0) {
			return dataList;
		}
		
		JSONTokener parser = new JSONTokener(new FileInputStream(file));
		JSONArray jsonList = new JSONArray(parser);
		
		for(int i = 0; i < jsonList.length(); i++) {
			JSONObject obj = (JSONObject)jsonList.get(i);
			ScoreData data = new ScoreData();
			data.setScore(obj.getInt("score"));
			data.setDate(obj.getString("date"));
			data.setDifficultyMode(obj.optString("difficultyMode", Constants.DifficultyLevel.EASY.toString()));
			dataList.add(data);
		}
		
		return dataList;
	}
	
	public static void writeFile(ArrayList<ScoreData> dataList) throws IOException {
		
		File outputFile = new File(Constants.SCORE_PATH);
		
		outputFile.getParentFile().mkdirs();
		outputFile.createNewFile();
		
		JSONArray jsonList = new JSONArray();
		
		for(ScoreData data: dataList) {
			
			JSONObject obj = new JSONObject();
			obj.put("score", data.getScore());
			obj.put("date", data.getDate());
			obj.put("difficultyMode", data.getDifficultyMode());
			
			jsonList.put(obj);
			
		}
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile.toURI()));
		jsonList.write(writer);
		writer.close();
		
	}
	
}
