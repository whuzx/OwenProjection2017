package com.MyAndroidCollection.Widget;



import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.MyAndroidCollection.R;
import com.MyCustomControl.TextExt;
import com.MyCustomControl.onExtClickListener;

public class CustomActivity extends Activity {
	boolean isChange = true;
	  final int DIALOG_DATEPICKER = 1;
	    Calendar c;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.widget);
		
		final TextExt te = new TextExt(this, "自定义组件", R.drawable.icon);
		
		//onExtClickListener 相当于自定义的事件,参数为text
		te.setOnExtClickListener(new onExtClickListener() {
			public void onclickListenr(String text) {
				// TODO Auto-generated method stub
				Toast.makeText(CustomActivity.this, text, 1000).show();
			}

			@Override
			public void onclickListenr1(View viw, String text) {
				// TODO Auto-generated method stub
				Log.e("origin view is",viw.toString());
				
			}

		});
		
		LinearLayout ly = (LinearLayout) findViewById(R.id.testWidget);
		ly.addView(te);//增加UI元素
		
		Button btn = (Button) findViewById(R.id.Button01);
		Button btn2 = (Button) findViewById(R.id.Button02);
		
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (isChange) {
					te.setImage(android.R.drawable.btn_dialog);
				} else {
					te.setDrawable(getResources().getDrawable(R.drawable.icon));
				}
				isChange = !isChange;
			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isChange) {
					te.setText("只可以改变一次喔 ");
				} else {
					te.setText("还可以改变喔 ");
				}
				isChange = !isChange;
				
			}
		});
	    /*获取当前的日期*/
        c = Calendar.getInstance();
        
        showDialog(DIALOG_DATEPICKER);
	}
	
    @Override
    protected Dialog onCreateDialog (int id) {
        switch (id) {
        case DIALOG_DATEPICKER:
            return new DatePickerDialog(this,
                    new OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker v, int y,
                                int m, int d) {
                            /*
                             * 这是设置好日期后的回调函数，
                             * 可以根据设置的内容更新用户界面
                             * 或者系统时间。
                             */
                        }
                     }, c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH), 
                    c.get(Calendar.DAY_OF_MONTH));   
        default:
            return null;
        }
    }
}