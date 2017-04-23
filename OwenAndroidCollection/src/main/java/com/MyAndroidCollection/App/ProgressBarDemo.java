package com.MyAndroidCollection.App;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.MyAndroidCollection.R;

public class ProgressBarDemo extends Activity {
	
	private ProgressBar rectangleProgressBar,CircleProgressBar;
	private Button mButton;
	
	protected static final int STOP=0x10000;
	protected static final int NEXT=0x10001;
	private int count=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.progressbar);
        rectangleProgressBar=(ProgressBar)findViewById(R.id.rectangleprobar);
        CircleProgressBar=(ProgressBar)findViewById(R.id.circleProgressBar);
        mButton=(Button)findViewById(R.id.probutton);
        
        rectangleProgressBar.setIndeterminate(false);   
        CircleProgressBar.setIndeterminate(false);  
        
        mButton.setOnClickListener(new Button.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				  rectangleProgressBar.setVisibility(View.VISIBLE);   
				  CircleProgressBar.setVisibility(View.VISIBLE);   
	                   
	                rectangleProgressBar.setMax(100);   
	                rectangleProgressBar.setProgress(0);   
	                CircleProgressBar.setProgress(0);   
	                   
	                //创建一个线程,每秒步长为增加,到100%时停止   
	                Thread mThread = new Thread(new Runnable() {   
	                       
						public void run() {   
	                           
	                        for(int i=0 ; i < 20; i++){   
	                            try{   
	                            	count = (i + 1) * 5 ;   
	                                Thread.sleep(1000);   
	                                if(i == 19){   
	                                    Message msg = new Message();   
	                                    msg.what = STOP;   
	                                    mHandler.sendMessage(msg);   
	                                    break;   
	                                }else{   
	                                    Message msg = new Message();   
	                                    msg.what = NEXT;   
	                                    mHandler.sendMessage(msg);   
	                                }   
	                            }catch (Exception e) {   
	                                e.printStackTrace();   
	                            }   
	                        }   
	                           
	                    }   
	                });   
	                mThread.start();   
	               
	            }});
       
    }
    
    
    //定义一个Handler   
    private Handler mHandler = new Handler(){   
        public void handleMessage(Message msg){   
    
            switch (msg.what) {   
            case STOP:   
                rectangleProgressBar.setVisibility(View.GONE);   
                CircleProgressBar.setVisibility(View.GONE);  
                toastshow1();
                Thread.currentThread().interrupt();   
                break;   
            case NEXT:   
                if(!Thread.currentThread().isInterrupted()){   
                    rectangleProgressBar.setProgress(count);   
                    CircleProgressBar.setProgress(count);   
                }   
                break;   
            }   
        }   
    };
	protected void toastshow() {
		// TODO Auto-generated method stub
        
		Toast toast=new Toast(getApplicationContext());
		ImageView imageView=new ImageView(getApplicationContext());
		imageView.setImageResource(R.drawable.icon);
		toast.setView(imageView);
		toast.show();	
	}   
	
	protected void toastshow1() {
		// TODO Auto-generated method stub
        
		Toast toast=Toast.makeText(this, "image and text", Toast.LENGTH_LONG);
		
		LinearLayout linearLayout=new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		ImageView imageView=new ImageView(this);
		imageView.setImageResource(R.drawable.icon);
		Button button=new Button(this);
		button.setText("progress over");
		View toastView=toast.getView();
		linearLayout.addView(imageView);
		linearLayout.addView(button);
		linearLayout.addView(toastView);
		
		toast.setView(linearLayout);
		toast.show();
		
	}

}