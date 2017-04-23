package com.MyAndroidCollection.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.MyAndroidCollection.R;

public class animActivity extends Activity implements OnClickListener  {
	ImageView iv = null;
	private int count;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation);

		iv = (ImageView) findViewById(R.id.ImageView01);
		//iv.setOnClickListener(this);
		
		iv.setOnClickListener(clickListener);
		//clickListener.onClick(iv);
		//testdefaultClick();//
	
		//onClick(iv);	
	    //mHandler.post(mRunnable);   

			 
	}

////	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		  Log.e("TEST", "Clicked0");
		AnimationDrawable anim = null;
		Object ob = iv.getBackground();
		anim = (AnimationDrawable) ob;
		anim.stop();
		anim.start();
	}
	
	 private Handler mHandler=new Handler();
		
		private Runnable mRunnable=new Runnable() {
			
			@Override
			public void run() {
				
				//clickListener.onClick(iv);
				
				// TODO Auto-generated method stub
				Log.e("....", Thread.currentThread().getName()+" "+count);
				count++;
				setTitle(" "+count);
				mHandler.postDelayed(mRunnable, 2000);
				AnimationDrawable anim = null;
				Object ob = iv.getBackground();
				anim = (AnimationDrawable) ob;
				anim.stop();
				anim.start();
				
			}
		};
		
	
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			   Log.e("TEST", "Clicked");
			// TODO Auto-generated method stub
			AnimationDrawable anim = null;
			Object ob = iv.getBackground();
			anim = (AnimationDrawable) ob;
			anim.stop();
			anim.start();
			
		}
	};
	
	private void testdefaultClick()
	{
		AnimationDrawable anim = null;
		Object ob = iv.getBackground();
		anim = (AnimationDrawable) ob;
		anim.stop();
		anim.start();
		
	}


}