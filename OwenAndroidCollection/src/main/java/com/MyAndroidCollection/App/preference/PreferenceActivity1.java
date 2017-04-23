package com.MyAndroidCollection.App.preference;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment.OnPreferenceStartFragmentCallback;
import android.util.Log;

import com.MyAndroidCollection.R;

public class PreferenceActivity1 extends PreferenceActivity implements OnPreferenceChangeListener,OnPreferenceClickListener,OnPreferenceStartFragmentCallback{

	
	String updateSwitchKeyString;
	String updateFrequencyKey;
	CheckBoxPreference updateCheckBoxPreference;
	ListPreference updateFrequListPreference;
	private static final String TAG="PreferenceActivity1";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//super.onBuildStartFragmentIntent(fragmentName, args, titleRes, shortTitleRes);
		//super.onBuildHeaders(target);
		addPreferencesFromResource(R.xml.preferencesetting1);
		
		updateSwitchKeyString=getResources().getString(R.string.auto_update_switch_key);
		
		updateFrequencyKey=getResources().getString(R.string.auto_update_frequency_key);
		
		updateCheckBoxPreference=(CheckBoxPreference) findPreference(updateSwitchKeyString);
		updateFrequListPreference=(ListPreference) findPreference(updateFrequencyKey);
		
		updateCheckBoxPreference.setOnPreferenceChangeListener(this);
		updateCheckBoxPreference.setOnPreferenceClickListener(this);
		
		updateFrequListPreference.setOnPreferenceChangeListener(this);
		updateFrequListPreference.setOnPreferenceClickListener(this);
		
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {

		Log.i(TAG, "preference is Clicked");
		Log.i(TAG,"Key = "+preference.getKey());
		// 判断是哪个Preference改变了
		if (preference.getKey().equals(updateSwitchKeyString)) {
			Log.i(TAG, "checkbox preference is changed");
		} else if (preference.getKey().equals(updateFrequencyKey)) {
			Log.i(TAG, "list preference is changed");
		} else {
			// 如果返回false表示不允许被改变
			return false;
		}
		// 返回true表示允许改变
		return true;
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {

		Log.i(TAG, "preference is changed");
		Log.i(TAG, "Key = " + preference.getKey());
		// 判断是哪个Preference改变了
		if (preference.getKey().equals(updateSwitchKeyString)) {
			Log.i(TAG, "checkbox preference is changed");
		} else if (preference.getKey().equals(updateFrequencyKey)) {
			Log.i(TAG, "list preference is changed");
		} else {
			// 如果返回false表示不允许被改变
			return false;
		}
		return true;
	}
}
