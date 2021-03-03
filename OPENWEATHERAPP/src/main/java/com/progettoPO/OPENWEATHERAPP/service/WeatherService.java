package com.progettoPO.OPENWEATHERAPP.service;

import java.util.ArrayList;

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
	
}
	

