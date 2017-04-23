package com.MyAndroidCollection.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityLaunchMode3 extends Activity {
	
	 @Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		TextView textView =new TextView(this);
		textView.setText(this+"");
		
		Button clickBtnButton=new Button(this);
		clickBtnButton.setText("go ActivityLaunchMode Activity "+"task id" +getTaskId());
		clickBtnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent =new Intent();
				intent.setClass(ActivityLaunchMode3.this, ActivityLaunchMode4.class);
				//use  singleTop
				//intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				
				//use  singleTask
				//intent.setFlags(Intent.F);
				
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				startActivity(intent);
			}
		});
		
		
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(textView);
		layout.addView(clickBtnButton);
		setContentView(layout);
	}

}
