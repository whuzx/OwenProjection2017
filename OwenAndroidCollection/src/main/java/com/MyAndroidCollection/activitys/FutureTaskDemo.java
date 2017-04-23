package com.MyAndroidCollection.activitys;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MyAndroidCollection.task.Future2Task;

public class FutureTaskDemo extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		final TextView textView =new TextView(this);
		textView.setText(this+"");
		
		Button clickBtnButton=new Button(this);
		clickBtnButton.setText("start task ");
		
		
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(textView);
		layout.addView(clickBtnButton);
		setContentView(layout);
		
		clickBtnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Intent intent =new Intent();
				//intent.setClass(ActivityLaunchMode.this, ActivityLaunchMode1.class);
				//use  singleTop
				//intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				
				//use  singleTask
				//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				//intent.setFlags(Intent.F)

				
				//startActivity(intent);
				
				Future2Task future2Task =new Future2Task(new Callable<Bundle>() {
					
					@Override
					public Bundle call() throws Exception {
						// TODO Auto-generated method stub
						
						Bundle bundle =new Bundle();
						bundle.putString("A", "AA");
						return bundle;
					}
				});
				new Thread(future2Task).start();
				
				//Future2Task future2Task1 =new Future2Task(Future2Tas)
				
				
				
				doMyThing();
				
				Bundle resBundle = null;
				try {
					 resBundle=future2Task.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				textView.setText(resBundle.toString());
			}
		});
		

	}

	protected void doMyThing() {
		// TODO Auto-generated method stub
		
	}
	

}
