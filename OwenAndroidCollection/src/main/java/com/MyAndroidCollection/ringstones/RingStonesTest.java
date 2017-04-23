package com.MyAndroidCollection.ringstones;

import java.io.File;
import java.util.List;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.MyAndroidCollection.util.LogX;
import com.MyAndroidCollection.view.BaseActivity;
//http://www.jizhuomi.com/android/example/360.html
public class RingStonesTest extends BaseActivity {

	@Override
	public void onCreate(Bundle savedBundle) {
			initResource();
			super.onCreate(savedBundle);
			testResouce();
		}
	
	
	private void initResource() {
		setNumber(2);		
	}
	
	private void testResouce() {
		List<Button> btns=getButtons();
		
		if(btns.size()> 0){
			btns.get(0).setText("change defaut value0000");	
			btns.get(0).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				
					LogX.i("owen", "onClick");
					//setMyRingtone("/mnt/sdcard/test/c.mp3") ;
					setVoice("/mnt/sdcard/test/b.mp3",0);
				}
			});
		}
		
	
	}
	
	
	public void setMyRingtone(String path) {
		File sdfile = new File(path);
		LogX.i("owen", "getAbsolutePath = " + sdfile.getAbsolutePath());
		ContentValues values = new ContentValues();
		values.put(MediaStore.MediaColumns.DATA, sdfile.getAbsolutePath());
		values.put(MediaStore.MediaColumns.TITLE, sdfile.getName());
		values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/*");
		values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
		values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
		values.put(MediaStore.Audio.Media.IS_ALARM, false);
		values.put(MediaStore.Audio.Media.IS_MUSIC, false);
		Uri uri = MediaStore.Audio.Media.getContentUriForPath(sdfile
				.getAbsolutePath());
		LogX.i("owen", "uri="  + uri.toString());
		//12-14 23:05:09.232: I/owen(5722): [main-1]uri=content://media/internal/audio/media(MyAndroidCollection/RingStonesTest.java:65)

		Uri newUri = this.getContentResolver().insert(uri, values);
		//12-14 23:06:33.436: I/owen(7607): [main-1]newUri=content://media/internal/audio/media/132(MyAndroidCollection/RingStonesTest.java:69)

		LogX.i("owen", "newUri="  + newUri.toString());
		RingtoneManager.setActualDefaultRingtoneUri(this,
				RingtoneManager.TYPE_RINGTONE, newUri);
		
		 Ringtone rt = RingtoneManager.getRingtone(this, newUri);     
		 rt.play();  
		Toast.makeText(getApplicationContext(), "设置来电铃声成功！", Toast.LENGTH_SHORT)
				.show();
		System.out.println("setMyRingtone()-----铃声");
	}
	

public interface AppConstant {     
     public static final int RINGTONE = 0;                   //铃声           
     public static final int NOTIFICATION = 1;               //通知音           
     public static final int ALARM = 2;                      //闹钟         
     public static final int ALL = 3;                        //所有声音           
} 


	
	private void setVoice(String path2,int id)       
	{     
	     ContentValues cv = new ContentValues();     
	     Uri newUri = null;        
	     Uri uri = MediaStore.Audio.Media.getContentUriForPath(path2);     
	      
	     // 查询音乐文件在媒体库是否存在       
	     Cursor cursor = this.getContentResolver().query(uri, null, MediaStore.MediaColumns.DATA + "=?", new String[] { path2 },null);        
	     if (cursor.moveToFirst() && cursor.getCount() > 0)      
	     {       
	          String _id = cursor.getString(0);       
	          switch (id) {        
	          case AppConstant.RINGTONE:        
	               cv.put(MediaStore.Audio.Media.IS_RINGTONE, true);       
	               cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);        
	               cv.put(MediaStore.Audio.Media.IS_ALARM, false);       
	               cv.put(MediaStore.Audio.Media.IS_MUSIC, false);       
	               break;       
	          case AppConstant.NOTIFICATION:        
	               cv.put(MediaStore.Audio.Media.IS_RINGTONE, false);       
	               cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);      
	               cv.put(MediaStore.Audio.Media.IS_ALARM, false);     
	               cv.put(MediaStore.Audio.Media.IS_MUSIC, false);     
	               break;     
	          case AppConstant.ALARM:     
	               cv.put(MediaStore.Audio.Media.IS_RINGTONE, false);     
	               cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);     
	               cv.put(MediaStore.Audio.Media.IS_ALARM, true);     
	               cv.put(MediaStore.Audio.Media.IS_MUSIC, false);     
	               break;     
	          case AppConstant.ALL:     
	               cv.put(MediaStore.Audio.Media.IS_RINGTONE, true);     
	               cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);     
	               cv.put(MediaStore.Audio.Media.IS_ALARM, true);     
	               cv.put(MediaStore.Audio.Media.IS_MUSIC, false);     
	               break;     
	          default:     
	               break;     
	      }     
	    
	      // 把需要设为铃声的歌曲更新铃声库     
	      getContentResolver().update(uri, cv, MediaStore.MediaColumns.DATA + "=?",new String[] { path2 });     
	      newUri = ContentUris.withAppendedId(uri, Long.valueOf(_id));     
	      // 一下为关键代码：     
	      switch (id) {     
	      case AppConstant.RINGTONE:     
	           RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_RINGTONE, newUri);     
	           break;     
	      case AppConstant.NOTIFICATION:     
	           RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION, newUri);     
	           break;     
	      case AppConstant.ALARM:     
	           RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALARM, newUri);     
	           break;     
	      case AppConstant.ALL:     
	           RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALL, newUri);     
	           break;     
	      default:     
	           break;     
	      }     
	    
	      //播放铃声     
	       Ringtone rt = RingtoneManager.getRingtone(this, newUri);     
	      rt.play();     
	   }     
	}    
}
