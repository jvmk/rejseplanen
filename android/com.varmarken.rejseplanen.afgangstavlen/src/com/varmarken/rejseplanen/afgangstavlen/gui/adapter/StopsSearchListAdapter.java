package com.varmarken.rejseplanen.afgangstavlen.gui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.varmarken.rejseplanen.afgangstavlen.R;
import com.varmarken.rejseplanen.afgangstavlen.model.Stop;

/**
 * <b>Work in progress:</b> A simple adapter for displaying a set of
 * {@link Stop}s in a {@link ListView}. This adapter is to be extended with a
 * better looking layout and more information for each item.
 * 
 * 
 * @author Janus Varmarken
 * 
 */
public class StopsSearchListAdapter extends BaseAdapter {

	/**
	 * The context of this adapter.
	 */
	private Context ctx;

	/**
	 * The stops to be displayed in the view associated with this adapter.
	 */
	private List<Stop> stops;

	/**
	 * Create a new {@link StopsSearchListAdapter}.
	 * 
	 * @param ctx
	 *            The context of this adapter.
	 * @param stops
	 *            The {@link Stop}s to be displayed by the view associated with
	 *            this adapter.
	 */
	public StopsSearchListAdapter(Context ctx, List<Stop> stops) {
		super();
		this.ctx = ctx;
		this.stops = stops;
	}

	@Override
	public int getCount() {
		return this.stops.size();
	}

	@Override
	public Object getItem(int position) {
		return this.stops.get(position);
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong(this.stops.get(position).getId());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Reuse any old view if possible.
		View result = convertView;
		if (result == null) {
			// No old view, create new.
			LayoutInflater li = (LayoutInflater) this.ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			result = li.inflate(R.layout.stops_search_item, null);
		}
		Stop item = stops.get(position);
		TextView lbl_stop_name = (TextView) result
				.findViewById(R.id.lbl_stop_name);
		lbl_stop_name.setText("Navn: " + item.getName());
		return result;
	}
}
