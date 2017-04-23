package com.MyAndroidCollection.App;

import android.app.Application;
import android.graphics.Bitmap;
//主要用来定义全局变量，单例模式
public class MyApp extends Application{
	
	private Bitmap mBitmap;

	public void setmBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}

	public Bitmap getmBitmap() {
		return mBitmap;
	}
	
}
