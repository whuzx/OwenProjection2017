package com.MyAndroidCollection;



import com.MyAndroidCollection.util.LogX;

import android.app.Application;
import android.preference.PreferenceManager;

public class MyAndroidCollectionApplication  extends Application {
	 @Override
	    public void onCreate() {
	        /*
	         * This populates the default values from the preferences XML file. See
	         * {@link DefaultValues} for more details.
	         */
	        PreferenceManager.setDefaultValues(this, R.xml.default_values, false);
	        
	        LogX.init(this);
	    }

	    @Override
	    public void onTerminate() {
	    }

}
