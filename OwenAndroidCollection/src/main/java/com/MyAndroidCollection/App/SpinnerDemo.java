package com.MyAndroidCollection.App;



import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.MyAndroidCollection.R;

public class SpinnerDemo extends Activity{

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		Spinner spinner=(Spinner)findViewById(R.id.spinner);
		ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.planets, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	
	}
	

}
