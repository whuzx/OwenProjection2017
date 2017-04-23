package com.MyAndroidCollection.App;

import Owen.Data.sington;
import android.app.Activity;
import android.os.Bundle;

import com.MyCustomControl.MyNoteBook;

public class MyControl extends Activity {
	public  void onCreate(Bundle onSavedBundleInstance)
	{
		super.onCreate(onSavedBundleInstance);
		MyNoteBook myNoteBook=new MyNoteBook(this);
		this.setContentView(myNoteBook);
		
		sington.getSingtonInstance();
		
	}

}
