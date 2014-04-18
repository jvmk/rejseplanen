package com.varmarken.rejseplanen.afgangstavlen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

/**
 * Utility class to load a properties file from the assets folder into memory.
 * Heavily inspired by:
 * http://khurramitdeveloper.blogspot.dk/2013/07/properties-file-in-android.html
 * 
 * @author Janus Varmarken
 * 
 */
public class AssetsPropertiesReader {

	/**
	 * Loads a properties file into memory.
	 * 
	 * @param ctx
	 *            The context to retrieve assets from.
	 * @param fileName
	 *            The name of the properties file. This can be hierarchical.
	 * @return A {@link Properties} instance matching the properties file or
	 *         null if an error occurs when attempting to access the properties
	 *         file.
	 */
	public static Properties loadProperties(Context ctx, String fileName) {
		AssetManager am = ctx.getAssets();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					am.open(fileName)));
			Properties result = new Properties();
			result.load(reader);
			return result;
		} catch (IOException e) {
			Log.e(AssetsPropertiesReader.class.getName(), e.getMessage());
		}
		return null;
	}

}
