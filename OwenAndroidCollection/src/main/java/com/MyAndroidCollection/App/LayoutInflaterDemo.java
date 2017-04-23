package com.MyAndroidCollection.App;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.MyAndroidCollection.R;

public class LayoutInflaterDemo extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    private Button button;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutinflater0);
        
        button=(Button)findViewById(R.id.LayoutInflaterbtn);
        button.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.e("Layout","000");
		showCustomDialog();
		//showSettings();
		
	}

	private void showCustomDialog() {
		Log.e("Layout", "???");
		// TODO Auto-generated method stub

		AlertDialog.Builder builder;
		AlertDialog alertDialog;
		Context mContext = LayoutInflaterDemo.this;
		// LayoutInflater inflater = getLayoutInflater();
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.customdialog, null);
		TextView textView = (TextView) layout.findViewById(R.id.dialogtext);
		textView.setText("Hello");

		ImageView imageView = (ImageView) layout.findViewById(R.id.dialogimage);
		imageView.setImageResource(R.drawable.icon);

		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.show();

	}

	private void showSettings(){   
        
	     final Intent settings = new Intent(android.provider.Settings.ACTION_SETTINGS);   
	            settings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   
	                    Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);   
	               
	        startActivity(settings);   
	}
}