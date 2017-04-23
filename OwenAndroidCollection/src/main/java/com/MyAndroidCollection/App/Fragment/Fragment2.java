package com.MyAndroidCollection.App.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;

public class Fragment2 extends Fragment {
	
	private static final String TAG ="Fragment2";
	
	HwIDListener callBack;


	/***
	 * . 需要实例化的资源id；

	2. fragment需要在哪个视图组下面插入，第二个变量就是这个视图组；

	3. 标识fragment的布局是否在inflate中需要绑定到该ViewGroup（本例中的false表示系统已经在container这个ViewGroup中插入了fragment的布局，如果再传一个true就会产生两个布局了）。

	现在我们知道如何通过一个layout来创建一个fragment了。下一步我们就需要将这个fragment加入到我们的应用中了。
	
	为了重用Fragment UI，我们就需要将该Fragment建立成一个可以自包含（自闭）的系统，拥有自己的layout和行为。
	一旦定义了这些可重用的fragments，就可以将他们绑定到一个activity上，实现全部的活动UI。
	很多时候我们想要在两个fragments间进行通信（例如根据用户输入改变内容），
	所有的Fragment间通信都是通过他们所依附的Activity，他们之间永远不能够直接通信。




	 */
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup contaier,Bundle bundle){
		return inflater.inflate(R.layout.fragment2, contaier,false);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Button button = (Button) getActivity().findViewById(R.id.fragment2_button);
		
		//注意要在fragment1_text 中已经加载的情况下，否则textView 为 null
	
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textView = (TextView) getActivity().findViewById(
						R.id.fragment1_text);
				if (textView != null) {
					Toast.makeText(getActivity(), textView.getText()+"======",
							Toast.LENGTH_LONG).show();
				}else {
					LogX.i("===", "textView is null");
				}
			
			}
		});
		
		Button fragment2_cancel = (Button) getActivity().findViewById(R.id.fragment2_cancel);
		fragment2_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				callBack.onCancel();
			}
		});
		Button fragment2_findpassword = (Button) getActivity().findViewById(R.id.fragment2_findpassword);
		fragment2_findpassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				callBack.onFindPassword(0);
				
			}
		});
	}
	

	
    @Override
    public void onAttach(Activity activity) {   
        super.onAttach(activity);   
        callBack=(HwIDListener) activity;
        
        LogX.d(TAG, "onAttach");   
    }   
    

	public void fragment2Fun1(int position) {
		LogX.i(TAG, "fragment2Fun1 : position=" +position);
		
	}
	
	public void fragment2Fun1() {
		LogX.i(TAG, "fragment2Fun1 : position=" );
		
	}
	
	
}
