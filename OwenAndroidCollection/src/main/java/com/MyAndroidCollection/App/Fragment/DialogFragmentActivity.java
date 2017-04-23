package com.MyAndroidCollection.App.Fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;

public class DialogFragmentActivity extends Activity implements HwIDListener{

	
	private static final String TAG ="DialogFragmentActivity";
	private MyCallBack myCallBack;
	
	
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.fragment_main_custom);
		myCallBack= new MyCallBack();
		CustomDialog1 newCustomDalog=CustomDialog1.newInstance("hello", "owen", myCallBack);
		newCustomDalog.show(getFragmentManager(), "a");
		
		
	}
	
	@Override
	public void onBackPressed() {
		
		this.finish();
	}
	
	class MyCallBack implements DialogClickListener{

		@Override
		public void doPositiveClick() {
			LogX.i(TAG, "doPositiveClick");
			
		}

		@Override
		public void doNegativeClick() {
			LogX.i(TAG, "doNegativeClick");
			
		}
		
	}

	@Override
	public void onEnterPassword(int position) {
		LogX.i(TAG, "onEnterPassword");
		gotoEnterPassword();
		
	}

	private void gotoEnterPassword() {
		myCallBack= new MyCallBack();
		CustomDialog2 newCustomDalog=CustomDialog2.newInstance("Enterpassword", "owen", myCallBack);
		newCustomDalog.show(getFragmentManager(), "b");
		
	}

	@Override
	public void onFindPassword(int position) {
		LogX.i(TAG, "onFindPassword");
		gotoFindPassword();
		
	}

	private void gotoFindPassword() {
		Intent intent =new Intent();
		intent.setAction("com.huawei.hwid.ACTION_FIND_PWD_BY_HWID");
		intent.setPackage("com.huawei.hwid");
		startActivity(intent);
		
	}

	@Override
	public void onCancel() {
		LogX.i(TAG, "onCancel");
		this.finish();
		
	}

	@Override
	public void onOK() {
		LogX.i(TAG, "onOK");
		//this.finish();
//		myCallBack= new MyCallBack();
//		CustomDialog1 newCustomDalog=CustomDialog1.newInstance("Enterpassword", "owen", myCallBack);
//		newCustomDalog.show(getFragmentManager(), "hhhhh");
	//	getFragmentManager().findFragmentByTag("a").getFragmentManager().
		
		
		
		//取出原有的Fragment
		FragmentTransaction transaction =getFragmentManager().beginTransaction();
		Fragment fragment= getFragmentManager().findFragmentByTag("a");
		if (fragment != null) {
			transaction.replace(R.id.main_custom, fragment);
			transaction.commit();
		}
	
		
	}
	
	
	

}
