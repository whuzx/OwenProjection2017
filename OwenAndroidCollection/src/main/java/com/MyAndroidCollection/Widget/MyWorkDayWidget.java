package com.MyAndroidCollection.Widget;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import com.MyAndroidCollection.R;

public class MyWorkDayWidget extends AppWidgetProvider {
	
	public  void onUpdate(Context context,AppWidgetManager appWidgetManager,int[] appWidgetIds)
	{
		Timer timer=new Timer();
		  timer.scheduleAtFixedRate(new MyTime(context,appWidgetManager), 1, 60000);   
	      super.onUpdate(context, appWidgetManager, appWidgetIds);   
		
	}
	
	private class MyTime extends TimerTask{   
        RemoteViews remoteViews;   
        AppWidgetManager appWidgetManager;   
        ComponentName thisWidget;   
           
        public MyTime(Context context,AppWidgetManager appWidgetManager){   
            this.appWidgetManager = appWidgetManager;   
            remoteViews = new RemoteViews(context.getPackageName(),R.layout.myworkwidget);   
               
            thisWidget = new ComponentName(context,MyWorkDayWidget.class);   
        }   
        public void run() {   
        	//date.getMonth().toString();      
       //android.util.Log.
            Date date = new Date();   
           // date.getMonth();
           // date.getDay();
          //  date.getHours();
            Calendar calendar=Calendar.getInstance();
            TimeZone timeZone=TimeZone.getTimeZone("GMT");
            calendar.setTimeZone(timeZone);
            calendar.clear(); 
            calendar.set(2009, 7, 8);
            
            Log.e("Widget","month:"+Integer.toString(date.getMonth())+"day:"+Integer.toString(date.getDay())+"hour"+Integer.toString(date.getHours()));
           // Calendar calendar = new GregorianCalendar(2009,07,8);     
            long days = (((date.getTime()-calendar.getTimeInMillis())/1000))/86400;   
            remoteViews.setTextViewText(R.id.widgettitle, "I have worked for" + days+"days");   
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);   
               
        }   

}
}
