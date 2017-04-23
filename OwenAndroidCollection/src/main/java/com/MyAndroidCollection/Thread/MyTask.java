package com.MyAndroidCollection.Thread;

import java.util.Timer;
import java.util.TimerTask;

import com.MyAndroidCollection.util.LogX;

public class MyTask {

	
	public static  void  testTimer(Timer timer){
		Timer Mytimer =timer;
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				LogX.i("TimerTask", "time:" + System.currentTimeMillis());
				
			}
		};
		timer.schedule(task, 1000,1000);
	}
}
