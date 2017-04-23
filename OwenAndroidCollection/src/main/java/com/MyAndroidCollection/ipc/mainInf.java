package com.MyAndroidCollection.ipc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.MyAndroidCollection.R;

public class mainInf extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mainpreference);
		
		SharedPreferences preferences=testSharePreference.CreateSharedPreferences(this, Context.MODE_MULTI_PROCESS);
		preferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
					String key) {
				// TODO Auto-generated method stub
				Log.d("[owen]", "key value" + key);
				
				Log.d("[owen]", "sharedPreferences" + sharedPreferences.getInt("writeTimes", 0));
			}
		});
		
		
		Button bWrite = (Button) findViewById(R.id.bWriteMain1);
		bWrite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Common.writeTofile(mainInf.this);
			}
		});
		Button b = (Button) findViewById(R.id.bReadSer1);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(mainInf.this, MyService.class));
			}
		});
		

	}
	

}
