package com.MyAndroidCollection.Services;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.MyAndroidCollection.R;

//模拟默认UI点击
public class QueryRunningServices extends Activity {
	
	
    /** Called when the activity is first created. */
  
    private static final String TAG = "ActivityDemo";  
    
    private DisplayMetrics dmDisplayMetrics;
    private static final int GUINOTIFIER = 0x1234; 
    public Calendar mCalendar; 
    public int mMinutes; 
    public int mHour; 
    public Handler mHandler; 
    private Thread mClockThread;
    
    private EditText mEditText;  
    private TextView clockTextView;
    private TextView mTextView;
    private String mString;  
    
    private Button mButton;
    private Button startButton;
    private Button stopButton;
    
    ActivityManager mActivityManager;
    List<ActivityManager.RunningServiceInfo> mServiceList;
    
    
    public void onCreate(Bundle savedInstanceState) {   
    	
        super.onCreate(savedInstanceState);  
    	setContentView(R.layout.runingservicelayout);  
        mClockThread = new LooperThread();
        setUpViews(); 
        init();
        test();
        
      
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case GUINOTIFIER:
					Log.e(TAG, mHour + " : " + mMinutes);
					clockTextView.setText(mHour + " : " + mMinutes);
					break;
				}
				super.handleMessage(msg);
			}

		};
		
		OnClickEvent.onClick(null);// 默认为点击状态
	
	}




	private void test() {
		// TODO Auto-generated method stub
		// 我要判断的服务名字，我在com.android.myplayer.MusicPlayService里加了一个音乐服务
		final String ClassName = "com.android.myplayer.MusicPlayService";
		mEditText.setText(ClassName);
		Query(ClassName);

	}

	private void Query(String ClassName) {

		boolean b = controlClassIsStart(mServiceList, ClassName);
		Log.e(TAG, Boolean.toString(b));
		mTextView.setText("你要判断的服务" + ClassName + "状态为: " + Boolean.toString(b)
				+ "\n" + "总的服务为：" + getServiceClassName(mServiceList));
	}

	private void init() {
		
		// TODO Auto-generated method stub
		  mActivityManager =    
	            (ActivityManager)getSystemService(ACTIVITY_SERVICE);        
	      mServiceList = mActivityManager.getRunningServices(30);   
		
	}




	private void setUpViews() {
	
        mEditText=(EditText)findViewById(R.id.editText);
        clockTextView=(TextView) findViewById(R.id.Clock);
        
        mButton=(Button)findViewById(R.id.Button01);
        mButton.setOnClickListener(OnClickEvent);
        
        startButton=(Button) findViewById(R.id.start);
        startButton.setOnClickListener(OnClickEvent);
        
        stopButton=(Button) findViewById(R.id.stop);
        stopButton.setOnClickListener(OnClickEvent);
       
        mTextView = (TextView)findViewById(R.id.mytxt); 
        Log.e(TAG, "start onCreate~~~");
	}
   


       
	// 获取所有启动的服务的类名
	private String getServiceClassName(
			List<ActivityManager.RunningServiceInfo> mServiceList) {
		String res = "";
		for (int i = 0; i < mServiceList.size(); i++) {
			res += mServiceList.get(i).service.getClassName() + " \n";
		}

		return res;
	}


	private boolean controlClassIsStart(List<RunningServiceInfo> mServiceList,
			String className) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mServiceList.size(); i++) {
			Log.e(TAG, mServiceList.get(i).service.getClassName());
			if (className.equals(mServiceList.get(i).service.getClassName())) {
				return true;
			}
		}
		return false;

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e(TAG, "start onStart~~~");
		displaymetics();
	}




	private void displaymetics() {
		dmDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dmDisplayMetrics);
		Log.e(TAG, "Width:" + dmDisplayMetrics.widthPixels + "height:"
				+ dmDisplayMetrics.heightPixels);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		mEditText.setText(mString);
		Log.e(TAG, "start onRestart~~~");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e(TAG, "start onResume~~~");
	}

	@Override
	protected void onPause() {
		super.onPause();
		mString = mEditText.getText().toString();
		Log.e(TAG, "start onPause~~~");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e(TAG, "start onStop~~~");
	} 
       
    @Override  
    protected void onDestroy() {   
        super.onDestroy();   
        Log.e(TAG, "start onDestroy~~~");   
    }  
    
	public class LooperThread extends Thread {
		public void run() {
			super.run();
			try {
				do {// 每间隔一秒取一次系统 时间
					long time = System.currentTimeMillis();
					final Calendar mCalendar = Calendar.getInstance();
					mCalendar.setTimeInMillis(time);
					mHour = mCalendar.get(Calendar.HOUR);
					mMinutes = mCalendar.get(Calendar.MINUTE);
					Thread.sleep(1000);
					// 取得系统时间后发送消息给Handler
					Message m = new Message();
					m.what = QueryRunningServices.GUINOTIFIER;

					QueryRunningServices.this.mHandler.sendMessage(m);
				} while (!LooperThread.interrupted());// 当系统发出终端命令时停止循环
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
   
	OnClickListener OnClickEvent = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.e("TEST", "Clicked");
			if (v == mButton) {
				if(mEditText!=null)
				 {
					Query(mEditText.getText().toString());
				 
				 }
			} else if (v == startButton) {
				
				//mClockThread.stop();
				//mClockThread.start();

			} else if (v == stopButton) {
				//mClockThread.suspend();
			}

		}
	};

    
    

}