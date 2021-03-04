package com.progettoPO.OPENWEATHERAPP.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>
 * <b>Classe</b> contenente dei metodi utili per interagire con le API
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
public class APIUtilities {
	
	/**
	 * Metodo che riceve come parametro un url (per gli scopi dell'applicativo sarà l'url associato a una chiamata all'API),
	 * apre una connessione Https e infine, attraverso uno stream di input, restituisce una stringa contenente
	 * la risorsa desiderata (originaria dall'url)
	 * 
	 * @param api
	 * @return la risorsa desiderata (originaria dall'url)
	 */
	public static String InputFromAPI(String api) {
		HttpURLConnection connection=null;
		URL url;
		try {
			url = new URL(api);
			connection=(HttpURLConnection)url.openConnection();
			connection.connect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonout="";
		BufferedReader reader=null;
		
		try {
			reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String str;
			while((str=reader.readLine())!=null) {
				jsonout+=str;
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonout;
	}

}
