package com.MyAndroidCollection.App.Fragment;

import android.app.Activity;
import android.os.Bundle;


import com.MyAndroidCollection.R;



public class MainFragment1 extends Activity  implements HwIDListener{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_articles);

		// Check that the activity is using the layout version with
		// the fragment_container FrameLayout
		if (findViewById(R.id.fragment_container) != null) {

			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null) {
				return;
			}

			// Create an instance of ExampleFragment
			Fragment1 fragment1 = new Fragment1();

			// In case this activity was started with special instructions from
			// an Intent,
			// pass the Intent's extras to the fragment as arguments
			fragment1.setArguments(getIntent().getExtras());

			// Add the fragment to the 'fragment_container' FrameLayout
			//getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1).commit();
			//getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1)
			
			getFragmentManager().beginTransaction().addToBackStack(null);
			getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1).commit();
		
			
		}
	}

	@Override
	public void onEnterPassword(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFindPassword(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOK() {
		// TODO Auto-generated method stub
		
	}

}
