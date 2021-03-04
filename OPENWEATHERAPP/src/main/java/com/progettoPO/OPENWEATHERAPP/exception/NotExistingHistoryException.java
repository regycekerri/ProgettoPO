package com.progettoPO.OPENWEATHERAPP.exception;

/**
 * <p>
 * <b>Classe</b> che descrive l'eccezione sollevata quando viene fatta una richiesta di rimozione
 * dello storico di uno stato che non lo possiede
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
@SuppressWarnings("serial")
public class NotExistingHistoryException extends Exception {
	
	private String msg;
	
	/**
	 * Costruttore parametrizzato
	 * 
	 * @param msg messaggio di errore
	 */
	public NotExistingHistoryException(String msg) {
		super();
		this.msg = msg;
	}
	
	/**
	 * Metodo che restituisce il messaggio di errore
	 * 
	 * @return msg messaggio di errore
	 */
	public String getMsg() {
		return msg;
	}
}
