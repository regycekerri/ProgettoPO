package com.progettoPO.OPENWEATHERAPP.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * <b>Classe</b> che testa i metodi di Statistics
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 * 
 */
class StatisticsTest {

	private ArrayList<Integer> numbers;
	
	/**
	 * Metodo che inizializza tutto ciò che serve per testare i metodi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		numbers = new ArrayList<Integer>();
		numbers.add(2);
		numbers.add(4);
		numbers.add(6);
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
	 * Metodo che testa i metodi getMin(), getMax(), getAvg(), getVar()
	 */
	@Test
	void testStatistics() {
		assertEquals(2, Statistics.getMin(numbers));
		assertEquals(6, Statistics.getMax(numbers));
		assertEquals(4, Statistics.getAvg(numbers));
		assertEquals((double)8/3, Statistics.getVar(numbers));
	}

}
