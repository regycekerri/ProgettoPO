package com.progettoPO.OPENWEATHERAPP.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.progettoPO.OPENWEATHERAPP.model.CityWithData;

/**
 * <p>
 * <b>Classe</b> che testa i metodi di WeatherAPI
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
class WeatherAPITest {

	private ArrayList<CityWithData> array;
	private double lat;
	private double lon;
	private int cnt;
	
	
	/**
	 * Metodo che inizializza tutto ciò che serve per testare i metodi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		lat = 51.5085;
		lon = -0.1257;
		cnt = 1;
		array = WeatherAPI.getHumidityAndVisibility(lat, lon, cnt);
	}

	/**
	 * Metodo che dealloca tutto ciò che è stato creato in precedenza per testare i metodi
	 * 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Metodo che testa il corretto funzionamento del metodo getHumidityAndVisibility()
	 */
	@Test
	void testGetHumidityAndVisibility() {
		IllegalArgumentException i1 = assertThrows(IllegalArgumentException.class, ()->WeatherAPI.getHumidityAndVisibility(200, 60.3, 25));
		assertEquals("Invalid latitude", i1.getMessage());
		
		IllegalArgumentException i2 = assertThrows(IllegalArgumentException.class, ()->WeatherAPI.getHumidityAndVisibility(30.8, -190, 25));
		assertEquals("Invalid longitude", i2.getMessage());
		
		IllegalArgumentException i3 = assertThrows(IllegalArgumentException.class, ()->WeatherAPI.getHumidityAndVisibility(15.8, 12.3, 70));
		assertEquals("Invalid number of cities (cnt must belong to this range: [1,50])", i3.getMessage());
		
		assertEquals("London", array.get(0).getName());
		assertEquals(51.5085, array.get(0).getLat());
		assertEquals(-0.1257, array.get(0).getLon());
		assertEquals(1, array.size());
	}
	
	/**
	 * Metodo che testa il corretto lancio delle eccezioni del metodo getHumidityStats()
	 */
	@Test
	void testGetHumidityStats() {
		IllegalArgumentException i1 = assertThrows(IllegalArgumentException.class, ()->WeatherAPI.getHumidityStats("Italy", 60, 1));
		assertEquals("Invalid number of cities (cnt must belong to this range: [1,50])", i1.getMessage());
		
		IllegalArgumentException i2 = assertThrows(IllegalArgumentException.class, ()->WeatherAPI.getHumidityStats("Italy", 5, 0));
		assertEquals("Invalid period (period must belong to this range: [1,30])", i2.getMessage());
	}
	
	/**
	 * Metodo che testa il corretto lancio delle eccezioni del metodo getVisibilityStats()
	 */
	@Test
	void testGetVisibilityStats() {
		IllegalArgumentException i1 = assertThrows(IllegalArgumentException.class, ()->WeatherAPI.getVisibilityStats("Italy", 60, 1));
		assertEquals("Invalid number of cities (cnt must belong to this range: [1,50])", i1.getMessage());
		
		IllegalArgumentException i2 = assertThrows(IllegalArgumentException.class, ()->WeatherAPI.getVisibilityStats("Italy", 5, 0));
		assertEquals("Invalid period (period must belong to this range: [1,30])", i2.getMessage());
	}

}
