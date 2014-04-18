package com.varmarken.rejseplanen.afgangstavlen.network;

import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

/**
 * Class used to perform an HTTP GET request. Uses an implementation of
 * {@link IInputStreamParser} to parse the contents of the response.
 * 
 * @author Janus Varmarken
 * 
 * @param <RESULT>
 *            The data type for the the result of the web request (if no errors
 *            occur).
 */
public class HttpWebClient<RESULT> extends AsyncTask<Void, Void, RESULT> {

	private final IWebClientCallback<RESULT> callback;
	private final IInputStreamParser<RESULT> parser;
	private final URL requestURL;

	private Exception error = null;

	/**
	 * Creates an {@link HttpWebClient}.
	 * 
	 * @param requestURL
	 *            The URL the request is targeted at.
	 * @param streamParser
	 *            A parser that will parse the response contents into the
	 *            expected result type.
	 * @param callback
	 *            A callback implementation allowing the {@link HttpWebClient}
	 *            to deliver progress updates.
	 */
	public HttpWebClient(URL requestURL,
			IInputStreamParser<RESULT> streamParser,
			IWebClientCallback<RESULT> callback) {
		if (requestURL == null) {
			throw new NullPointerException(
					"requestURL argument may not be null.");
		}
		this.requestURL = requestURL;
		if (streamParser == null) {
			throw new NullPointerException(
					"streamParser argument may not be null.");
		}
		this.parser = streamParser;
		if (callback == null) {
			throw new NullPointerException("callback argument may not be null.");
		}
		this.callback = callback;
	}

	@Override
	protected RESULT doInBackground(Void... params) {
		try {
			HttpURLConnection conn = (HttpURLConnection) this.requestURL
					.openConnection();
			conn.setRequestMethod("GET");
			RESULT result = parser.parseStream(conn.getInputStream());
			return result;
		} catch (Exception ioe) {
			this.error = ioe;
		}
		return null;
	}

	@Override
	protected void onPostExecute(RESULT result) {
		if (this.error == null) {
			this.callback.onSuccess(result);
		} else {
			this.callback.onFailure(this.error);
		}
	}
}
