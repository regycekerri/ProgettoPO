package com.progettoPO.OPENWEATHERAPP.exception;

/**
 * <p>
 * <b>Classe</b> che descrive l'eccezione sollevata quando viene fatta una richiesta in una rotta qualsiasi
 * inserendo come parametro uno stato non supportato dall'applicativo
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
public class CountryNotSupportedException extends Exception {
	
	private String msg;
	
	/**
	 * Costruttore parametrizzato
	 * 
	 * @param msg messaggio di errore
	 */
	public CountryNotSupportedException(String msg) {
		super();
		this.msg = msg;
	}
	
	/**
	 * Metodo che restituisce il messaggio di errore
	 * 
	 * @return messaggio di errore
	 */
	public String getMsg() {
		return msg;
	}

}
