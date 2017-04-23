package com.MyAndroidCollection.util;

import android.R.integer;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class ScreenTools {

	private static final String TAG = "ScreenTools";

	public static final void getAndroidScreenInfo(Context context) {

		DisplayMetrics dm = new DisplayMetrics();

		
		dm = context.getResources().getDisplayMetrics();

		float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）

		int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）

		float xdpi = dm.xdpi;

		float ydpi = dm.ydpi;
		
		int screenWidth, screenHeight;

		Log.e(TAG + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);

		Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI="
				+ densityDPI);

		screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）

		screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）

		Log.e(TAG + "  DisplayMetrics", "screenWidth=" + screenWidth
				+ "; screenHeight=" + screenHeight);
	}

}
