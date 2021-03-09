package com.progettoPO.OPENWEATHERAPP.service;

import org.springframework.stereotype.Service;

import com.progettoPO.OPENWEATHERAPP.model.CountryInfo;
import com.progettoPO.OPENWEATHERAPP.utilities.CSVParser;
import com.progettoPO.OPENWEATHERAPP.utilities.FileUtilities;

/**
 * <p>
 * <b>Classe</b> contenente dei metodi che creano e gestiscono la lista degli stati supportati dall'applicativo
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
@Service
public class ListOfCountries {
	
	public CountryInfo[] countries;
	private final static String file = "src/main/resources/country-capitals.txt";
	
	/**
	 * Costruttore di default che crea la struttura dati contenente le informazioni sugli stati
	 */
	public ListOfCountries(){
		countries=CSVParser.CSVParserCountries(file);
	}
	
	/**
	 * Metodo che restituisce una stringa che verrà interpretata dal browser come un file html
	 * che visualizza la lista degli stati supportati dall'applicativo
	 * 
	 * @return stringa interpretata come un file html
	 */
	public String visualizeList() {
		String html = FileUtilities.readFromFile("src/main/resources/stati.html");
		return html;
	}
	
	/**
	 * Metodo che verifica se il nome dello stato passato come parametro è contenuto nella lista
	 * degli stati supportati dall'applicativo
	 * 
	 * @param country nome dello stato passato come parametro
	 * @return un valore booleano indicante l'esito della ricerca
	 */
	public boolean countrySupported(String country) {
		boolean supported = false;
		
		for(int i=0; i<countries.length && supported==false; i++) {
			if(country.equals(countries[i].getCountry_name()))
				supported = true;
		}
			
		return supported;
	}
	
	/** 
	 * Metodo che riceve in ingresso il nome di uno stato e cerca l'indice dell'elemento nella lista
	 * degli stati supportati che ha proprio quel nome
	 * 
	 * @param country nome dello stato passato come parametro
	 * @return l'indice (pari a -1 se la ricerca è terminata con esito negativo)
	 */
	public int indexOfCountry(String country) {
		boolean found = false;
		int index = -1;
		
		for(int i=0; i<countries.length && found==false; i++) {
			if(country.equals(countries[i].getCountry_name())) {
				index = i;
				found = true;
			}
		}
		return index;
	}

}
