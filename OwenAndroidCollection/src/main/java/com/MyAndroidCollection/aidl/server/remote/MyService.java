package com.MyAndroidCollection.aidl.server.remote;

import java.util.HashMap;
import java.util.Map;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.MyAndroidCollection.aidl.server.IMyService;
import com.MyAndroidCollection.aidl.server.ITaskCallBack;
import com.MyAndroidCollection.aidl.server.Student;

public class MyService extends Service {


	
	@Override 
	public void onStart(Intent intent, int startId) {
		Log.d("MyService","service start id=" + startId);
		//callback(startId); 
	}

	
	
	private void callback(int startId) {
		final int N=mCallbacks.beginBroadcast();
		
		try {
			for (int i = 0; i < N; i++) {
				mCallbacks.getBroadcastItem(i).actionPerformed(i);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		mCallbacks.finishBroadcast();
	}



	final RemoteCallbackList <ITaskCallBack>mCallbacks = new RemoteCallbackList <ITaskCallBack>();


	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MyServiceimpl();
	}

	
	public class MyServiceimpl extends IMyService.Stub {

		
		public Student getStudent() throws RemoteException {
			 
			// TODO Auto-generated method stub
			Student st = new Student();
			st.setAge(18);
			st.setName("terry");

			return st;
		}

	
		public Map getMap(String testClass, Student student)
				throws RemoteException {
			// TODO Auto-generated method stub
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("class", "五年级");
			map.put("age", student.getAge());
			map.put("name", student.getName());
			return map;
		}

		@Override
		public void registeCallBack(ITaskCallBack cb) throws RemoteException {
			if (cb !=null) {
				mCallbacks.register(cb);
				
			}
		}

		@Override
		public void unregisterCallBack(ITaskCallBack cb) throws RemoteException {
			if (cb !=null) {
				mCallbacks.unregister(cb);
				
				
			}
		}

		@Override
		public boolean isTaskRunning() throws RemoteException {
			return false;
		}

		@Override
		public void stopRunningTask() throws RemoteException {
			
		}

		@Override
		public void getToken() throws RemoteException {

			new Thread(new Runnable() {

				
				public void run() {
					for (int i = 10; i >= 0; i--) {
						try {
							Thread.sleep(1000);
							Log.d("MyService", "please wait....");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					callback(1);

				}
			}).start();
			
			
			
		}

	}

}
