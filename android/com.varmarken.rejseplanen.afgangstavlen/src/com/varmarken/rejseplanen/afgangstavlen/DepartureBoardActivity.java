package com.varmarken.rejseplanen.afgangstavlen;

import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.varmarken.rejseplanen.afgangstavlen.model.Stop;
import com.varmarken.rejseplanen.afgangstavlen.webclient.HttpWebClient;
import com.varmarken.rejseplanen.afgangstavlen.webclient.IWebClientCallback;
import com.varmarken.rejseplanen.afgangstavlen.webclient.LocationJSONResponseParser;

public class DepartureBoardActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * The base URL to the Rejseplanen's web API. Field is static as its value is to be loaded from a file.
	 * In order to boost performance, we do not want to reload it every time the activty is recreated.
	 */
	private static String rejseplanenApiBaseURL = null;
	
	private IWebClientCallback<List<Stop>> stopSearchCallback = new IWebClientCallback<List<Stop>>() {

		@Override
		public void onSuccess(List<Stop> resultData) {
			/*
			 * Is the list view present or did the user switch to display departures for a favorite stop?
			 */
			View lv_stops_found = DepartureBoardActivity.this.findViewById(R.id.lv_stops_found);
			// If present, we populate the list view.
			if(lv_stops_found != null) {
				ListView lv = (ListView) lv_stops_found;
				StopsSearchListAdapter adapter = new StopsSearchListAdapter(DepartureBoardActivity.this, resultData); 
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		}

		@Override
		public void onFailure(Exception errorData) {
			// TODO handle error.
		}
	};
	
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the base URL if it wasn't already loaded.
        if(rejseplanenApiBaseURL == null) {
        	Properties props = AssetsPropertiesReader.loadProperties(this, "rejseplanen_webservices.properties");
        	rejseplanenApiBaseURL = props.getProperty("base_url");
        }
        
        setContentView(R.layout.activity_departure_board);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	this.setIntent(intent);
    	this.handleIntent(intent);
    }
    
    private void handleIntent(Intent intent) {
    	if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
    		this.performSearch(intent.getStringExtra(SearchManager.QUERY));
    	}
    }
    
    private void performSearch(String searchString) {
//    	Toast.makeText(this, "Performing search...", Toast.LENGTH_SHORT).show();
    	// TODO test code - make dynamic + cleaner
    	String urlStr = rejseplanenApiBaseURL + "/location?input=";
    	try {
    		urlStr = urlStr + URLEncoder.encode(searchString, "UTF-8") + "&format=json";
        	// String result = locationServiceBase + "?input="
        	// + URLEncoder.encode(userInput, "UTF-8");
        	// return result;
        	URL url = new URL(urlStr);
        	HttpWebClient<List<Stop>> webClient = new HttpWebClient<>(url,
        			new LocationJSONResponseParser(),
        			this.stopSearchCallback);
        	webClient.execute();
    	} catch(Exception e) {
    		System.out.println("buhuu");
    	}
    }
    
    @Override
    public void onNavigationDrawerItemSelected(int position) {
    	FragmentManager fm = this.getFragmentManager();
    	fm.beginTransaction().replace(R.id.container, new StopSearchFragment()).commit();
    	
    	
// update the main content by replacing fragments
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.departure_board_search, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_departure_board, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((DepartureBoardActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
