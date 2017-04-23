package com.MyAndroidCollection.App.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DialogFragment1 extends DialogFragment {

	Button btn_close;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				return container;
//		View v = inflater.inflate(R.layout.search, container, false);
//		// 初始化控件
//		btn_close = (Button) v.findViewById(R.id.btn_cancel);
//		btn_close.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// 关闭对话框
//				dismiss();
//			}
//		});
//		return v;
	}
	
//	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		return new Dialog().set
//		
//	}

}
