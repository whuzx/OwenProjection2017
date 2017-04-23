package com.MyAndroidCollection.aidl;

import Owen.Data.IMyService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.MyAndroidCollection.R;

public class aidlActivity extends Activity implements OnClickListener {
	Button btn1, btn2;

	private IMyService myService = null;
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			myService = IMyService.Stub.asInterface(service);
			btn2.setEnabled(true);

		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidlclient);

		btn1 = (Button) findViewById(R.id.Button01);
		btn2 = (Button) findViewById(R.id.Button02);
		btn2.setEnabled(false);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Button01:
			bindService(new Intent("com.aidl.test.IMyService"),
					serviceConnection, Context.BIND_AUTO_CREATE);
			break;
		case R.id.Button02:
			StringBuilder sb = new StringBuilder();
			try {
				sb.append("学生名称为：" + myService.getStudent().getName() + "\n");
				sb.append("年龄为：" + myService.getStudent().getAge() + "\n");
				sb.append("map 对象内容为如下："
						+ myService.getMap("中国", myService.getStudent())
								.toString());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			new AlertDialog.Builder(aidlActivity.this).setTitle("调用外部服务")
					.setMessage(sb.toString()).setPositiveButton(
							android.R.string.ok, null).show();
			break;
		default:
			break;
		}
	}
}