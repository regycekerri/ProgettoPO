package com.progettoPO.OPENWEATHERAPP.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.progettoPO.OPENWEATHERAPP.model.CityWithData;

/**
 * <p>
 * <b>Classe</b> che implementa tutti i metodi dichiarati in "WeatherService"
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
@Service
public class WeatherServiceImpl implements WeatherService {
	
	/**
	 * Per la descrizione di tale metodo fare riferimento alla dichiarazione in "WeatherService"
	 */
	@Override
	public ArrayList<CityWithData> actualDataService(double lat, double lon, int cnt){
		ArrayList<CityWithData> array;
		array = WeatherAPI.getHumidityAndVisibility(lat, lon, cnt);
		return array;
	}

}
