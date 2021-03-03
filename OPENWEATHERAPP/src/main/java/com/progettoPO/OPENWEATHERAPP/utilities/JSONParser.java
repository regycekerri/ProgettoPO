package com.progettoPO.OPENWEATHERAPP.utilities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.progettoPO.OPENWEATHERAPP.model.CityWithData;

/**
 * <p>
 * <b>Classe</b> contenente dei metodi utili a parsare dei JSON in strutture dati specifiche
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
public class JSONParser {
	
	/**
	 * Metodo che prende come parametri in ingresso un JSONArray (jsonArray) contenente dei dati ottenuti dalla chiamata all'API
	 * e un'ArrayList (array); l'ArrayList viene popolata di città con i rispettivi valori di umidità
	 * attuali, attraverso un dettagliato parsing del JSONArray
	 * 
	 * @param jsonArray
	 * @param array
	 */
	public static void setHumidityFromJSONArray(JSONArray jsonArray, ArrayList<CityWithData> array) {
		String name;
		double lat;
		double lon;
		int city_id;
		int humidity;
		
		for(int i=0; i<jsonArray.length(); i++) {
			name = jsonArray.getJSONObject(i).getString("name");
			lat = jsonArray.getJSONObject(i).getJSONObject("coord").getDouble("lat");
			lon = jsonArray.getJSONObject(i).getJSONObject("coord").getDouble("lon");
			city_id = jsonArray.getJSONObject(i).getInt("id");
			humidity = jsonArray.getJSONObject(i).getJSONObject("main").getInt("humidity");
			
			array.add(new CityWithData(name, lat, lon, city_id, humidity, 0));
		}
	}

	/**
	 * Metodo che prende come parametro in ingresso un JSONObject (jsonObject) e ne ricava in
	 * uscita il valore corrispondente al campo "visibility"
	 * 
	 * @param jsonObject
	 * @return visibilità
	 */
	public static int getVisibilityFromJSONObject(JSONObject jsonObject) {
		int visibility = jsonObject.getInt("visibility");
		return visibility;
	}
	
	/**
	 * Metodo che prende come parametri in ingresso un JSONArray (jsonArray) contenente dei nomi di stati
	 * e un'ArrayList (array); l'ArrayList viene popolata con i nomi degli stati, attraverso un parsing
	 * del JSONArray
	 * 
	 * @param jsonArray
	 * @param array
	 */
	public static void setCountriesNamesfromJSONArray(JSONArray jsonArray, ArrayList<String> array) {
		String name;
		
		for(int i=0; i<jsonArray.length(); i++) {
			name = jsonArray.getJSONObject(i).getString("name");
			array.add(name);
		}
	}
	
	/**
	 * Metodo che prende come parametri in ingresso un'ArrayList di Città con i loro dati e una data;
	 * quello che viene fatto è creare un JSONArray contenente tanti JSONObject corrispondenti a ciascuna
	 * delle città contenute nella lista, ognuno dei quali possiede a loro volta un altro JSONArray
	 * contenente dei JSONObject indicanti la visibilità e l'umidità rilevata in una determinata data
	 * 
	 * Nota: tale metodo aggiunge solo un rilevamento di dati nel JSONArray annidato, poichè si occupa
	 * di creare un archivio storico, non di aggiornarlo
	 * 
	 * @param array lista delle città con i loro dati
	 * @param date data di rilevamento dei dati
	 * 
	 * @return un JSONArray contenente i dati strutturati nel modo descritto precedentemente
	 */
	public static JSONArray generateHistory(ArrayList<CityWithData> array, String date) {
		JSONArray json_arr = new JSONArray();
		JSONObject json_obj;
		JSONArray data_array;
		JSONObject single_data;
		
		for(int i=0; i<array.size(); i++) {
			json_obj = new JSONObject();
			data_array = new JSONArray();
			single_data = new JSONObject();
			
			single_data.put("humidity", array.get(i).getHumidity());
			single_data.put("visibility",array.get(i).getVisibility());
			single_data.put("date", date);
			
			data_array.put(single_data);
			
			json_obj.put("name", array.get(i).getName());
			json_obj.put("lat", array.get(i).getLat());
			json_obj.put("lon", array.get(i).getLon());
			json_obj.put("data", data_array);
			json_arr.put(json_obj);
		}
		
		return json_arr;
	}
}

	