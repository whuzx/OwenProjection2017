package com.MyAndroidCollection.ipc;



import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleLoopTest  extends Activity implements OnClickListener{
	
	Button  sendmsgbtn =null;
	TextView valueTextView=null;
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		 LinearLayout linearLayout =new LinearLayout(this);
	     linearLayout.setOrientation(LinearLayout.VERTICAL);
	        
	     sendmsgbtn =new Button(this);
	     sendmsgbtn.setText("send msg");
	      
	        
	     valueTextView= new TextView(this);
	     valueTextView.setTextColor(Color.WHITE);
	     
	     linearLayout.addView(sendmsgbtn);
	     linearLayout.addView(valueTextView);
	     setContentView(linearLayout);
	     
	     sendmsgbtn.setOnClickListener(this);
	      
		
		
		
	}
	
	@Override
	public void onClick(View v) {
		
		if (v == sendmsgbtn) {
			Looper looper =Looper.myLooper();//获取当前looper
			MyHandler mHandler =new MyHandler(looper);
			mHandler.removeMessages(0);//
			String msg="different module exchange in main thread";
			Message message=mHandler.obtainMessage(1, 1, 1, msg);
			mHandler.sendMessage(message);
			
		}
		
		
	}
	
	private class MyHandler extends Handler{             

        public MyHandler(Looper looper){
               super(looper);

         }

        @Override

        public void handleMessage(Message msg) {//处理消息
        	   Log.e("looptest", msg.obj.toString()+"");
               valueTextView.setText(msg.obj.toString());

         }            

  }

	


}





