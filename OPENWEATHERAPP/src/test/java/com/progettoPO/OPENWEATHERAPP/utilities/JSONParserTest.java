package com.progettoPO.OPENWEATHERAPP.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.progettoPO.OPENWEATHERAPP.model.CityStats;

/**
 * <p>
 * <b>Classe</b> che testa alcuni metodi di JSONParser
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
class JSONParserTest {

	private String string;
	private JSONArray array;
	ArrayList<CityStats> array1;
	ArrayList<CityStats> array2;
	
	/**
	 * Metodo che inizializza tutto ciò che serve per testare i metodi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		string = "[{\"data\":[{\"date\":\"2021-03-06\",\"visibility\":10000,\"humidity\":67},{\"date\":\"2021-03-06\",\"visibility\":10000,\"humidity\":59},{\"date\":\"2021-03-08\",\"visibility\":10000,\"humidity\":93}],\"name\":\"Pigna\",\"lon\":12.4846,\"lat\":41.8964}]";
	    array = new JSONArray(string);
	    array1 = JSONParser.setHumidityStatsFromJSONArray(array, 1, 30);
	    array2 = JSONParser.setVisibilityStatsFromJSONArray(array, 1, 30);
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
	 * Metodo che testa il corretto funzionamento dei metodi setHumidityStatsFromJSONArray() e setVisibilityStatsFromJSONArray()
	 */
	@Test
	void testJSONParser() {
		assertEquals("Pigna", array1.get(0).getName());
		assertTrue(array1.get(0).getMin()<=array1.get(0).getMax());
		assertTrue(array1.get(0).getAvg()!=0);
		assertTrue(array1.get(0).getVar()!=0);
		
		assertEquals("Pigna", array2.get(0).getName());
		assertTrue(array2.get(0).getMin()<=array2.get(0).getMax());
		assertTrue(array2.get(0).getAvg()!=0);
		assertTrue(array2.get(0).getVar()!=0);
	}

}
