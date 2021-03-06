package com.progettoPO.OPENWEATHERAPP.model;

import java.util.Comparator;

/**
 * <p>
 * <b>Classe</b> contenente gli indici statistici (per un determinato parametro metereologico) di una 
 * qualsiasi città [valore minimo (min), valore massimo (max), valore medio (avg), varianza (var)],
 * oltre ad alcuni attributi di base ereditati dalla superclasse City
 * </p>
 * 
 * @author Cekerri Regy
 * @author Vigliotta Michele
 */
public class CityStats extends City {
	
	private String par;
	
	private int min;
	private int max;
	private double avg;
	private double var;
	
	/**
	 * Costruttore parametrizzato
	 * 
	 * @param name
	 * @param lat
	 * @param lon
	 * @param par
	 * @param min
	 * @param max
	 * @param avg
	 * @param var
	 */
	public CityStats(String name, double lat, double lon, String par, int min, int max, double avg, double var) {
		super(name, lat, lon, 0);
		this.par = par;
		this.min = min;
		this.max = max;
		this.avg = avg;
		this.var = var;
	}
	
	/**
	 * Metodo che restituisce il parametro statistico
	 * 
	 * @return parametro statistico
	 */
	public String getPar() {
		return par;
	}
	
	/**
	 * Metodo che imposta il parametro statistico
	 * 
	 * @param par parametro statistico
	 */
	public void setPar(String par) {
		this.par = par;
	}
	
	/**
	 * Metodo che restituisce il valore minimo del parametro
	 * 
	 * @return valore minimo del parametro
	 */
	public int getMin() {
		return min;
	}
	
	/**
	 * Metodo che imposta il valore minimo del parametro
	 * 
	 * @param min valore minimo del parametro
	 */
	public void setMin(int min) {
		this.min = min;
	}
	
	/**
	 * Metodo che restituisce il valore massimo del parametro
	 * 
	 * @return valore massimo del parametro
	 */
	public int getMax() {
		return max;
	}
	
	/**
	 * Metodo che imposta il valore massimo del parametro
	 * 
	 * @param max valore massimo del parametro
	 */
	public void setMax(int max) {
		this.max = max;
	}
	
	/**
	 * Metodo che restituisce il valore medio del parametro
	 * 
	 * @return valore medio del parametro
	 */
	public double getAvg() {
		return avg;
	}
	
	/**
	 * Metodo che imposta il valore medio del parametro
	 * 
	 * @param avg valore medio del parametro
	 */
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	/**
	 * Metodo che restituisce la varianza del parametro
	 * 
	 * @return varianza del parametro
	 */
	public double getVar() {
		return var;
	}
	
	/**
	 * Metodo che imposta la varianza del parametro
	 * 
	 * @param var varianza del parametro
	 */
	public void setVar(double var) {
		this.var = var;
	}
	
	/**
	 * Comparatore che permette di confrontare due città in base al valore minimo del parametro
	 */
	public static Comparator<CityStats> MinComparator = new Comparator<CityStats>() {
		
		public int compare(CityStats c1, CityStats c2) {
			int min1 = c1.getMin();
			int min2 = c2.getMin();
			
			return min1 - min2;
		}
	};
	
	/**
	 * Comparatore che permette di confrontare due città in base al valore massimo del parametro
	 */
	public static Comparator<CityStats> MaxComparator = new Comparator<CityStats>() {
		
		public int compare(CityStats c1, CityStats c2) {
			int max1 = c1.getMax();
			int max2 = c2.getMax();
			
			return max1 - max2;
		}
	};
	
	/**
	 * Comparatore che permette di confrontare due città in base al valore medio del parametro
	 */
	public static Comparator<CityStats> AvgComparator = new Comparator<CityStats>() {
		
		public int compare(CityStats c1, CityStats c2) {
			double avg1 = c1.getAvg();
			double avg2 = c2.getAvg();
			
			if(avg1>avg2) {
				return 1;
			}else if (avg1<avg2) {
				return -1;
			}else {
				return 0;
			}
		}
	};
	
	/**
	 * Comparatore che permette di confrontare due città in base alla varianza del parametro
	 */
	public static Comparator<CityStats> VarComparator = new Comparator<CityStats>() {
		
		public int compare(CityStats c1, CityStats c2) {
			double var1 = c1.getVar();
			double var2 = c2.getVar();
			
			if(var1>var2) {
				return 1;
			}else if(var1<var2) {
				return -1;
			}else {
				return 0;
			}
		}
	};
}
