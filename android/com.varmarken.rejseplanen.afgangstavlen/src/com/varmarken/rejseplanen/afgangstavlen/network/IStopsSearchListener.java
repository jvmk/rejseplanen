package com.varmarken.rejseplanen.afgangstavlen.network;

import java.util.List;

import com.varmarken.rejseplanen.afgangstavlen.model.Stop;

import android.app.Activity;

/**
 * Interface used by an {@link Activity} to listen for {@link Stop} search
 * results
 * 
 * @author Janus Varmarken
 * 
 */
public interface IStopsSearchListener {
	
	void onSearchSuccess(List<Stop> stops);
	
	void onSearchError();
}
