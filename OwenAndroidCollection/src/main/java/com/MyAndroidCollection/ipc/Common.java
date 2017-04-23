package com.MyAndroidCollection.ipc;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Common {
	public static final String TAG = "test_share_pf";
	public static final String FILE_NAME = "myTestFile";
	public static final String ITEM_KEY = "writeTimes";
	

	public static boolean writeTofile(Context context){
		Log.d(TAG, "enter writeTofile(Context:" + context + ")");
		SharedPreferences sf = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		int times = sf.getInt(ITEM_KEY, 0);
		Log.d(TAG, "read from file times:" + times + ")");
		sf.edit().putInt(ITEM_KEY, ++times).commit();
		Log.d(TAG, "write times:" + times + ")");
		return true;
	}
	public static int getTimes(Context context){
		Log.d(TAG, "enter getTimes(Context:" + context + ")");
		SharedPreferences sf = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		int times = sf.getInt(ITEM_KEY, 0);
		Log.d(TAG, "get times:" + times + ")");
		return times;
	}
}
