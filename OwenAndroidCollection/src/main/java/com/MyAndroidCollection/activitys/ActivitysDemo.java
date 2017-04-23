package com.MyAndroidCollection.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MyAndroidCollection.util.NetWorkUtil;

public class ActivitysDemo extends Activity {

	private Button btn1 =null;
	private TextView displayTextView=null;
	private TextView countTextView=null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initResource(this);
	//	setContentView(R.layout.activitysdemo);
		Bundle bundle = this.getIntent().getExtras();
		String str = bundle.getString("title");
		if (str != null) {
			// 设置输出文字
			displayTextView.setText("we get info:" + "title:" + str);
		}

		SharedPreferences mPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		int counter = mPreferences.getInt("counter", 0);
		countTextView.setText("This app has been started: " + counter
				+ " times" + "  ip:" + NetWorkUtil.getLocalIpAddress());
		SharedPreferences.Editor mEditor = mPreferences.edit();
		mEditor.putInt("counter", ++counter);
		mEditor.commit();

		btn1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(ActivitysDemo.this, ActivitysDemo1.class);
				startActivity(intent);

				ActivitysDemo.this.finish();
				setTitle("ActivitysDemo1");

			}
		});

	}

	private void initResource(Context context) {
		LinearLayout linearLayout=new LinearLayout(context);
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
	        
	     btn1 =new Button(this);
  
	     displayTextView= new TextView(this);
	     displayTextView.setTextColor(Color.WHITE);
	     
	     countTextView= new TextView(this);
	     countTextView.setTextColor(Color.WHITE);
	     
	     linearLayout.addView(btn1);
	     linearLayout.addView(displayTextView);
	     linearLayout.addView(countTextView);
	     
	     
	     setContentView(linearLayout);
		
	}

}