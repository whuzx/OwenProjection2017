package com.MyAndroidCollection.aidl.server.local;


import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService  extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
	
	private final IBinder mBinder=new LocalBinder();
	
	
	
	private final Random mGeneratorRandom=new Random();
	
	/**
	 * * Class used for the client Binder. Because we know this service always *
	 * runs in the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder {
		LocalService getService() {
			// Return this instance of LocalService so clients can call public
			// methods
			return LocalService.this;
		}
	}
	
	public int getRandomNumber(){
		return mGeneratorRandom.nextInt(100);
	}
	

}
