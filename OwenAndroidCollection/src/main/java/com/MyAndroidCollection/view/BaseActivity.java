package com.MyAndroidCollection.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class BaseActivity extends Activity {
	
	
	private List<Button> buttons=new ArrayList<Button>();
	private List<TextView> textviews=new ArrayList<TextView>();
	private int numbers=1;

	@Override
	public void onCreate(Bundle savedBundle){
		super.onCreate( savedBundle);
		initResource(this);
	}
	
	public List<Button> getButtons(){
		return buttons;
	}
		
	public List<TextView> getTextViews(){
		return textviews;
	}
	
	public void setNumber(int number){
		this.numbers=number;
	}
	
	private void initResource(Context context) {
		
		  ScrollView scrollView =new ScrollView(context);
		    
		   
		LinearLayout linearLayout=new LinearLayout(context);
        //this is demnosfasdasdfasdfas


		//
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
	 
	 
	    for(int i=0;i<numbers;i++){
	    	Button btn=new Button(context);
	    	btn.setText("btn"+String.valueOf(i));
	    	TextView textVew=new TextView(context);
	    	textVew.setText("textVew"+String.valueOf(i));
	    	buttons.add(btn);
	    	textviews.add(textVew);
	    	linearLayout.addView(btn);
	      	linearLayout.addView(textVew);	
	    } 
	     scrollView.addView(linearLayout);
	     
	     setContentView(scrollView);	
	}

}
