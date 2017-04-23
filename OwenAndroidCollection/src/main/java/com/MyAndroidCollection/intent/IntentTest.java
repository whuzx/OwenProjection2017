/**
 * 
 */
package com.MyAndroidCollection.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.MyAndroidCollection.view.BaseActivity;

/**
 * @author z0020101
 *
 */
public class IntentTest extends BaseActivity {

	
	
	Intent intent=new Intent();
	
	String data;
	Uri uri;
	
	
	/**
	 * 
	 */
	public void onCreate(Bundle bundle){
		initResource();
		super.onCreate(bundle);

		testIntentProperties();
		getButtons().get(0).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
//				data="content://contacts/people/1";
//				uri=Uri.parse(data);
//				intent.setAction(Intent.ACTION_VIEW);
//				intent.setData(uri);
				
				
//				data="tel:192782402";
//				uri=Uri.parse(data);
//				intent.setAction(Intent.ACTION_DIAL);
//				intent.setData(uri);
				
//				data="tel:192782402";
//				uri=Uri.parse(data);
//				intent.setAction(Intent.ACTION_CALL);
//				intent.setData(uri);
				
				
				data="http://www.baidu.com";
				uri=Uri.parse(data);
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(uri);
				
				
				startActivity(intent);
				
			}
		});
		
	}

	private void testIntentProperties() {
		
		
	}

	private void initResource() {
		setNumber(6);
		
	}
	
	
	
}
