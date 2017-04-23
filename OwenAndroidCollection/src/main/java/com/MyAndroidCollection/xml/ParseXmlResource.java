package com.MyAndroidCollection.xml;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;

import android.R.integer;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ParseXmlResource extends Activity {

	TextView textView;
	
	private static final String TAG ="ParseXmlResource";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout linearLayout = new LinearLayout(this);
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
        
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		linearLayout.setLayoutParams(params);
		
		LayoutParams params1 = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);

		Button parseButton = new Button(this);
		parseButton.setText("parse xml resource");
		parseButton.setLayoutParams(params1);
		
		LayoutParams params2 = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);

		textView= new TextView(this);
		textView.setText("res:");
		textView.setBackground(getResources().getDrawable(R.drawable.icon));
		textView.setLayoutParams(params2);

		
		linearLayout.addView(parseButton);
		linearLayout.addView(textView);
		

		setContentView(linearLayout);

		parseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				new parseThread().start();
				//new parseThread().run();

			}
		});

	}
	


	private String parsexmltool(int id) {
		
		LogX.i(TAG, "parsexmltool");

		StringBuilder sBuilder = new StringBuilder("");

		int counter = 0;

		Resources resources = getResources();

		XmlResourceParser xrp = resources.getXml(R.xml.customers);

		try {

			while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
				switch (xrp.getEventType()) {
				case XmlResourceParser.START_TAG:

					String nameString = xrp.getName();
					if (nameString.equals("customer")) {
						counter++;
						sBuilder.append(" " + counter + "info:" + "\n");
						sBuilder.append(xrp.getAttributeValue(0) + "\n");
						sBuilder.append(xrp.getAttributeValue(1) + "\n");
						sBuilder.append(xrp.getAttributeValue(2) + "\n");
						sBuilder.append(xrp.getAttributeValue(3) + "\n");
					}

					break;

				case XmlResourceParser.END_TAG:
				case XmlResourceParser.TEXT:
					break;

				default:
					break;
				}
				xrp.next();

			}
			
			LogX.i(TAG,sBuilder.toString());
			

		} catch (Exception e) {
			LogX.e(TAG, e+ "");
		}
		return sBuilder.toString();

	}
	
	class parseThread extends Thread{
		
	 
		@Override
		public void run() {
			
		
			
			
			final String reString=parsexmltool(0);
			LogX.i(TAG, "res" +reString);
			//textView.setText(reString);
			runOnUiThread(new Runnable() {
				
			
				@Override
				public void run() {
					textView.setText(reString);
					
				}
			});
			
		}
		
		
	}

}
