package com.MyAndroidCollection.App;

import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.MyAndroidCollection.R;
import com.MyCustomControl.ImageAdapter;



public class Hellogallery extends Activity implements ViewFactory, OnItemSelectedListener {
    /** Called when the activity is first created. */
	
	private ImageSwitcher imageSwitcher;
	private ImageAdapter imageAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //无title     
        requestWindowFeature(Window.FEATURE_NO_TITLE);     
         //全屏     
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,       
                       WindowManager.LayoutParams. FLAG_FULLSCREEN);    

        
        setContentView(R.layout.hellogallery);
        imageSwitcher=(ImageSwitcher)findViewById(R.id.imageswitcher);
        imageSwitcher.setFactory(this);
        // 设置ImageSwitcher组件显示图像的动画效果
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
               android.R.anim.fade_in));       
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        Gallery g=(Gallery)findViewById(R.id.gallery);
        imageAdapter=new ImageAdapter(this);
        g.setAdapter(imageAdapter);    
        g.setOnItemSelectedListener(this);
        
        g.setOnItemClickListener(new OnItemClickListener() 
        {
        public void onItemClick(AdapterView parent,View view,int position,long id)
        {
        	//imageSwitcher.setImageResource(imageAdapter.mImageIds[position%imageAdapter.mImageIds.length]);
        	Toast.makeText(Hellogallery.this, "" + position, Toast.LENGTH_SHORT).show();
            
        } });
    }
    

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView imageView=new ImageView(this);
		imageView.setBackgroundColor(color.darker_gray);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		return imageView;
	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		imageSwitcher.setImageResource(imageAdapter.mImageIds[arg2%imageAdapter.mImageIds.length]);
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	

  
}