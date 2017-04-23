package com.MyAndroidCollection.ipc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class testSharePreference {
	
	
	private static final  String PreferenceName="MyAndroidCollection_SharePreference";
	
	public static void inputValueToSharePreference(Context context,String key,String value){
		SharedPreferences sharedPreferences=CreateSharedPreferences(context, context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	
	public static SharedPreferences CreateSharedPreferences(Context context,int mode){
		return context.getSharedPreferences(PreferenceName, mode);
	}
	
	public static String outputValueFromSharePreference(Context context,String key){
		SharedPreferences sharedPreferences=CreateSharedPreferences(context, context.MODE_PRIVATE);
		return sharedPreferences.getString(key, "default");
		
		
		
	}
	
/*	public void OnSharedPreferenceChangeListener(SharedPreferences sharedPreferences, String key){
		
	}*/


}
