package com.varmarken.rejseplanen.afgangstavlen.model;

/**
 * Class modeling a departure from a bus stop or train station.
 * 
 * @author Janus Varmarken
 * 
 */
public class Departure {

	/**
	 * The bus stop or train station associated with this departure.
	 */
	private final Stop stop;

	/**
	 * Name of the departure (i.e. the name of the bus line or train number).
	 */
	private final String name;

	/**
	 * In what direction the journey, that commences with this departure, is
	 * heading. In most cases this is equal to {@link #finalStop}.
	 */
	private final String direction;

	/**
	 * The last stop of the journey that commences with this departure. In most
	 * cases this is equal to {@link #direction}.
	 */
	private String finalStop;
	
	public Departure(Stop stop, String name, String direction, String finalStop) {
		this.stop = stop;
		this.name = name;
		this.direction = direction;
		this.finalStop = finalStop;
	}
}
