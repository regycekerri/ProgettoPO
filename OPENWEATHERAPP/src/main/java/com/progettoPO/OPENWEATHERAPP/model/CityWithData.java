package com.progettoPO.OPENWEATHERAPP.model;

/**
 * <p>
 * <b>Classe</b> che estende la classe City, aggiungendo gli attributi contenenti l'umidità (humidity)
 * e la visibilità (visibility) della città
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 *
 */
public class CityWithData extends City {
	
	private int humidity;
	private int visibility;
	
	/**
	 * Costruttore parametrizzato
	 * 
	 * @param name nome della città
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param id id della città
	 * @param humidity umidità della città
	 * @param visibility visibilità della città
	 */
	public CityWithData(String name, double lat, double lon, int id, int humidity, int visibility) {
		super(name, lat, lon, id);
		this.humidity = humidity;
		this.visibility = visibility;
	}

	/**
	 * Metodo che restituisce l'umidità della città
	 * 
	 * @return umidità della città
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * Metodo che imposta l'umidità della città
	 * 
	 * @param humidity umidità della città
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	 * Metodo che restituisce la visibilità della città
	 * 
	 * @return visibilità della città
	 */
	public int getVisibility() {
		return visibility;
	}

	/**
	 * Metodo che imposta la visibilità della città
	 * 
	 * @param visibility visibilità della città
	 */
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
	/**
	 * Implementazione del metodo compareTo() appartenente all'interfaccia Comparable
	 * 
	 * Nota: per quanto riguarda questa classe, tale implementazione non sarà mai utilizzata, ma 
	 * serve a prevenire segnalazioni di errore del compilatore
	 */
	public int compareTo(Object o) {
		return 0;
	}
}
