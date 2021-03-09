package com.progettoPO.OPENWEATHERAPP.utilities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>
 * <b>Classe</b> che contiene dei metodi utili ad effettuare dei calcoli statistici
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
public class Statistics {
	
	/**
	 * Metodo che, data una lista di interi, restituisce il valore minimo
	 * 
	 * @param numbers lista di interi
	 * @return valore minimo
	 */
	public static int getMin(ArrayList<Integer> numbers) {
		int min = Collections.min(numbers);
		return min;
	}
	
	/**
	 * Metodo che , data una lista di interi, restituisce il valore massimo
	 * 
	 * @param numbers lista di interi
	 * @return valore massimo
	 */
	public static int getMax(ArrayList<Integer> numbers) {
		int max = Collections.max(numbers);
		return max;
	}
	
	/**
	 * Metodo che, data una lista di interi, restituisce il valore medio
	 * 
	 * @param numbers lista di interi
	 * @return valore medio
	 */
	public static double getAvg(ArrayList<Integer> numbers) {
		double avg = 0;
		
		for(int number : numbers) {
			avg += number;
		}
		
		avg /= numbers.size();
		
		return avg;
	}
	
	/**
	 * Metodo che, data una lista di interi, restituisce la varianza
	 * 
	 * @param numbers lista di interi
	 * @return varianza
	 */
	public static double getVar(ArrayList<Integer> numbers) {
		double avg = getAvg(numbers);
		
		double deviation;
		double sum_of_deviations = 0;
		for(int number : numbers) {
			
			deviation = (number - avg) * (number - avg);
			sum_of_deviations += deviation;
		}
		
		double var = sum_of_deviations / numbers.size();
		
		return var;
	}
}
