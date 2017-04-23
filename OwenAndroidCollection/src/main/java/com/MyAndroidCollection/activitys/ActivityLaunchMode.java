package com.MyAndroidCollection.activitys;

/**
 * http://www.open-open.com/lib/view/open13417337330.html
 * http://geyubin.iteye.com/blog/1128792
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MyAndroidCollection.ipc.testSharePreference;

public class ActivityLaunchMode extends Activity {
	
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
				intent.setClass(ActivityLaunchMode.this, ActivityLaunchMode1.class);
				//use  singleTop
				//intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				
				//use  singleTask
				//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				//intent.setFlags(Intent.F)

				
				startActivity(intent);
				
				
			}
		});
		
		
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(textView);
		layout.addView(clickBtnButton);
		setContentView(layout);
		
		testSharePreference.inputValueToSharePreference(this, "id", "z0020101");
		
		getDeviceType(this);
	}

	 
	public static String getDeviceType(Context context) {

		int sDeviceType ;
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		sDeviceType = tm.getPhoneType();

		Log.v("owen", "deviceType= " + sDeviceType);
		if (TelephonyManager.PHONE_TYPE_CDMA == sDeviceType) {
			return "2";
		} else {
			return "0";
		}
	}
}
