package com.MyAndroidCollection.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.MyAndroidCollection.R;

public class TransContentView extends Activity implements OnClickListener {
	
	Button view0tranfbtn;
	Button view0btn1;
	Button view0btn2;
	
	
	Button view1tranfbtn;
	Button view1btn1;
	Button view1btn2;
	
	
	private String TAG="TransContentView";
	
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.contentview0);
	        
	        view0tranfbtn=(Button) findViewById(R.id.transcontentview_btn0);
	        view0btn1=(Button) findViewById(R.id.transcontentview_btn1);
	        view0btn2=(Button) findViewById(R.id.transcontentview_btn2);
	     
	        //btn1.setOnClickListener(clickListener);
	        
	        view0tranfbtn.setOnClickListener(this);
	        view0btn1.setOnClickListener(this);
	        view0btn2.setOnClickListener(this);
	        
		    
	       // Toast.makeText(this, "Solution 3 start", Toast.LENGTH_SHORT).show();      
	   }
	   
//	   OnClickListener clickListener=new OnClickListener(){
//		   
//		   @Override
//		   public void onClick(View v) {
//				
//				switch (v.getId()) {
//				case R.id.transcontentview_btn1:
//					Log.d(TAG, "clicked button1");
//					showDialog("clicked button","button1");
//					break;
//					
//			    case R.id.transcontentview_btn2:
//			    	Log.d(TAG, "clicked button2");
//			    	showDialog("clicked button","button2");
//					break;
//					
//				case R.id.transcontentview1_btn1:
//					Log.d(TAG, "clicked button1");
//					showDialog("clicked button","button1");
//					break;
//					
//			    case R.id.transcontentview1_btn2:
//			    	Log.d(TAG, "clicked button2");
//			    	showDialog("clicked button","button2");
//					break;
//					
//				default:
//					break;
//				}
//			
//			}
//	   };
	   
//	OnClickListener clickListener = new OnClickListener() {
//
//		@Override
//		public void onClick(View v) {
//
//			if (v == view0tranfbtn) {
//				Log.d(TAG, "clicked transcontentview tranf button");
//				goToLayout2();
//			} else if (v == view0btn2) {
//				Log.d(TAG, "clicked transcontentview  button2");
//				showDialog("clicked button", "button2");
//			} else if (v == view0btn1) {
//				Log.d(TAG, "clicked  transcontentview button1");
//				showDialog("clicked button", "button1");
//				
//			} else if (v == view1btn2) {
//				Log.d(TAG, "clicked  transcontentview1 button2");
//				showDialog("clicked button", "button2");
//			} else if (v == view1btn1) {
//				Log.d(TAG, "clicked  transcontentview1 button1");
//				showDialog("clicked button", "button1");
//
//			}else if (v == view1tranfbtn) {
//				Log.d(TAG, "clicked  transcontentview1 tranf button");
//                goToLayout1();
//			}
//
//		}
//	};
		   	   
	   
	   
	   
//	@Override
//	public void onClick(View v) {
//		
//		showId(v);
//		
//		switch (v.getId()) {
//		
//		case R.id.transcontentview_btn1:
//			Log.d(TAG, "clicked button1");
//			showDialog("clicked button","transcontentview button1");
//			break;
//			
//		case R.id.transcontentview_btn0:
//			Log.d(TAG, "clicked transcontentview tranf button");
//			goToLayout2();
//			break;
//			
//	    case R.id.transcontentview_btn2:
//	    	Log.d(TAG, "clicked button2");
//	    	showDialog("clicked button","transcontentview button2");
//			break;
//			
//		case R.id.transcontentview1_btn1:
//			Log.d(TAG, "clicked button1");
//			showDialog("clicked button","transcontentview1 button1");
//			break;
//			
//	    case R.id.transcontentview1_btn2:
//	    	Log.d(TAG, "clicked button2");
//	    	showDialog("clicked button","transcontentview1 button2");
//			break;
//			
//		default:
//			break;
//		}
//	
//	}
//	
	private void showId(View v) {
		
		Log.d(TAG, "view id: " + v.getId());
		
		Log.d(TAG, "view0 btn0: " + R.id.transcontentview_btn0);
		Log.d(TAG, "view0 btn1: " + R.id.transcontentview_btn1);
		Log.d(TAG, "view0 btn2: " + R.id.transcontentview_btn2);
		
		Log.d(TAG, "view1 btn0: " + R.id.transcontentview1_btn0);
		Log.d(TAG, "view1 btn1: " + R.id.transcontentview1_btn1);
		Log.d(TAG, "view1 btn2: " + R.id.transcontentview1_btn2);
	
	}



	private void goToLayout2() {
		
		    setContentView(R.layout.contentview1);
		   
		    view1tranfbtn = (Button) findViewById(R.id.transcontentview1_btn0);
		    view1btn1=(Button) findViewById(R.id.transcontentview1_btn1);
		    view1btn2=(Button) findViewById(R.id.transcontentview1_btn2);

		    view1btn1.setOnClickListener(this);
		    view1btn2.setOnClickListener(this);
		    view1tranfbtn.setOnClickListener(this);
		    
	        
//	        view1tranfbtn.setOnClickListener(new Button.OnClickListener() {
//	            public void onClick(View v) {
//	            	Log.d(TAG, "view1 tranf btn:" +v.getId());
//	                goToLayout1();
//	            }
//	        });

		
	}

	protected void goToLayout1() {

		setContentView(R.layout.contentview0);
//
		view0tranfbtn = (Button) findViewById(R.id.transcontentview_btn0);
		view0btn1 = (Button) findViewById(R.id.transcontentview_btn1);
		view0btn2 = (Button) findViewById(R.id.transcontentview_btn2);

		view0tranfbtn.setOnClickListener(this);
		view0btn1.setOnClickListener(this);
		view0btn2.setOnClickListener(this);

	}

	public void showDialog(String msg,String viewtext){
		Log.d(TAG, "showDialog");
	     new AlertDialog.Builder(this).setTitle("clicked " + viewtext)
	  .setMessage(msg).setPositiveButton(
			android.R.string.ok, null).show();
	}



	@Override
	public void onClick(View v) {
		
		if (v == view0tranfbtn) {
			Log.d(TAG, "clicked transcontentview tranf button");
			goToLayout2();
		} else if (v == view0btn2) {
			Log.d(TAG, "clicked transcontentview  button2");
			showDialog("clicked button", "button2");
		} else if (v == view0btn1) {
			Log.d(TAG, "clicked  transcontentview button1");
			showDialog("clicked button", "button1");
			
		} else if (v == view1btn2) {
			Log.d(TAG, "clicked  transcontentview1 button2");
			showDialog("clicked button", "button2");
		} else if (v == view1btn1) {
			Log.d(TAG, "clicked  transcontentview1 button1");
			showDialog("clicked button", "button1");

		}else if (v == view1tranfbtn) {
			Log.d(TAG, "clicked  transcontentview1 tranf button");
            goToLayout1();
		}

		
	}
	
}
