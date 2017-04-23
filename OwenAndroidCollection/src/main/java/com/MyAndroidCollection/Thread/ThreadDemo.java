package com.MyAndroidCollection.Thread;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.MyAndroidCollection.R;

public class ThreadDemo extends Activity{
	
	private static final String TAG="ThreadDemo";
	private int count=0;
	private Handler mHandler=new Handler();
	private Timer timer;
	
	private Runnable mRunnable=new Runnable() {
		
		@Override
		public void run() {
			Log.e(TAG, Thread.currentThread().getName()+" "+count);
			count++;
			setTitle(" "+count);
			mHandler.postDelayed(mRunnable, 2000);
		}
	};
	
	  public void onCreate(Bundle savedInstanceState) {   
	        super.onCreate(savedInstanceState);   
	        setContentView(R.layout.main);    
	        //通过Handler启动线程   
	        mHandler.post(mRunnable);  
	        timer =new Timer(true);
	        MyTask.testTimer(timer);
	    }   

	public void onDestroy() {
		mHandler.removeCallbacks(mRunnable);
		if (timer != null) {
			timer.cancel();
			timer.purge();
			timer = null;
		}

		super.onDestroy();
	}

}

	
		
	


