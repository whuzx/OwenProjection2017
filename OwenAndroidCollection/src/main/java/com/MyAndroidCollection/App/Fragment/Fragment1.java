package com.MyAndroidCollection.App.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;

public class Fragment1 extends Fragment {   
    public static final String TAG = "Fragment1";  
    HwIDListener callBack;
    
    // Fragment 向Activity 传递事件
//    public interface onListener {	
//    	  public void onEnterPassword(int position);
//    	  public void onCancel();
//	
//	}
//    
    
        
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
        LogX.d(TAG, "onCreateView");   
        return inflater.inflate(R.layout.fragment1, container, false);   
        
    }   
        
    @Override
    public void onAttach(Activity activity) {   
        super.onAttach(activity);   
        callBack=(HwIDListener) activity;
        
        
  
        LogX.d(TAG, "onAttach");   
    }   
        
    /***
     * Called to do initial creation of a fragment. This is called after onAttach(Activity) and before onCreateView(LayoutInflater, ViewGroup, Bundle). 

     */
    @Override
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        LogX.d(TAG, "onCreate");   
    }   
        
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {   
        super.onActivityCreated(savedInstanceState);   
        Button button = (Button) getActivity().findViewById(R.id.fragment1_start);
        Button cancel = (Button) getActivity().findViewById(R.id.fragment1_cancel);
		
		//注意要在fragment1_text 中已经加载的情况下，否则textView 为 null
	
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack.onEnterPassword(v.getId());
			
			}
		});
		
		
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack.onCancel();
			
			}
		});
		
        LogX.d(TAG, "onActivityCreated");   
    }   
        
    @Override
    public void onStart() {   
        super.onStart();   
        LogX.d(TAG, "onStart");   
    }   
        
    @Override
    public void onResume() {   
        super.onResume();   
        LogX.d(TAG, "onResume");   
    }   
        
    @Override
    public void onPause() {   
        super.onPause();   
        LogX.d(TAG, "onPause");   
    }   
        
    @Override
    public void onStop() {   
        super.onStop();   
        LogX.d(TAG, "onStop");   
    }   
        
    @Override
    public void onDestroyView() {   
        super.onDestroyView();   
        LogX.d(TAG, "onDestroyView");   
    }   
        
    @Override
    public void onDestroy() {   
        super.onDestroy();   
        LogX.d(TAG, "onDestroy");   
    }   
        
    @Override
    public void onDetach() {   
        super.onDetach();   
        LogX.d(TAG, "onDetach");   
    }   
    
    
    public void testFun(){
    	
    	//最好不要这么玩，Activity 对外暴露了callback。
    	MainFragment activity=(MainFragment)getActivity();
    	HwIDListener listener=activity.myCallBack;
    }
        
}
