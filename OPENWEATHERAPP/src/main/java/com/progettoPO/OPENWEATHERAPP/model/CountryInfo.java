package com.progettoPO.OPENWEATHERAPP.model;

/**
 * <p>
 * <b>Classe</b> che definisce le informazioni principali di uno stato [nome dello stato (country_name),
 * nome della capitale (capital_name), latitudine della capitale (capital_lat), longitudine della capitale (capital_lon)] 
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
public class CountryInfo {
	private String country_name;
	private String capital_name;
	private double capital_lat;
	private double capital_lon;
	
	/**
	 * Costruttore di default
	 */
	public CountryInfo() {
		country_name = null;
		capital_name = null;
		capital_lat = 0.0;
		capital_lon = 0.0;
	}
	
	/**
	 * Costruttore parametrizzato
	 * 
	 * @param country_name nome dello stato
	 * @param capital_name nome della capitale
	 * @param capital_lat latitudine della capitale
	 * @param capital_lon longitudine della capitale
	 */
	public CountryInfo(String country_name, String capital_name, double capital_lat, double capital_lon) {
		this.country_name = country_name;
		this.capital_name = capital_name;
		this.capital_lat = capital_lat;
		this.capital_lon = capital_lon;
	}
	
	/**
	 * Metodo che restituisce il nome dello stato
	 * 
	 * @return nome dello stato
	 */
	public String getCountry_name() {
		return country_name;
	}
	
	/**
	 * Metodo che imposta il nome dello stato
	 * 
	 * @param country_name nome dello stato
	 */
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	/**
	 * Metodo che restituisce il nome della capitale
	 * 
	 * @return nome della capitale
	 */
	public String getCapital_name() {
		return capital_name;
	}
	
	/**
	 * Metodo che imposta il nome della capitale
	 * 
	 * @param capital_name nome della capitale
	 */
	public void setCapital_name(String capital_name) {
		this.capital_name = capital_name;
	}
	
	/**
	 * Metodo che restituisce la latitudine della capitale
	 * 
	 * @return latitudine della capitale
	 */
	public double getCapital_lat() {
		return capital_lat;
	}
	
	/**
	 * Metodo che imposta la latitudine della capitale
	 * 
	 * @param capital_lat latitudine della capitale
	 */
	public void setCapital_lat(double capital_lat) {
		this.capital_lat = capital_lat;
	}
	
	/**
	 * Metodo che restituisce la longitudine della capitale
	 * 
	 * @return longitudine della capitale
	 */
	public double getCapital_lon() {
		return capital_lon;
	}
	
	/**
	 * Metodo che imposta la longitudine della capitale
	 * 
	 * @param capital_lon longitudine della capitale
	 */
	public void setCapital_lon(double capital_lon) {
		this.capital_lon = capital_lon;
	}
}
