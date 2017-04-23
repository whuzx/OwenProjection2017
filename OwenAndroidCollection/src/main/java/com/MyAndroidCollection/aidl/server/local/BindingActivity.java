package com.MyAndroidCollection.aidl.server.local;



import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.aidl.server.local.LocalService.LocalBinder;

public class BindingActivity extends Activity {

	LocalService mService;
	boolean mBound = false;
	Button btnButton;

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main_aidl_server);
		btnButton = (Button) findViewById(R.id.btn);
		btnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mBound) {
					// Call a method from the LocalService.
					// However, if this call were something that might hang,
					// then this request should
					// occur in a separate thread to avoid slowing down the
					// activity performance.
					if (mService !=null) {
						int num = mService.getRandomNumber();
						Log.d("owen", "num="+num);
						Toast.makeText(BindingActivity.this, "number: " + num, Toast.LENGTH_SHORT)
								.show();
					}
					
				}

			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		// Bind to LocalService
		Intent intent = new Intent(this, LocalService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Unbind from the service
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// We've bound to LocalService, cast the IBinder and get
			// LocalService instance
			LocalBinder binder = (LocalBinder) service;
			mService = binder.getService();
			mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mBound = false;

		}
	};

	public void onButtonClick(View v) {
		if (mBound) {
			// Call a method from the LocalService.
			// However, if this call were something that might hang, then this
			// request should
			// occur in a separate thread to avoid slowing down the activity
			// performance.
			int num = mService.getRandomNumber();
			Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
		}
	}

}
