package com.varmarken.rejseplanen.afgangstavlen.webclient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.varmarken.rejseplanen.afgangstavlen.model.Stop;

/**
 * An implementation of {@link IInputStreamParser} that attempts to parse the
 * contents of an {@link InputStream} into a list of {@link Stop}s. A Location
 * query does not only contain stops but also other locations, e.g. points of
 * interest, but these are filtered out as part of the parsing done by this
 * class. This parser assumes that the content of the input stream is JSON
 * formatted.
 * 
 * @author Janus Varmarken
 * 
 */
public class LocationJSONResponseParser implements
		IInputStreamParser<List<Stop>> {

	private static final int bufferSize = 65000;

	/**
	 * Parses an {@link InputStream} attempting to convert its contents to a
	 * list of {@link Stop} instances. This implementation assumes that the
	 * content of the input stream is JSON formatted.
	 */
	@Override
	public List<Stop> parseStream(InputStream stream) throws IOException,
			JSONException {
		StringBuilder sb = new StringBuilder();
		BufferedInputStream bis = new BufferedInputStream(stream);

		byte[] buf = new byte[bufferSize];
		int read = 0;
		while ((read = bis.read(buf)) != -1) {
			sb.append(new String(buf, 0, read));
			buf = new byte[bufferSize];
		}
		return this.parseString(sb.toString());
	}

	/**
	 * Parses the JSON response into a list of {@link Stop} instances.
	 * 
	 * @param response
	 *            The JSON response (read from the input stream).
	 * @return A list of {@link Stop}s contained in the response data.
	 * @throws JSONException
	 *             If {@code response} cannot be parsed (e.g. if the JSON
	 *             structure of {@code response} is not as expected).
	 */
	private List<Stop> parseString(String response) throws JSONException {
		JSONObject outer = new JSONObject(response);
		// First get the "LocationList" object
		JSONObject locationList = outer.getJSONObject("LocationList");
		// Now get its inner "StopLocation" array.
		JSONArray stopLocations = locationList.getJSONArray("StopLocation");
		List<Stop> result = new ArrayList<Stop>();
		for (int i = 0; i < stopLocations.length(); i++) {
			JSONObject currentObj = stopLocations.getJSONObject(i);
			Stop stop = Stop.createFromJSON(currentObj);
			result.add(stop);
		}
		return result;
	}
}
