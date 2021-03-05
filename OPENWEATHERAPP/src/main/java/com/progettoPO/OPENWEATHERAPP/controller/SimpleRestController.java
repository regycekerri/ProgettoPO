package com.progettoPO.OPENWEATHERAPP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.progettoPO.OPENWEATHERAPP.exception.AlreadyExistingHistoryException;
import com.progettoPO.OPENWEATHERAPP.exception.CountryNotSupportedException;
import com.progettoPO.OPENWEATHERAPP.exception.NotExistingHistoryException;
import com.progettoPO.OPENWEATHERAPP.service.History;
import com.progettoPO.OPENWEATHERAPP.service.ListOfCountries;
import com.progettoPO.OPENWEATHERAPP.service.WeatherServiceImpl;

/**
 * <p>
 * <b>Controller</b>, ossia la classe che gestisce le varie rotte richiamabili
 * dall'utente per usufruire delle funzionalità messe a disposizione dall'applicativo
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
@RestController
public class SimpleRestController {
	
	@Autowired
	ListOfCountries list = new ListOfCountries();
	
	@Autowired
	History history = new History();
	
	@Autowired
	WeatherServiceImpl service;
	
	/** 
	 * La seguente rotta restituisce all'utente la lista degli stati supportati dall'applicativo
	 */
	@RequestMapping(value="/countries", method = RequestMethod.GET)
	public ResponseEntity<Object> getList(){
		return new ResponseEntity<>(list.visualizeList(), HttpStatus.OK);
	}
	
	/**
	 * La seguente rotta riceve come parametri il nome dello stato (country) e un numero di città (cnt); restituisce la visibilità
	 * e l'umidità attuali delle cnt città limitrofe alla capitale (compresa nelle cnt città)
	 * 
	 * @param country nome dello stato
	 * @param cnt numero di città limitrofe
	 * @return lista delle città, ciascuna con le corrispettive umidità e visibilità attuali
	 * @throws CountryNotSupportedException nel caso in cui lo stato ricevuto come parametro non sia supportato
	 */
	@RequestMapping(value="/actualdata", method = RequestMethod.GET)
	public ResponseEntity<Object> actualData(@RequestParam(name = "country") String country, @RequestParam(name = "cnt") int cnt) throws CountryNotSupportedException{
		try {
			if(list.countrySupported(country)) {
				int index = list.indexOfCountry(country);
				double lat = list.countries[index].getCapital_lat();
				double lon = list.countries[index].getCapital_lon();
				return new ResponseEntity<>(service.actualDataService(lat, lon, cnt), HttpStatus.OK);
			}else
				throw new CountryNotSupportedException("This country is not supported");
			
		}catch (CountryNotSupportedException c) {
			return new ResponseEntity<>(c.getMsg(), HttpStatus.BAD_REQUEST);
			
		}catch (IllegalArgumentException i)	{
			return new ResponseEntity<>(i.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
	}
	
	/**
	 * La seguente rotta restituisce all'utente la lista degli stati dei quali si hanno attualmente
	 * degli archivi storici
	 */
	@RequestMapping(value="/history", method = RequestMethod.GET)
	public ResponseEntity<Object> getCountriesWithHistory(){
		return new ResponseEntity<>(history.getCountriesWithHistory(), HttpStatus.OK);		
	}
	
	/**
	 * La seguente rotta permette all'utente di creare l'archivio storico di uno degli stati supportati
	 * dall'applicativo, purchè non lo possieda già
	 * 
	 * @param country stato di cui si vuole creare l'archivio
	 * @throws CountryNotSupportedException se lo stato non è supportato
	 * @throws AlreadyExistingHistoryException se esiste già l'archivio per lo stato
	 */
	@RequestMapping(value="/history/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createHistory(@RequestBody String country) throws CountryNotSupportedException, AlreadyExistingHistoryException{
		try {
			if(list.countrySupported(country)) {
				if(history.containsCountry(country)) {
					throw new AlreadyExistingHistoryException("History for this country is already available");
				}else {
					int index = list.indexOfCountry(country);
					double lat = list.countries[index].getCapital_lat();
					double lon = list.countries[index].getCapital_lon();
					history.createHistory(country, lat, lon);
					return new ResponseEntity<>("History for this country has been created!", HttpStatus.OK);
				}
			}else
				throw new CountryNotSupportedException("This country is not supported");
			
		}catch (CountryNotSupportedException c) {
			return new ResponseEntity<>(c.getMsg(), HttpStatus.BAD_REQUEST);
			
		}catch (AlreadyExistingHistoryException a) {
			return new ResponseEntity<>(a.getMsg(), HttpStatus.BAD_REQUEST);
			
		}
	}
	
	/**
	 * La seguente rotta permette all'utente di cancellare l'archivio storico di uno degli stati per 
	 * il quale lo si possieda
	 * 
	 * @param country stato di cui si vuole cancellare l'archivio
	 * @throws NotExistingHistoryException se lo stato non possiede un archivio
	 */
	@RequestMapping(value="/history/remove", method = RequestMethod.POST)
	public ResponseEntity<Object> removeHistory(@RequestBody String country) throws NotExistingHistoryException{
		try {
			if(history.containsCountry(country)) {
				history.removeHistory(country);
				return new ResponseEntity<>("History for this country has been removed!", HttpStatus.OK);
			}else {
				throw new NotExistingHistoryException("History for this country doesn't exist!");
			}
			
		}catch (NotExistingHistoryException n) {
			return new ResponseEntity<>(n.getMsg(), HttpStatus.BAD_REQUEST);
		}
	}
}
