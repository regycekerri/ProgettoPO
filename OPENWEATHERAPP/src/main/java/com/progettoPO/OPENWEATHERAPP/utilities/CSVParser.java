package com.progettoPO.OPENWEATHERAPP.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.progettoPO.OPENWEATHERAPP.model.CountryInfo;

/**
 * <p>
 * <b>Classe</b> contenente il metodo necessario a parsare il file "country-capitals.txt"
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
public class CSVParser {
	
	/**
	 * Metodo che riceve come parametro il nome del file che dovrà essere parsato come vettore di oggetti
	 * del tipo CountryInfo
	 * 
	 * Nota: il metodo è volutamente non permissivo, infatti genera un eccezione se il file passato non è esattamente
	 * quello per cui è stato concepito ("src/main/resources/country-capitals.txt")
	 * 
	 * @param file nome del file
	 * @return vettore contenente gli oggetti di tipo CountryInfo
	 * @throws IllegalArgumentException se il nome del file non coincide con "src/main/resources/country-capitals.txt"
	 */
	public static CountryInfo[] CSVParserCountries(String file) throws IllegalArgumentException {
		if(file.equals("src/main/resources/country-capitals.txt")) {
			CountryInfo[] countries = new CountryInfo[241];
			
			BufferedReader reader = null;
			String line = "";
			String[] values = new String[6];
			int i=0;
			
			try {
				
				reader = new BufferedReader(new FileReader(file));
				while(((line=reader.readLine())!=null)&&(i<241)) {
					values = line.split(",");
					countries[i] = new CountryInfo(values[0], values[1], Double.parseDouble(values[2]), Double.parseDouble(values[3]));
					i++;
				}
				
				reader.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return countries;
		} else
			throw new IllegalArgumentException("Questo file non è consentito");
	}
}
