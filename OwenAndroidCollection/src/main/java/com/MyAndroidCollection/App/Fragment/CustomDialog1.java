package com.MyAndroidCollection.App.Fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;

/***
 * DialogFragment的实例newInstance()已经在上一次学习笔记中实现。我们创建dialog的UI，
 * 可以通过重写DialogFragment的两个函数当中的一个来实现，这两个函数是onCreateView()和onCreateDialog()，
 * 前者返回view，后者返回dialog，如同通过AlertDialog.Builder构造一样。


 * @author z0020101
 *
 */
public class CustomDialog1 extends DialogFragment {

	private static final String TAG="CustomDialog1";


	public CustomDialog1(){  
		
	}  

	
	static DialogClickListener mListener;
	static HwIDListener hwIDListener;

	public static CustomDialog1 newInstance(String title, String message,
			DialogClickListener listener) {
		CustomDialog1 frag = new CustomDialog1();
		Bundle b = new Bundle();
		b.putString("title", title);
		b.putString("message", message);
		frag.setArguments(b);
		mListener = listener;
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		hwIDListener=(HwIDListener)(DialogFragmentActivity)getActivity();
		final Dialog dialog = new Dialog(getActivity(), R.style.MyDialogStyle);

		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// why? LayoutInflater.from(getActivity()).inflate(resource, root,
		// attachToRoot);
		
		if (inflater == null) {
			LogX.i("ERR", "inflater is null");
			return new Dialog(getActivity());
		}
		

		View view = inflater.inflate(R.layout.fragmentdialog, null, false);
		
		if (view == null) {
			LogX.i("ERR", "view is null");
			return new Dialog(getActivity());
		}

		String title = getArguments().getString("title");
		String message = getArguments().getString("message");
		if (title != null && title.length() > 0) {
			TextView t = (TextView) view.findViewById(R.id.title_text_view);
			t.setText(title);
		}

		if (message != null && message.length() > 0) {
			TextView m = (TextView) view.findViewById(R.id.content_text_view);
			m.setText(message);
		}

		View ok = view.findViewById(R.id.dialog_ok);
		View cancel = view.findViewById(R.id.dialog_cancel);

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (mListener != null) {
					mListener.doPositiveClick();
				}
				//dialog.dismiss();
				
				if (hwIDListener != null) {
					hwIDListener.onEnterPassword(0);
				}

			}

		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (mListener != null) {
					mListener.doNegativeClick();
				}
				
				if (hwIDListener != null) {
					hwIDListener.onCancel();
				}
				
				//dialog.dismiss();
			}

		});
		
		
		
		dialog.setCanceledOnTouchOutside(false);

		dialog.setContentView(view);
		
	

		return dialog;
	}
	
	
    @Override //仅用于状态跟踪
    public void onCancel(DialogInterface dialog) {  
        LogX.i(TAG,"onCancel() is called"); 
        super.onCancel(dialog); 
        dialog.dismiss();
    }  

    @Override  //仅用户状态跟踪
    public void onDismiss(DialogInterface dialog) {  
        LogX.i(TAG,"onDismiss() is called"); 
        super.onDismiss(dialog); 
       // dialog.dismiss();
    }  
    




}
