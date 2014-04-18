package com.varmarken.rejseplanen.afgangstavlen.network;

/**
 * Interface providing callback methods that a class performing a web request
 * may use to communicate progress to its caller in an async way.
 * 
 * @author Janus Varmarken
 * 
 * @param <RESULT>
 *            The result type delivered when a web request completes with
 *            success.
 */
public interface IWebClientCallback<RESULT> {

	/**
	 * Invoked if the request completed successfully, delivering the result data
	 * for the request.
	 * 
	 * @param resultData
	 *            The result data for the request.
	 */
	void onSuccess(RESULT resultData);

	/**
	 * Invoked if the request has failed.
	 * 
	 * @param errorData
	 *            Information about the error.
	 */
	void onFailure(Exception errorData);
}
