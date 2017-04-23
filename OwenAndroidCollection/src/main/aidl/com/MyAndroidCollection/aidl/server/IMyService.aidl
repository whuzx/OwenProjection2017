package com.MyAndroidCollection.aidl.server;

import com.MyAndroidCollection.aidl.server.Student;
import com.MyAndroidCollection.aidl.server.ITaskCallBack;
interface IMyService
{
	Map getMap(in String test_class,in Student student);
	Student getStudent();
	
	void registeCallBack(ITaskCallBack cb);
	void unregisterCallBack(ITaskCallBack cb);
	
	boolean isTaskRunning();
	void stopRunningTask();
	
	void getToken();
}