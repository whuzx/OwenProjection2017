package com.MyAndroidCollection.App;

import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class MySpeak extends Activity {
	
	private TextToSpeech mSpeech=null;
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout=new LinearLayout(this);
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.FILL_PARENT);
		linearLayout.setLayoutParams(params);
		
		Button speakBtnButton=new Button(this);
		LayoutParams params1=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		speakBtnButton.setLayoutParams(params1);
		speakBtnButton.setText("Speak");
		
		linearLayout.addView(speakBtnButton);
		setContentView(linearLayout);
		
		speakBtnButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	Speech.speak("hello, i am zhou xiong",
              //          TextToSpeech.QUEUE_FLUSH, null);
				
				mSpeech.speak("Welcome Android", TextToSpeech.QUEUE_FLUSH, null);
			}
		});
		
		
		
		
	
	
	 mSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

         @Override
         public void onInit(int status) {
             // TODO Auto-generated method stub
             if (status == TextToSpeech.SUCCESS) {
                 int result = mSpeech.setLanguage(Locale.ENGLISH);
                 if (result == TextToSpeech.LANG_MISSING_DATA
                         || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                     Log.e("lanageTag", "not use");
                 } else {
                     mSpeech.speak("i love you", TextToSpeech.QUEUE_FLUSH,
                             null);
                 }
             }
         }
     });


}
}
	
	



