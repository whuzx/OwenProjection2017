package com.MyAndroidCollection.activitys;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.MyAndroidCollection.R;


public class FloatSearchBoxDemo extends Activity {
	private final int SEARCH_MENU = 1;
	private final int ADD_MENU = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searcher);
		setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
		handleSearchQuery(getIntent());
	}


	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleSearchQuery(intent);
	}
	
	private void handleSearchQuery(Intent queryIntent) {
		final String queryAction = queryIntent.getAction();
		if (Intent.ACTION_SEARCH.equals(queryAction)) {
			onSearch(queryIntent);
		}
	}


	private void onSearch(Intent intent) {
		final String queryString = intent.getStringExtra(SearchManager.QUERY);
		Toast.makeText(this, queryString, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		menu.add(0, SEARCH_MENU, 0, getText(R.string.searchMenu)).setIcon(
				android.R.drawable.ic_menu_search);
		menu.add(0, ADD_MENU, 0, "ADD").setIcon(
				android.R.drawable.ic_input_add);
		return result;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case SEARCH_MENU:
			onSearchRequested();
			return true;
		case ADD_MENU:
			Toast.makeText(this, "TEST ADD", Toast.LENGTH_LONG).show();
			return true;
			
		}

		return super.onMenuItemSelected(featureId, item);
	}

}