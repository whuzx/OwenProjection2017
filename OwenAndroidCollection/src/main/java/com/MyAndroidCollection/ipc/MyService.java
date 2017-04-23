package com.MyAndroidCollection.ipc;

import com.MyAndroidCollection.util.SpeakUtil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Common.getTimes(this);
		SpeakUtil.speak(MyService.this);
		
		return super.onStartCommand(intent, flags, startId);
	}

}
