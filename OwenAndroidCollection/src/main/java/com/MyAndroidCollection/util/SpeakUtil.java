package com.MyAndroidCollection.util;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

public class SpeakUtil {
	
	
	public static void speak(Context context) {
		
		LogX.d("owen", "speak");
		
		AccessibilityManager accessibilityManager=(AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
		if (accessibilityManager.isEnabled()) {
			LogX.d("owen", "accessibilityManager is enabled");
			final String msg="Welcome to Android.";
			//TYPE_ANNOUNCEMENT
			//TYPE_WINDOW_STATE_CHANGED
			AccessibilityEvent event=AccessibilityEvent.obtain(AccessibilityEvent.TYPE_ANNOUNCEMENT);
			event.setClassName(context.getClass().getName());
			event.getText().add(msg);
			event.setAddedCount(msg.length());
			accessibilityManager.sendAccessibilityEvent(event);
			
		}
		
	}
	

	
	

}
