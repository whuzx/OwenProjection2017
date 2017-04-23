package com.MyAndroidCollection.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.MyAndroidCollection.view.BaseActivity;

public class ActivitysDemo1 extends BaseActivity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		setNumber(1);
		
		super.onCreate(savedInstanceState);

		
		getButtons().get(0).setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ActivitysDemo1.this, ActivitysDemo.class);
				Bundle bundle = new Bundle();
				bundle.putString("title", "Example");
				intent.putExtras(bundle);
				startActivity(intent);
				ActivitysDemo1.this.finish();
				setTitle("activitysDemo");
			}
		});
	}
}
