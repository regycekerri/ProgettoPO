package com.progettoPO.OPENWEATHERAPP.utilities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.progettoPO.OPENWEATHERAPP.model.CityStats;
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
	
	/**
	 * Metodo che, dato un JSONArray contenente dei dati storici, genera una lista di città con i 
	 * corrispettivi indici statistici riguardanti l'umidità, filtrati nel numero di città (cnt) e 
	 * nel periodo di tempo (period)
	 * 
	 * @param jsonArray JSONArray contenente i dati storici
	 * @param cnt numero di città per il quale si richiedono le statistiche
	 * @param period periodo temporale in giorni sul quale effettuare statistiche
	 * @return lista di città con i corrispettivi indici statistici riguardanti l'umidità
	 */
	public static ArrayList<CityStats> setHumidityStatsFromJSONArray(JSONArray jsonArray, int cnt, int period) {
		ArrayList<CityStats> array = new ArrayList<CityStats>();
		ArrayList<Integer> humidities;
		
		JSONObject city;
		JSONArray city_data;
		JSONObject single_city_data;
		
		String name;
		double lat;
		double lon;
		int min;
		int max;
		double avg;
		double var;
		LocalDate date;
		
		for(int i=0; i<cnt; i++) {
			city = jsonArray.getJSONObject(i);
			city_data = city.getJSONArray("data");
			
			humidities = new ArrayList<Integer>();
			
			name = city.getString("name");
			lat = city.getDouble("lat");
			lon = city.getDouble("lon");
			
			for(int j=0; j<city_data.length(); j++) {
				single_city_data = city_data.getJSONObject(j);
				
				date = LocalDate.parse(single_city_data.getString("date"));
				
				if(date.until(LocalDate.now(), ChronoUnit.DAYS)<period) {
					humidities.add(single_city_data.getInt("humidity"));
				}
			}
			
			min = Statistics.getMin(humidities);
		    max = Statistics.getMax(humidities);
		    avg = Statistics.getAvg(humidities);
		    var = Statistics.getVar(humidities);
		    
		    array.add(new CityStats(name, lat, lon, "humidity", min, max, avg, var));
		}
		
		return array;
	}
	
	/**
	 * Metodo che, dato un JSONArray contenente dei dati storici, genera una lista di città con i 
	 * corrispettivi indici statistici riguardanti la visibilità, filtrati nel numero di città (cnt) e 
	 * nel periodo di tempo (period)
	 * 
	 * @param jsonArray JSONArray contenente i dati storici
	 * @param cnt numero di città per il quale si richiedono le statistiche
	 * @param period periodo temporale in giorni sul quale effettuare statistiche
	 * @return lista di città con i corrispettivi indici statistici riguardanti la visibilità
	 */
	public static ArrayList<CityStats> setVisibilityStatsFromJSONArray(JSONArray jsonArray, int cnt, int period) {
		ArrayList<CityStats> array = new ArrayList<CityStats>();
		ArrayList<Integer> visibilities;
		
		JSONObject city;
		JSONArray city_data;
		JSONObject single_city_data;
		
		String name;
		double lat;
		double lon;
		int min;
		int max;
		double avg;
		double var;
		LocalDate date;
		
		for(int i=0; i<cnt; i++) {
			city = jsonArray.getJSONObject(i);
			city_data = city.getJSONArray("data");
			
			visibilities = new ArrayList<Integer>();
			
			name = city.getString("name");
			lat = city.getDouble("lat");
			lon = city.getDouble("lon");
			
			for(int j=0; j<city_data.length(); j++) {
				single_city_data = city_data.getJSONObject(j);
				
				date = LocalDate.parse(single_city_data.getString("date"));
				
				if(date.until(LocalDate.now(), ChronoUnit.DAYS)<period) {
					visibilities.add(single_city_data.getInt("visibility"));
				}
			}
			
			min = Statistics.getMin(visibilities);
		    max = Statistics.getMax(visibilities);
		    avg = Statistics.getAvg(visibilities);
		    var = Statistics.getVar(visibilities);
		    
		    array.add(new CityStats(name, lat, lon, "visibility", min, max, avg, var));
		}
		
		return array;
	}
}

	