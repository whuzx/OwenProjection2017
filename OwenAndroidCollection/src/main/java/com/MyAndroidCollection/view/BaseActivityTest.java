package com.MyAndroidCollection.view;

import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.MyAndroidCollection.activitys.ActivitysDemo;
import com.MyAndroidCollection.util.LogX;

public class BaseActivityTest extends BaseActivity {
	
	public void onCreate(Bundle bundle){
		initResource();
		super.onCreate(bundle);
		testResouce();
		
		testActionisExist();
	}

	//判断对应的Action 是否存在？
	private void testActionisExist() {
	       PackageManager pkgMgr = getPackageManager();
	        if(null != pkgMgr) {
	        	 LogX.d("owen", "check");
	        	
	            List<ResolveInfo> list = pkgMgr.queryIntentActivities(new Intent("android.intent.action.HWID.Authenticator"), 0);
	            
	            if(list != null && list.size() > 0) {
	              LogX.d("owen", "OKOKOK");
	            }
	        }
		
	}

	private void testResouce() {
		List<Button> btns=getButtons();
		
		if(btns.size()> 0){
			btns.get(0).setText("change defaut value");	
			btns.get(0).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				
					Intent intent=new Intent();
	        		intent.setClass(BaseActivityTest.this, ActivitysDemo.class);
	        		Bundle bundle=new Bundle();
	        		bundle.putString("title", "this is from BaseActivityTest");
	        		intent.putExtras(bundle);
	        		startActivity(intent);
				}
			});
		}
		
		List<TextView> textViews=getTextViews();
		for (TextView textView : textViews) {
			textView.setText("this is BaseActivityTest");
		}	
	}

	private void initResource() {
		setNumber(2);		
	}
	
	

}
