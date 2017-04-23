package com.MyAndroidCollection.App;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.MyAndroidCollection.R;



public class DialogDemo extends Activity {
	
	private Builder myDialog;
	private Button btnButton;

	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dialog);
	        btnButton=(Button) findViewById(R.id.combtn1);
	        btnButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int number=getRandNumber(7);
					testDialog(number);
					
				}
			});
		
	  }

	protected void testDialog(int number) {
		// TODO Auto-generated method stub
		switch (number) {
		case 1:
			testDialog1();
			break;
		case 2:
			testDialog2();
			break;
		case 3:
			testDialog3();
			break;
		case 4:
			testDialog4();
			break;
		case 5:
			testDialog5();
			break;
		case 6:
				testDialog6();
				break;
			case 7:
				testDialog7();
				break;

		default:
			testDialog1();
			break;
		}
		
	}

	protected int getRandNumber(int i) {
		return (int)(Math.random()*i);
		
		
	}

	private void testDialog7() {
		// TODO Auto-generated method stub
		ImageView img=new ImageView(this);
		img.setImageResource(R.drawable.sample_0);
		
		new AlertDialog.Builder(this)
		.setTitle("Image")
		.setView(img)
		.setPositiveButton("yes", null)
		.show();
		
		
	}

	private void testDialog6() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
		.setTitle("列表框")
		.setItems(new String[]{"items1","Item2","Itme3","Itme4"}, null)
		.setNegativeButton("确定", null)
		.show();
		
	}

	private void testDialog5() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
		.setTitle("请选择")
		.setMultiChoiceItems(new String[]{"items1","Item2","Itme3","Itme4"}, null,null)
		.setPositiveButton("确定", null)
		.setNegativeButton("取消", null)
		.show();
		
	}

	private void testDialog4() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
		.setTitle("请选择")
		.setIcon(R.drawable.icon)
	    .setSingleChoiceItems(new String[]{"items1","Item2","Itme3","Itme4"}, 0, 
	    		new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						
					}
				})
		.setNegativeButton("取消", null)
		.show();
		
	}

	private void testDialog3() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
		.setTitle("请输入")
		.setIcon(R.drawable.icon)
		.setView(new EditText(this))//可传入任何视图对象
		.setPositiveButton("确定", null)
		.setNegativeButton("取消", null)
		.show();
		
	}

	private void testDialog2() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this).setTitle("确认")
		.setMessage("确定吗？")
		.setPositiveButton("是", null)
		.setNegativeButton("否", null)
		.show();
	}

	private void testDialog1() {
		// TODO Auto-generated method stub
	     new AlertDialog.Builder(this)     

         .setTitle("标题")  

         .setMessage("简单消息框")  

         .setPositiveButton("确定", null)  

        .show(); 
	     
		
	}

}
