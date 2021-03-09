package com.progettoPO.OPENWEATHERAPP.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * <b>Classe</b> che testa i metodi di ListOfCountries
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
class ListOfCountriesTest {

	private ListOfCountries list;
	/**
	 * Metodo che inizializza tutto ciò che serve per testare i metodi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		list = new ListOfCountries();
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
	 * Metodo che testa il corretto funzionamento del metodo countrySupported()
	 */
	@Test
	void testCountrySupported() {
		assertTrue(list.countrySupported("Italy"));
		assertFalse(list.countrySupported("Italiaaaa"));
	}
	
	/**
	 * Metodo che testa il corretto funzionamento del metodo indexOfCountry()
	 */
	@Test
	void testIndexOfCountry() {
		assertEquals(110, list.indexOfCountry("Italy"));
		assertEquals(-1, list.indexOfCountry("Italiaaaa"));
	}

}
