package com.progettoPO.OPENWEATHERAPP.service;

import java.util.ArrayList;

import com.progettoPO.OPENWEATHERAPP.model.CityStats;
import com.progettoPO.OPENWEATHERAPP.model.CityWithData;

/**
 * Interfaccia del servizio che restituisce le informazioni metereologiche 
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
public interface WeatherService {
	
	/**
	 * Metodo che restituisce la lista delle città con le loro rispettive umidità e visibilità 
	 * richieste dalla rotta "/actualdata"
	 * 
	 * @param lat latitudine del centro
	 * @param lon longitudine del centro
	 * @param cnt numero di città attorno al centro richieste
	 * 
	 * @return la lista delle città con la loro rispettiva umidità e visibilità
	 */
	public abstract ArrayList<CityWithData> actualDataService(double lat, double lon, int cnt);
	
	/**
	 * Metodo che restituisce la lista delle città con le loro rispettive statistiche riguardanti
	 * l'umidità richieste dalla rotta "/stats/humidity/{order}/{period}"
	 * 
	 * @param country stato dal quale attingere i dati sulle città
	 * @param order modalità di ordinamento delle città
	 * @param cnt numero di città attorno alla capitale richieste
	 * @param period periodo di tempo in giorni sul quale fare le statistiche
	 * 
	 * @return la lista delle città con le rispettive statistiche riguardanti l'umidità
	 */
	public abstract ArrayList<CityStats> statsHumidityService(String country, String order, int cnt, int period);
	
	/**
	 * Metodo che restituisce la lista delle città con le loro rispettive statistiche riguardanti 
	 * la visibilità richieste dalla rotta "/stats/humidity/{order}/{period}"
	 * 
	 * @param country stato dal quale attingere i dati sulle città
	 * @param order modalità di ordinamento delle città
	 * @param cnt numero di città attorno alla capitale richieste
	 * @param period periodo di tempo in giorni sul quale fare le statistiche
	 * 
	 * @return la lista delle città con le rispettive statistiche riguardanti la visibilità
	 */
	public abstract ArrayList<CityStats> statsVisibilityService(String country, String order, int cnt, int period);
}
	

