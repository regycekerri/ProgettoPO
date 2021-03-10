package com.progettoPO.OPENWEATHERAPP.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * <b>Classe</b> che testa i metodi di WeatherServiceImpl
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele;
 */
class WeatherServiceImplTest {
	
	private WeatherServiceImpl service;

	/**
	 * Metodo che inizializza tutto ciò che serve per testare i metodi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new WeatherServiceImpl();
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
	 * Metodo che testa il corretto lancio delle eccezioni del metodo statsHumidityService()
	 */
	@Test
	void testStatsHumidityService() {
		IllegalArgumentException i1 = assertThrows(IllegalArgumentException.class, ()->service.statsHumidityService("Italy", "abc", 20, 30));
		assertEquals("Invalid order (order must equal: [min, max, avg, var]", i1.getMessage());
	}
	
	/**
	 * Metodo che testa il corretto lancio delle eccezioni del metodo statsVisibilityService()
	 */
	@Test
	void testStatsVisibilityService() {
		IllegalArgumentException i2 = assertThrows(IllegalArgumentException.class, ()->service.statsVisibilityService("Italy", "abc", 20, 30));
		assertEquals("Invalid order (order must equal: [min, max, avg, var]", i2.getMessage());
	}

}
