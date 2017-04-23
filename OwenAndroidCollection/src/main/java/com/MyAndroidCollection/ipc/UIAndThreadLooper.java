package com.MyAndroidCollection.ipc;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MyAndroidCollection.util.LogX;

/**
 * http://www.cnblogs.com/qingblog/archive/2012/06/27/266021.html
 * @author z0020101
 1.。而在子线程中，Looper需要通过显式调用Looper. Prepare()方法进行创建。
 Prepare方法通过ThreadLocal来保证Looper在线程内的唯一性，
 如果Looper在线程内已经被创建并且尝试再度创建"Only one Looper may be created per thread"异常将被抛出。*
 
 2.Handler在创建的时候可以指定Looper，这样通过Handler的sendMessage()方法发送出去的消息就会添加到指定Looper里面的MessageQueue里面去。
 在不指定Looper的情况下，Handler绑定的是创建它的线程的Looper。
 如果这个线程的Looper不存在，程序将抛出"Can't create handler inside thread that has not called Looper.prepare()"。

 *
 *3.整个消息处理的大概流程是：
 *1. 包装Message对象（指定Handler、回调函数和携带数据等）；
 *2. 通过Handler的sendMessage()等类似方法将Message发送出去；
 *3. 在Handler的处理方法里面将Message添加到Handler绑定的Looper的MessageQueue；
 *4. Looper的loop()方法通过循环不断从MessageQueue里面提取Message进行处理，并移除处理完毕的Message；
 *. 通过调用Message绑定的Handler对象的dispatchMessage()方法完成对消息的处理。
 *
 *
 * 在dispatchMessage()方法里，如何处理Message则由用户指定，三个判断，优先级从高到低：
 * 1. Message里面的Callback，一个实现了Runnable接口的对象，其中run函数做处理工作；
 * 2. Handler里面mCallback指向的一个实现了Callback接口的对象，由其handleMessage进行处理；
 * 3. 处理消息Handler对象对应的类继承并实现了其中handleMessage函数，通过这个实现的handleMessage函数处理消息。
 *  
 
 4.有的时候，我们的子线程想去改变UI了，这个时候千万不要再子线程中去修改，获得UI线程的Looper，然后发送消息即可。
 
 
*/
public class UIAndThreadLooper extends Activity implements OnClickListener{
	
	Button  sendmsgbtn =null;
	TextView valueTextView=null;
	MyThread thread;
	MyHandler myHandler=null;//若直接在子线程中新建handle，然后在子线程中发送消息，这样的话就失去了我们多线程的意义了。
	WorkerCanGetLooper worker=null;
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
	
	
	int count=0;
	@Override
	public void onClick(View v) {
		
		if(v == sendmsgbtn){
			if(count %2 == 0){
			thread = new MyThread();
			thread.start();
			}else{
				worker=new WorkerCanGetLooper("worker");
				worker.getLooper();
				//myHandler=new MyHandler(worker.getLooper());//no
				myHandler=new MyHandler(Looper.myLooper());
			}
			count++;
				
		}
		
	}
	
	private class MyHandler extends Handler{
		public MyHandler(Looper looper){
			super(looper);
		}
		
		@Override
		public void handleMessage(Message msg){
			valueTextView.setText(msg.obj.toString());
		}
		
	}
	
	private class MyThread extends Thread{
		public void run(){
			/**
			 * Android会自动替主线程建立Message Queue。在这个子线程里并没有建立Message Queue。
			 * 所以，myLooper值为null，而mainLooper则指向主线程里的Looper。于是，执行到：
			 */
			Looper curLooper =Looper.myLooper();
			Looper mainLooper=Looper.getMainLooper();
			String msg;
			if (curLooper == null) {
				myHandler =new MyHandler(mainLooper);
				msg="curLooper is null";
				
			}else {
				myHandler =new MyHandler(curLooper);
				msg="this is curLooper";
			}
			myHandler.removeMessages(0);
			Message message=myHandler.obtainMessage(1, 1, 1,msg);
			myHandler.sendMessage(message);
			
			
		}
	}
	
	private class WorkerCanGetLooper implements Runnable {
		
		private Looper mLooper=null;
		private final Object mLoc=new Object();
		
		public WorkerCanGetLooper(String name) {
			Thread t=new Thread(null,this,name);
			t.setPriority(Thread.MIN_PRIORITY);
			t.start();
			
			synchronized (mLoc) {
				while(mLooper == null){
					try{
						mLoc.wait();
					}catch(InterruptedException e){
						
					}
				}
			}
		}
		public void run(){
			synchronized (mLoc) {
				Looper.prepare();
				LogX.d("WorkerCanGetLooper","run and init looper");
				mLooper=Looper.myLooper();
				mLoc.notifyAll();
			}
			String msg="this is Worker Looper";
			myHandler.removeMessages(0);
			Message message=myHandler.obtainMessage(1, 1, 1,msg);
			myHandler.sendMessage(message);
			//myHandler.post(r)
			
			Looper.loop();
			
		}
		
		public Looper getLooper(){
			LogX.d("WorkerCanGetLooper","getLooper");
			return mLooper;
		}
		
		public void quit(){
			mLooper.quit();
		}
	}

}
