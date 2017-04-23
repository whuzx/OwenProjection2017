package com.MyAndroidCollection.ipc;



import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.MyAndroidCollection.util.LogX;
import com.MyAndroidCollection.view.BaseActivity;

public class updateuiinthread extends BaseActivity {

	private static final int UPDATE = 1;

	Handler mHandler; // 主线程Handler
	MyHandler myHandler; // 主线程Handler 重写的 handleMessage 函数

	public void onCreate(Bundle savedBundle) {

		setNumber(4);
		super.onCreate(savedBundle);

		mHandler = new Handler();

		mHandler.post(mRunnable);

		LogX.d("MAIN", "current id " + Thread.currentThread().getId()
				+ "  name " + Thread.currentThread().getName());
		/*
		 * StatusBarManager statusMgr =
		 * (StatusBarManager)getSystemService("statusbar"); if(statusMgr !=
		 * null) { statusMgr.disable(StatusBarManager.DISABLE_EXPAND |
		 * StatusBarManager.DISABLE_HOME | StatusBarManager.DISABLE_RECENT
		 * 
		 * ); }
		 */
		// 这就是多线程编程，有时候你运行若干次，结果正确，并不表明你的逻辑就是对的。我们一定要遵循代码的规范，保持清晰的思维。
		// new myThread1().start(); //不要在主线程中更新UI

		getButtons().get(0).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mHandler.postDelayed(mRunnable, 2000); // mRunnable1 为UI线程

			}

		});

		myHandler = new MyHandler();

		getButtons().get(1).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Message msg = myHandler.obtainMessage(UPDATE);
				myHandler.sendMessage(msg);

				Message msg1 = mHandler.obtainMessage(UPDATE);
				mHandler.sendMessage(msg1); // 没有接受处理的话会出现问题

				// android.view.ViewRootImpl$CalledFromWrongThreadException:
				// Only the original thread that created a view hierarchy can
				// touch its views.

				// new myThread1().start();//错误

				// 另外一种更简洁的发送消息给ui线程的方法。
				getTextViews().get(3).post(new Runnable() {

					@Override
					public void run() {
						getTextViews().get(3).setText(
								"this is update ui use  view pist");

					}
				});

			}

		});
	}

	Runnable mRunnable = new Runnable() {

		@Override
		public void run() {

			LogX.d("mRunnable", "current id " + Thread.currentThread().getId()
					+ "  name " + Thread.currentThread().getName());
			getTextViews().get(0).setText("this is update ui on main thread");
		}
	};

	Thread myThread = new Thread(new Runnable() {

		@Override
		public void run() {

			LogX.d("myThread ", "current id  " + Thread.currentThread().getId()
					+ "  name " + Thread.currentThread().getName());
			getTextViews().get(1).setText(
					"this is update ui on  thread---=====ZYR");

		}

	});

	class myThread1 extends Thread {

		public void run() {

			// LogX.d("myThread1 ", "current id  " +
			// Thread.currentThread().getId() + "  name "
			// LogX.d("myThread1 ", "current id  " +
			// Thread.currentThread().getId() + "  name "
			// LogX.d("myThread1 ", "current id  " +
			// Thread.currentThread().getId() + "  name "
			// LogX.d("myThread1 ", "current id  " +
			// Thread.currentThread().getId() + "  name "
			// LogX.d("myThread1 ", "current id  " +
			// Thread.currentThread().getId() + "  name "
			// + Thread.currentThread().getName()
			// );
			getTextViews().get(2).setText("this is update ui on  thread");
		}

		Runnable mRunnable1 = new Runnable() {
			int count = 0;

			@Override
			public void run() {

				LogX.d("mRunnable", "current id "
						+ Thread.currentThread().getId() + "  name "
						+ Thread.currentThread().getName());
				getTextViews().get(3).setText(
						"this is update ui on main thread " + count);
				count++;
			}
		};

		// ，一个线程可以有多个Handler，但是只能有一个Looper！
		/**
		 * 有了handler之后，我们就可以使用 post(Runnable),postAtTime(Runnable,
		 * long),postDelayed(Runnable, long),
		 * sendEmptyMessage(int),sendMessage(Message),sendMessageAtTime(Message,
		 * long)和 sendMessageDelayed(Message, long)这些方法向MQ上发送消息了。
		 * 光看这些API你可能会觉得handler能发两种消息，一种是Runnable对象，一种是message对象，这是直观的理解，
		 * 但其实post发出的Runnable对象最后都被封装成message对象了，见源码：
		 * 
		 * @author z0020101
		 * 
		 *         1. message.target为该handler对象，
		 *         这确保了looper执行到该message时能找到处理它的handler，即loop()方法中的关键代码
		 *         2.post发出的message，其callback为Runnable对象
		 *         3.handler如何处理消息。消息的处理是通过核心方法dispatchMessage(Message
		 *         msg)与钩子方法handleMessage(Message msg)完成的，见源码
		 */
		public class LooperThread extends Thread {
			private Handler handler1;

			private Handler handler2;

			@Override
			public void run() {
				// 将当前线程初始化为Looper线程
				Looper.prepare();
				// 实例化两个handler
				handler1 = new Handler();
				handler2 = new Handler();
				// 开始循环处理消息队列
				Looper.loop();
			}
		}

	}

	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case UPDATE:// 在收到消息时，对界面进行更新
				getTextViews().get(1).setText("This update by message");
				break;
			}
		}
	}
}