package com.MyAndroidCollection.App;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class IntentDemo extends Activity {
	
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//gobroswer();
		//call();
		sms();
		//sendEmail();
	}

	private void gobroswer() {
		// TODO Auto-generated method stub
		Uri uri = Uri.parse("http://www.facebook.com");
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}
	
	public void call()
	{
	  Uri uri=Uri.parse("tel:10086");
	 //Intent it=new Intent(Intent.ACTION_DIAL, uri);
	  Intent it=new Intent(Intent.ACTION_CALL, uri);
	  startActivity(it);
	}
	
	public void sms()
	{
//	  Uri uri=Uri.parse("smsto://0800000123");
//	  Intent it =new Intent(Intent.ACTION_SEND,uri);
//	  it.putExtra("sms_body", "the smsm test");
//	  startActivity(it);
	  
	    //传送SMS/MMS   
	    //调用短信程序   
		Uri uri = Uri.parse("smsto://0800000123");
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		it.putExtra("sms_body", "The SMS text");
		it.setType("vnd.android-dir/mms-sms");
		startActivity(it);
	}
	
	public void sendEmail()
	{
		Uri uri = Uri.parse("mailto:owen.panda@gmail.com");   
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);   
		startActivity(it);    
	}
	

}


