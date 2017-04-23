package com.MyAndroidCollection.App.Fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;

public class CustomDialog2 extends DialogFragment {



	private static final String TAG = "CustomDialog2";

	public CustomDialog2() {

	}

	static DialogClickListener mListener;
	static HwIDListener hwIDListener;

	public static CustomDialog2 newInstance(String title, String message,
			DialogClickListener listener) {
		CustomDialog2 frag = new CustomDialog2();
		Bundle b = new Bundle();
		b.putString("title", title);
		b.putString("message", message);
		frag.setArguments(b);
		mListener = listener;
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		hwIDListener = (HwIDListener) (DialogFragmentActivity) getActivity();
		final Dialog dialog = new Dialog(getActivity(), R.style.MyDialogStyle);

		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// why? LayoutInflater.from(getActivity()).inflate(resource, root,
		// attachToRoot);

		if (inflater == null) {
			LogX.i("ERR", "inflater is null");
			return new Dialog(getActivity());
		}

		View view = inflater.inflate(R.layout.fragmentdialog2, null, false);

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
				// dialog.dismiss();

				if (hwIDListener != null) {
					hwIDListener.onOK();
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

				// dialog.dismiss();
			}

		});
		
		
		Button findpassword=(Button) view.findViewById(R.id.fragdiag2_findpwd);
		
		findpassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (hwIDListener != null) {
					hwIDListener.onFindPassword(0);
					
				}
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


}
