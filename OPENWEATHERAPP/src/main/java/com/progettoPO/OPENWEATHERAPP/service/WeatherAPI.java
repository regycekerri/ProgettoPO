package com.progettoPO.OPENWEATHERAPP.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.progettoPO.OPENWEATHERAPP.model.CityStats;
import com.progettoPO.OPENWEATHERAPP.model.CityWithData;
import com.progettoPO.OPENWEATHERAPP.utilities.APIUtilities;
import com.progettoPO.OPENWEATHERAPP.utilities.FileUtilities;
import com.progettoPO.OPENWEATHERAPP.utilities.JSONParser;

/**
 * <p>
 * <b>Classe</b> che invoca, attraverso i suoi metodi, le chiamate alle API adatte alle determinate
 * rotte richieste, ricavando da esse dei dati utili, strutturati o meno
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
public class WeatherAPI {
	
	private final static String APIkey = FileUtilities.readFromFile("src/main/resources/APIkey.txt");
	
	/**
	 * Tale metodo prende come parametri di ingresso una latitudine, una longitudine e un numero cnt e ricava i dati
	 * riguardanti l'umidità e la visibilità di ciascuna delle cnt città più vicine a quel punto, attraverso
	 * delle chiamate all'API; tali dati vengono utilizzati per creare una lista di città da restituire
	 * in uscita
	 * 
	 * @param lat latitudine
	 * @param lon longitudine
	 * @param cnt numero di città
	 * @return lista delle città con le corrispettive informazioni
	 * @throws IllegalArgumentException se vengono passati parametri non validi
	 */
	public static ArrayList<CityWithData> getHumidityAndVisibility(double lat, double lon, int cnt) throws IllegalArgumentException{
		if(lat<-90.0||lat>90.0)
			throw new IllegalArgumentException("Invalid latitude");
		
		if(lon<-180.0||lon>180.0)
			throw new IllegalArgumentException("Invalid longitude");
		
		if(cnt<1||cnt>50)
			throw new IllegalArgumentException("Invalid number of cities (cnt must belong to this range: [1,50])");
		
		ArrayList<CityWithData> array = new ArrayList<CityWithData>();
		
		String api1 = "https://api.openweathermap.org/data/2.5/find?lat="+lat+"&lon="+lon+"&cnt="+cnt+"&appid="+APIkey;
		String json1 = APIUtilities.InputFromAPI(api1);
		
		JSONObject jsonObject = new JSONObject(json1);
		JSONArray jsonArray = jsonObject.getJSONArray("list");
		
		JSONParser.setHumidityFromJSONArray(jsonArray, array);
		
		String api2;
		String json2;
		int visibility;
	    for(CityWithData city : array) {
	    	api2 = "https://api.openweathermap.org/data/2.5/weather?id="+city.getCity_id()+"&appid="+APIkey;
	    	json2 = APIUtilities.InputFromAPI(api2);
	    	jsonObject = new JSONObject(json2);
	    	visibility = JSONParser.getVisibilityFromJSONObject(jsonObject);
	    	city.setVisibility(visibility);	
		}
	    
	    return array;
	}
	
	/**
	 * Metodo che riceve come parametri lo stato per il quale si vogliono effettuare le statistiche
	 * (country), il numero di città richieste attorno alla capitale (compresa) (cnt) e il periodo 
	 * temporale in giorni sul quale effettuare statistiche (period); viene restituita la lista delle
	 * città con i corrispettivi indici statistici riguardanti l'umidità
	 * 
	 * @param country stato per il quale si vogliono effettuare le statistiche
	 * @param cnt numero di città richieste attorno alla capitale (compresa) (cnt)
	 * @param period periodo temporale in giorni sul quale effettuare statistiche (period)
	 * @return lista delle città con i corrispettivi indici statistici riguardanti l'umidità
	 * @throws IllegalArgumentException se vengono inseriti in modo errato i parametri
	 */
	public static ArrayList<CityStats> getHumidityStats(String country, int cnt, int period) throws IllegalArgumentException{
		if(cnt<1 || cnt>50)
			throw new IllegalArgumentException("Invalid number of cities (cnt must belong to this range: [1,50])");
		if(period<1 || period>30)
			throw new IllegalArgumentException("Invalid period (period must belong to this range: [1,30])");
		
		String history = FileUtilities.readFromFile("src/main/resources/history/"+country+".txt");
		JSONArray json_arr = new JSONArray(history);
		
		ArrayList<CityStats> array = JSONParser.setHumidityStatsFromJSONArray(json_arr, cnt, period);
		
		return array;
	}
	
	/**
	 * Metodo che riceve come parametri lo stato per il quale si vogliono effettuare le statistiche
	 * (country), il numero di città richieste attorno alla capitale (compresa) (cnt) e il periodo 
	 * temporale in giorni sul quale effettuare statistiche (period); viene restituita la lista delle
	 * città con i corrispettivi indici statistici riguardanti la visibilità
	 * 
	 * @param country stato per il quale si vogliono effettuare le statistiche
	 * @param cnt numero di città richieste attorno alla capitale (compresa) (cnt)
	 * @param period periodo temporale in giorni sul quale effettuare statistiche (period)
	 * @return lista delle città con i corrispettivi indici statistici riguardanti la visibilità
	 * @throws IllegalArgumentException se vengono inseriti in modo errato i parametri
	 */
	public static ArrayList<CityStats> getVisibilityStats(String country, int cnt, int period) throws IllegalArgumentException{
		if(cnt<1 || cnt>50)
			throw new IllegalArgumentException("Invalid number of cities (cnt must belong to this range: [1,50])");
		if(period<1 || period>30)
			throw new IllegalArgumentException("Invalid period (period must belong to this range: [1,30])");
		
		String history = FileUtilities.readFromFile("src/main/resources/history/"+country+".txt");
		JSONArray json_arr = new JSONArray(history);
		
		ArrayList<CityStats> array = JSONParser.setVisibilityStatsFromJSONArray(json_arr, cnt, period);
		
		return array;
	}
}
