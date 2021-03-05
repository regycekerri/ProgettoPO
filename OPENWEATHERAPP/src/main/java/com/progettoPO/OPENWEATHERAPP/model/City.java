package com.progettoPO.OPENWEATHERAPP.model;

/**
 * <p>
 * <b>Classe</b> caratterizzata dagli attributi fondamentali che definiscono ogni città
 * [nome (name), latitudine (lat), longitudine (lon), id(city_id)]
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
public class City {
	
	private String name;
	private double lat;
	private double lon;
	private int city_id;
	
	/**
	 * Costruttore parametrizzato
	 * 
	 * @param name nome della città
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param city_id id della città
	 */
	public City(String name, double lat, double lon, int city_id) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.city_id = city_id;
	}
	
	/**
	 * Metodo che restituisce il nome della città
	 * 
	 * @return nome della città
	 */
    public String getName() {
		return name;
	}

	/**
	 * Metodo che imposta il nome della città
	 * 
	 * @param name nome della città
	 */
    public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metodo che restituisce la latitudine della città
	 * 
	 * @return latitudine della città
	 */
    public double getLat() {
		return lat;
	}

	/**
	 * Metodo che imposta la latitudine della città
	 * 
	 * @param lat latitudine della città
	 */
    public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Metodo che restituisce la longitudine della città
	 * 
	 * @return longitudine della città
	 */
    public double getLon() {
		return lon;
	}

	/**
	 * Metodo che imposta la longitudine della città
	 * 
	 * @param lon longitudine della città
	 */
    public void setLon(double lon) {
		this.lon = lon;
	}

    /**
     * Metodo che restituisce l'id della città
     * 
     * @return id della città
     */
	public int getCity_id() {
		return city_id;
	}

	/**
	 * Metodo che imposta l'id della città
	 * 
	 * @param city_id
	 */
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
}
