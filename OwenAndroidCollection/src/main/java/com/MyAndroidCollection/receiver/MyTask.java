package com.MyAndroidCollection.receiver;

import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

public class MyTask {


	public static  void  testTimer(Timer timer){
		Timer Mytimer =timer;
		TimerTask task=new TimerTask() {

			@Override
			public void run() {
				Log.i("TimerTask", "time:" + System.currentTimeMillis());

			}
		};
		timer.schedule(task, 1000,1000);
	}



}
