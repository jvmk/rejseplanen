package com.varmarken.rejseplanen.afgangstavlen;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import android.os.Bundle;
import android.util.Log;

import com.varmarken.rejseplanen.afgangstavlen.model.Stop;
import com.varmarken.rejseplanen.afgangstavlen.network.HttpWebClient;
import com.varmarken.rejseplanen.afgangstavlen.network.IStopsSearchListener;
import com.varmarken.rejseplanen.afgangstavlen.network.IWebClientCallback;
import com.varmarken.rejseplanen.afgangstavlen.network.LocationJSONResponseParser;
import com.varmarken.rejseplanen.afgangstavlen.util.AssetsPropertiesReader;

public class StopsSearchWorkerFragment extends WorkerFragment<IStopsSearchListener> implements IWebClientCallback<List<Stop>> {
	
	private HttpWebClient<List<Stop>> httpClient;
	private String rejseplanenApiBaseURL;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Load the base URL.
		/*
		 *	This is only done once as onCreate is only run once as the fragment is retained.
		 */
		Properties props = AssetsPropertiesReader.loadProperties(this.getActivity(), "rejseplanen_webservices.properties");
		rejseplanenApiBaseURL = props.getProperty("base_url");
	}
	
	public void doSearch(String searchString) {
		if(this.httpClient != null) {
			/*
			 *	Unregister from any previous web client.
			 *  This is to avoid callbacks from previous tasks.
			 *  We are no longer interested in callbacks from previous tasks when we start a new.
			 */
			this.httpClient.unregisterCallback();
		}
		// Go to location REST service.
		String urlStr = rejseplanenApiBaseURL + "/location?input=";
		// Append query.
		String encoding = "UTF-8";
		try {
			urlStr = urlStr + URLEncoder.encode(searchString, encoding) + "&format=json";
			URL url = new URL(urlStr);
			this.httpClient = new HttpWebClient<List<Stop>>(url, new LocationJSONResponseParser(), this);
			this.httpClient.execute();
		} catch (UnsupportedEncodingException e) {
			Log.e(this.getClass().getSimpleName(), "Encoding not supported: " + encoding);
		} catch (MalformedURLException e) {
			Log.e(this.getClass().getSimpleName(), "Malformed URL: " + urlStr);
		}
	}

	@Override
	public void onSuccess(List<Stop> resultData) {
		if(this.callback != null) {
			this.callback.onSearchSuccess(resultData);
		}
	}
	@Override
	public void onFailure(Exception errorData) {
		// TODO pass on exception?
		if(this.callback != null) {			
			this.callback.onSearchError();
		}
	}
}
