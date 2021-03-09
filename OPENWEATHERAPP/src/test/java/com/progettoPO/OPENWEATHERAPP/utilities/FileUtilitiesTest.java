package com.progettoPO.OPENWEATHERAPP.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * <b>Classe</b> che testa i metodi di FileUtilities
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
class FileUtilitiesTest {

	private String file;
	private String msg;
	
	/**
	 * Metodo che inizializza tutto ciò che serve per testare i metodi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		file = "src/main/resources/tests/test.txt";
		msg = "test\n";
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
	 * Metodo che testa il corretto funzionamento del metodo readFromFile()
	 */
	@Test
	void testFileUtilities() {
		assertTrue(FileUtilities.readFromFile(file).equals(msg));
	}

}
