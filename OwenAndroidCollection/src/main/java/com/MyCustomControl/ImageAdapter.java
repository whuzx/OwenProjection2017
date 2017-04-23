package com.MyCustomControl;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.MyAndroidCollection.R;

public class ImageAdapter extends BaseAdapter{
	 int mGalleryItemBackground;   
	 int txtsize;
	 private Context mContext;
	 public Integer[] mImageIds = 
	      {            
			 R.drawable.sample_1,            
			 R.drawable.sample_2,            
			 R.drawable.sample_3,            
			 R.drawable.sample_4,            
			 R.drawable.sample_5,            
			 R.drawable.sample_6,            
			 R.drawable.sample_7   
			 };
	 
	  public ImageAdapter(Context c) {       
		  mContext = c;        
		  TypedArray a = c.obtainStyledAttributes(R.styleable.HelloGallery);        
		  mGalleryItemBackground = a.getResourceId(               
				  R.styleable.HelloGallery_android_galleryItemBackground, 1);  
		 // txtsize=a.getResourceId(R.styleable.HelloGallery_txtsize, 24);
		  
		  a.recycle();    
		  }   
	  public int getCount() 
	  {        
			 // return mImageIds.length;  
		  return Integer.MAX_VALUE;
			  
	  }    
		  public Object getItem(int position)
		  {        
			  return position;   
			  
		  }    
		  public long getItemId(int position) 
		  {        return position;    
		  }   
		  public View getView(int position, View convertView, ViewGroup parent) 
		  {       
			  ImageView i = new ImageView(mContext);       
			  i.setImageResource(mImageIds[position%mImageIds.length]);       
			  i.setLayoutParams(new Gallery.LayoutParams(10, 100));        
			  i.setScaleType(ImageView.ScaleType.FIT_XY);        
			  i.setBackgroundResource(mGalleryItemBackground);       
			  return i;    
	}

}
