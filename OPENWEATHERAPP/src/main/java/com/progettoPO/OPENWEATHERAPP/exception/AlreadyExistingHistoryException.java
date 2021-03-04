package com.progettoPO.OPENWEATHERAPP.exception;

/**
 * <p>
 * <b>Classe</b> che descrive l'eccezione sollevata quando viene fatta una richiesta di creazione 
 * dello storico di uno stato che già lo possiede
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */

@SuppressWarnings("serial")
public class AlreadyExistingHistoryException extends Exception {
	
	private String msg;
	
	/**
	 * Costruttore parametrizzato
	 * 
	 * @param msg messaggio di errore
	 */
	public AlreadyExistingHistoryException(String msg) {
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
