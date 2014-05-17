package com.varmarken.rejseplanen.afgangstavlen.gui;

import com.varmarken.rejseplanen.afgangstavlen.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A fragment that displays departures for a bus stop or train station.
 * 
 * @author Janus Varmarken
 * 
 */
public class DeparturesFragment extends Fragment {

	/**
	 * Key used in the {@link Bundle} passed to the {@link DeparturesFragment}
	 * to define what bus stop or train station the fragment should display
	 * departures for.
	 */
	private static final String EXTRA_STOP_ID = "EXTRA_STOP_ID";

	private String stopId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			// Get the stop id if fragment is being restored.
			this.stopId = savedInstanceState.getString(EXTRA_STOP_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_departures, container,
				false);
		ListView lv_departures = (ListView) v.findViewById(R.id.lv_departures);
		// TODO create list adapter
		// lv_departures.setAdapter(adapter);
		// TextView tv = (TextView) v.findViewById(R.id.lblStopId);
		// tv.setText(this.stopId);
		return v;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(EXTRA_STOP_ID, this.stopId);
	}

	/**
	 * Create a new {@link DeparturesFragment} that is to display departures for
	 * the given bus stop or train station ID.
	 * 
	 * @param stopId
	 *            The ID of the bus stop or train station that this fragment is
	 *            to display departures for.
	 * @return A new {@link DeparturesFragment} associated with the given
	 *         {@code stopId}.
	 */
	public static DeparturesFragment newInstance(String stopId) {
		DeparturesFragment df = new DeparturesFragment();
		df.stopId = stopId;
		return df;
	}
}
