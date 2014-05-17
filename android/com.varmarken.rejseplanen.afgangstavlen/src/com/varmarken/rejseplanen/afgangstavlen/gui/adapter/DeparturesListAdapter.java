package com.varmarken.rejseplanen.afgangstavlen.gui.adapter;

import java.util.List;

import com.varmarken.rejseplanen.afgangstavlen.model.Departure;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DeparturesListAdapter extends BaseAdapter {

	private List<Departure> dataSet;
	
	@Override
	public int getCount() {
		return dataSet.size();
	}

	@Override
	public Object getItem(int position) {
		return this.dataSet.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
