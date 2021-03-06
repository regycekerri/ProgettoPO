package com.progettoPO.OPENWEATHERAPP.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Service;

import com.progettoPO.OPENWEATHERAPP.model.CityStats;
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
	
	/**
	 * Per la descrizione di tale metodo fare riferimento alla dichiarazione in "WeatherService"
	 */
	public ArrayList<CityStats> statsHumidityService(String country, String order, int cnt, int period){
		ArrayList<CityStats> array = WeatherAPI.getHumidityStats(country, cnt, period);
		
		if(order.equals("min")) {
			Collections.sort(array, CityStats.MinComparator);
			return array;
		}else if(order.equals("max")) {
			Collections.sort(array, CityStats.MaxComparator);
			return array;
		}else if(order.equals("avg")) {
			Collections.sort(array, CityStats.AvgComparator);
			return array;
		}else if(order.equals("var")) {
			Collections.sort(array, CityStats.VarComparator);
			return array;
		}else {
			throw new IllegalArgumentException("Invalid order (order must equal: [min, max, avg, var]");
		}
	}
	
	/**
	 * Per la descrizione di tale metodo fare riferimento alla dichiarazione in "WeatherService"
	 */
	public ArrayList<CityStats> statsVisibilityService(String country, String order, int cnt, int period){
		ArrayList<CityStats> array = WeatherAPI.getVisibilityStats(country, cnt, period);
		
		if(order.equals("min")) {
			Collections.sort(array, CityStats.MinComparator);
			return array;
		}else if(order.equals("max")) {
			Collections.sort(array, CityStats.MaxComparator);
			return array;
		}else if(order.equals("avg")) {
			Collections.sort(array, CityStats.AvgComparator);
			return array;
		}else if(order.equals("var")) {
			Collections.sort(array, CityStats.VarComparator);
			return array;
		}else {
			throw new IllegalArgumentException("Invalid order (order must equal: [min, max, avg, var]");
		}
	}
}
