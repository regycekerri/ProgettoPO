package com.progettoPO.OPENWEATHERAPP.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *<p>
 *<b>Classe</b> contenente metodi che permettono di manipolare i file
 *</p>
 *
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
public class FileUtilities {
	
	/**
	 * Metodo che restituisce sotto forma di stringa il contenuto di un file
	 * 
	 * @param file file da leggere
	 * @return stringa contenente il contenuto del file
	 */
	public static String readFromFile(String file) {
		String out="";
		
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(file));
			
			String line = "";
			while((line=reader.readLine())!=null) {
				out+=line;
				out+="\n";
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}
	
	/**
	 * Metodo che scrive su un file il contenuto di una stringa
	 * 
	 * Nota: l'operazione prevede una sovrascrittura nel caso in cui il file contenga già del testo
	 * 
	 * @param file file su cui scrivere
	 * @param input stringa che verrà scritta nel file
	 */
	public static void writeOnFile(String file, String input) {
		BufferedWriter writer = null;
		
		try {
			
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(input);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
