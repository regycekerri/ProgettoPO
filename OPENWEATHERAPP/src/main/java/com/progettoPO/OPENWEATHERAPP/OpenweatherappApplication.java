package com.progettoPO.OPENWEATHERAPP;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.progettoPO.OPENWEATHERAPP.utilities.APIUtilities;
import com.progettoPO.OPENWEATHERAPP.utilities.FileUtilities;
import com.progettoPO.OPENWEATHERAPP.utilities.JSONParser;

/**
 * <p>
 * <b>Classe</b> che permette l'avvio (o anche chiamato "bootstrap") dell'applicazione Spring Boot
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
@SpringBootApplication
public class OpenweatherappApplication {

	/** 
	 * Main dell'applicazione
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OpenweatherappApplication.class, args);
	}
	
	/** 
	 * Classe di configurazione dello scheduling
	 */
	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
	class SchedulingConfiguration {}
	
	/**
	 * Metodo che si occupa di aggiornare ogni 5 ore gli archivi storici degli stati
	 */
	@Scheduled(fixedRate = 18000000L)
	public void updateHistory() {
		ArrayList<String> list_to_update = new ArrayList<String>();
		String date = LocalDate.now().toString();
		
		String input1 = FileUtilities.readFromFile("src/main/resources/history/countries-with-history.txt");
		
		if((!input1.isBlank()) && (!input1.equals("[]"))) {
			JSONArray countries_with_history = new JSONArray(input1);
			JSONParser.setCountriesNamesfromJSONArray(countries_with_history, list_to_update);
			
			JSONArray json_country;
			JSONArray json_country_api;
			JSONObject single_data;
			String input2;
			String api;
			int humidity;
			int visibility;
			for(String country : list_to_update) {
				input2 = FileUtilities.readFromFile("src/main/resources/history/"+country+".txt");
				json_country = new JSONArray(input2);
				api = APIUtilities.InputFromAPI("http://localhost:8080/actualdata?country="+country+"&cnt=50");
				json_country_api = new JSONArray(api);
				
				for(int i=0; i<json_country.length(); i++) {
					humidity = json_country_api.getJSONObject(i).getInt("humidity");
					visibility = json_country_api.getJSONObject(i).getInt("visibility");
					single_data = new JSONObject("{\"date\":\""+date+"\",\"visibility\":"+visibility+",\"humidity\":"+humidity+"}");
					json_country.getJSONObject(i).getJSONArray("data").put(single_data);
				}
				
				FileUtilities.writeOnFile("src/main/resources/history/"+country+".txt", json_country.toString());
			}
		}
	}
}

