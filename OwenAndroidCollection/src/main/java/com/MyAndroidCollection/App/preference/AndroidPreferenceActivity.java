package com.MyAndroidCollection.App.preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.MyAndroidCollection.R;

public class AndroidPreferenceActivity extends PreferenceActivity {

	
	private static final String TAG="AndroidPreferenceActivity";
	 final private int menuSettings=Menu.FIRST;  
	 private static final int REQ_SYSTEM_SETTINGS = 0; 

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.simplepreference);
		
		
	}
	

	//创建菜单  
	    @Override  
	    public boolean onCreateOptionsMenu(Menu menu)  
	    {  
	        // 建立菜单  
	        menu.add(0, menuSettings, 2, "设置");  
	        return super.onCreateOptionsMenu(menu);  
	    }  
	    //菜单选择事件处理  
	    @Override  
	    public boolean onMenuItemSelected(int featureId, MenuItem item)  
	    {  
	        switch (item.getItemId())  
	        {  
	            case menuSettings:  
	                //转到Settings设置界面  
	            	Intent intent = new Intent();
	            	intent.setAction("com.MyAndroidCollection.ACTION_PREFERENCE");
	                startActivityForResult(intent, REQ_SYSTEM_SETTINGS);  
	                break;  
	            default:  
	                break;  
	        }  
	        return super.onMenuItemSelected(featureId, item);  
	    }  
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// 获取设置界面PreferenceActivity中各个Preference的值
		String updateSwitchKey = getResources().getString(
				R.string.auto_update_switch_key);
		String updateFrequencyKey = getResources().getString(
				R.string.auto_update_frequency_key);
		// 取得属于整个应用程序的SharedPreferences
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(this);
		Boolean updateSwitch = settings.getBoolean(updateSwitchKey, true);
		String updateFrequency = settings.getString(updateFrequencyKey, "10");
		// 打印结果
		Log.i(TAG, "CheckBoxPreference_Main"+ updateSwitch.toString());
		Log.i(TAG, "ListPreference_Main"+ updateFrequency);
	}
	       
	     

}
