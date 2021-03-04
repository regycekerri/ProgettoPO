package com.progettoPO.OPENWEATHERAPP.service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.progettoPO.OPENWEATHERAPP.model.CityWithData;
import com.progettoPO.OPENWEATHERAPP.utilities.FileUtilities;
import com.progettoPO.OPENWEATHERAPP.utilities.JSONParser;

/**
 * <p>
 * <b>Classe</b> che contiene dei metodi necessari a gestire gli archivi storici degli stati e strutture
 * dati contenenti informazioni rigurdanti essi
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
@Service
public class History {
	
	private final String path = "src/main/resources/history/";
	private ArrayList<String> list;
	
	/**
	 * Costruttore di default, che inizializza la lista degli stati che possiedono già un archivio
	 */
	public History() {
		list = getCountriesWithHistory();
	}
	/**
	 * Metodo che restituisce la lista degli stati di cui si ha un archivio storico, attraverso la lettura
	 * dal file che li contiene
	 * 
	 * @return lista degli stati di cui si ha un archivio storico
	 *
	 */
	public ArrayList<String> getCountriesWithHistory() {
		String input = FileUtilities.readFromFile(path+"countries-with-history.txt");
		ArrayList<String> list = new ArrayList<String>();
		
		if(input.isBlank()) {
			FileUtilities.writeOnFile(path+"countries-with-history.txt", "[]");
			return list;
		}else {
			JSONArray json_arr = new JSONArray(input);
			JSONParser.setCountriesNamesfromJSONArray(json_arr, list);
			
			return list;
		}
	}
	
	/**
	 * Metodo che verifica se uno stato appartiene alla lista di quelli per cui si ha 
	 * già un archivio o meno
	 * 
	 * @param country stato del quale si richiede la verifica
	 * @return esito della verifica
	 */
	public boolean containsCountry(String country) {
		if(list.contains(country))
			return true;
		else
			return false;
	}
	
	/** 
	 * Metodo che crea l'archivio storico di uno stato passato in ingresso
	 * 
	 * Nota: non si avrà mai la situazione in cui tale metodo venga chiamato quando l'archivio già esiste,
	 * poichè altre parti del codice lo impediscono
	 * 
	 * @param country
	 * @param lat
	 * @param lon
	 */
	public void createHistory(String country, double lat, double lon) {
		ArrayList<CityWithData> array = WeatherAPI.getHumidityAndVisibility(lat, lon, 50);
		String date = LocalDate.now().toString();
		
		JSONArray json_arr = JSONParser.generateHistory(array, date);
		String str = json_arr.toString();
		
		File file = new File(path+country+".txt");
		FileUtilities.writeOnFile(path+country+".txt", str);
		
		list.add(country);
		
		String countries_str = FileUtilities.readFromFile(path+"countries-with-history.txt");
		JSONArray countries_json = new JSONArray(countries_str);
		JSONObject json_obj = new JSONObject();
		json_obj.put("name", country);
		countries_json.put(json_obj);
		
		countries_str = countries_json.toString();
		FileUtilities.writeOnFile(path+"countries-with-history.txt", countries_str);
	}
	
	/**
	 * Metodo che cancella l'archivio storico di uno stato passato in ingresso
	 * 
	 * Nota: non si avrà mai la situazione in cui l'archivio dello stato passato in ingresso non
	 * esista, poichè altre parti del codice lo impediscono
	 * 
	 * @param country
	 */
	public void removeHistory(String country) {
		String str = FileUtilities.readFromFile(path+"countries-with-history.txt");
		JSONArray countries_json = new JSONArray(str);
		
		boolean found = false;
		
		for(int i=0; (i<countries_json.length())&&(found==false); i++) {
			if(countries_json.getJSONObject(i).getString("name").equals(country)) {
				countries_json.remove(i);
				found = true;
			}
		}
		
		str = countries_json.toString();
		FileUtilities.writeOnFile(path+"countries-with-history.txt", str);
		
		list.remove(country);
		
	    File file = new File(path+country+".txt");
		file.delete();
	}
}
