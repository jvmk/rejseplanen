package com.varmarken.rejseplanen.afgangstavlen;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * A fragment without a UI that is set to retain its instance. This allows
 * background workers to perform callbacks to a new activity instance in case
 * the activity is recreated due to configuration change. This fragment
 * 
 * @param <CALLBACK>
 *            The type of the callback interface that workers running in this
 *            fragment use to report progress to the interested {@link Activity}
 *            . Any {@link Activity} that attaches an instance of this fragment
 *            type must implement this interface.
 * @author Janus Varmarken
 * 
 */
public abstract class WorkerFragment<CALLBACK> extends
		Fragment {

	/**
	 * The callback instance (an activity implementing a callback interface).
	 */
	protected CALLBACK callback;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.callback = (CALLBACK) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		this.callback = null;
	}
	
//	/**
//	 * Subclasses should use this to perform the time consuming task of the worker fragment.
//	 */
//	public abstract void doWork();
}
