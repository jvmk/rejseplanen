package com.varmarken.rejseplanen.afgangstavlen.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class modeling a stop (both bus stops and train stations).
 * 
 * @author Janus Varmarken
 * 
 */
public class Stop {

	/**
	 * Name of the stop.
	 */
	private String name;

	/**
	 * x coordinate of the stop.
	 */
	private String x;

	/**
	 * y coordinate of the stop.
	 */
	private String y;

	/**
	 * ID used by Rejseplanen API to identify the stop.
	 */
	private String id;

	/**
	 * Constructor is private in order to only allow object creation using a
	 * {@link JSONObject}.
	 */
	private Stop(String name, String x, String y, String id) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * Create a new {@link Stop} instance from the contents of a
	 * {@link JSONObject}.
	 * 
	 * @param jsonObj
	 *            A {@link JSONObject} matching the structure of a JSON Stop
	 *            object as per Rejseplanen's definition.
	 * @return The parsed Stop instance.
	 * @throws JSONException
	 *             If {@code jsonObj} does not match the expected structure of a
	 *             Stop JSON object.
	 */
	public static Stop createFromJSON(JSONObject jsonObj) throws JSONException {
		String name = jsonObj.getString("name");
		String x = jsonObj.getString("x");
		String y = jsonObj.getString("y");
		String id = jsonObj.getString("id");
		return new Stop(name, x, y, id);
	}

	/**
	 * Get the name of this stop.
	 * 
	 * @return The name of this stop.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the x coordinate of this stop.
	 * 
	 * @return The x coordinate of this stop.
	 */
	public String getX() {
		return x;
	}

	/**
	 * Get the y coordinate of this stop.
	 * 
	 * @return The y coordinate of this stop.
	 */
	public String getY() {
		return y;
	}

	/**
	 * Get the ID of this stop. This is Rejseplanen's unique ID for this stop.
	 * 
	 * @return The ID of this stop.
	 */
	public String getId() {
		return id;
	}

}
