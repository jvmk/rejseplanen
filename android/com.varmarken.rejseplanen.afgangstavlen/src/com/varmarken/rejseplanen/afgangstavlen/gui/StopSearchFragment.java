package com.varmarken.rejseplanen.afgangstavlen.gui;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.varmarken.rejseplanen.afgangstavlen.R;

public class StopSearchFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_stop_search, container, false);
		SearchView sw = (SearchView) v.findViewById(R.id.sw_stop_search);
		SearchManager sm = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
		sw.setSearchableInfo(sm.getSearchableInfo(getActivity().getComponentName()));
		return v;
	}
}
