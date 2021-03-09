package com.progettoPO.OPENWEATHERAPP.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * <b>Classe</b> che testa i metodi di APIUtilities
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
class APIUtilitiesTest {

	private String malformedURL;
	private String wellformedURL;
	/**
	 * Metodo che inizializza tutto ciò che serve per testare i metodi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		malformedURL = "http://NotExisting";
		wellformedURL = "https://api.openweathermap.org/data/2.5/weather?lat=15&lon=10&appid="+FileUtilities.readFromFile("scr/main/resources/APIkey.txt");
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
	 * Metodo che testa il corretto funzionamento del metodo InputFromAPI()
	 */
	@Test
	void testInputFromAPI() {
		assertThrows(MalformedURLException.class, ()->APIUtilities.InputFromAPI(malformedURL));
		
		assertNotNull(APIUtilities.InputFromAPI(wellformedURL));
	}

}
