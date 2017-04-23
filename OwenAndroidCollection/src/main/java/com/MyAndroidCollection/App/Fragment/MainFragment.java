package com.MyAndroidCollection.App.Fragment;



import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;

public class MainFragment extends Activity  implements HwIDListener {
	
	
	private static final String TAG="MainFragment";
	public MyCallBack myCallBack;

	@Override
	protected void onCreate(Bundle bundle) {
		
		super.onCreate(bundle);
		//notdaynamic();
		daynamic() ;

	}

	private void notdaynamic() {
		setContentView(R.layout.fragment_main);
	}

	
	/***
	 * 1.获取到FragmentManager，在Activity中可以直接通过getFragmentManager得到。

	2.开启一个事务，通过调用beginTransaction方法开启。

	3.向容器内加入Fragment，一般使用replace方法实现，需要传入容器的id和Fragment的实例。

	4.提交事务，调用commit方法提交。


	 */
	// 屏幕切换需要重走该函数才行，否则依然不变哦
	private void daynamic() {
		setContentView(R.layout.fragment_main_custom);
		Display display=getWindowManager().getDefaultDisplay();
		if (display.getWidth() > display.getHeight()) {
			Fragment1 fragment1 =new Fragment1();
			getFragmentManager().beginTransaction().replace(R.id.main_custom,fragment1).commit();
		} else {

			Fragment2 fragment2 =new Fragment2();
			getFragmentManager().beginTransaction().replace(R.id.main_custom,fragment2).commit();
			
		//	getFragmentManager().beginTransaction().add(R.id.main_custom,fragment2).commit();
			
		//	getFragmentManager().getFragment(bundle, key);
		//	getFragmentManager().putFragment(bundle, key, fragment);

		}
	
	
	}

	@Override
	public void onEnterPassword(int position) {
		LogX.i(TAG,"callBack=="+"position" + position);
		
		
		//Activity 调用Fragment 的 方法，交互信息。
		Fragment2 fragment2 =null;
				//(Fragment2) getFragmentManager().findFragmentById(R.id.fragment2);
		
		if (fragment2 != null) {
			/***
			 * 宿主Activity可以可以通过findFragmentById()函数获取Fragment实例，
			 * 然后通过访问fragments的共有函数来将消息传递给Fragments。
			 */
			fragment2.fragment2Fun1(position);
		}else {
			
			// 动态加载的情形
			 fragment2= new Fragment2();
			 Bundle args=new Bundle();
			 args.putInt("pos", position);
			 fragment2.setArguments(args);
			 
			 
			 FragmentTransaction transaction = getFragmentManager().beginTransaction();
			 
			 transaction.replace(R.id.main_custom, fragment2);
			 transaction.addToBackStack(null);
			 transaction.commit();
		
		}
		
}

	@Override
	public void onCancel() {
		this.finish();
		
	}

	@Override
	public void onFindPassword(int position) {
		LogX.i(TAG, "onFindPassword");
		Intent intent =new Intent();
		intent.setAction("com.huawei.hwid.ACTION_FIND_PWD_BY_HWID");
		intent.setPackage("com.huawei.hwid");
		startActivity(intent);
		
		
	}
	
	

	@Override
	public void onOK() {
		LogX.i(TAG, "onOK");
	}
	
		
		
	
	
	
//	@Override
//	public void  onConfigurationChanged(Configuration newConfig){
//		super.onConfigurationChanged(newConfig);
//		daynamic();
//		
//	}
	
	class MyCallBack implements HwIDListener{

		@Override
		public void onEnterPassword(int position) {
			LogX.i(TAG, "onEnterPassword");
			
		}

		@Override
		public void onFindPassword(int position) {
			LogX.i(TAG, "onFindPassword");
			
		}

		@Override
		public void onCancel() {
			LogX.i(TAG, "onCancel");
		}

		@Override
		public void onOK() {
			LogX.i(TAG, "onOK");
		}
		
	}


	

}
