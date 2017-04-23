package com.MyAndroidCollection.Thread;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.MyAndroidCollection.R;

public class AsyncTaskDemo extends Activity {
	
	private Button saveButton;
	private ImageView imageView;
	private Bitmap bitmap;
	  public void onCreate(Bundle savedInstanceState) {   
	        super.onCreate(savedInstanceState);   
	        setContentView(R.layout.ayynctask);   
	        saveButton=(Button) findViewById(R.id.asyncBtn);
	        imageView=(ImageView) findViewById(R.id.asyncImageView);
	        imageView.setTag("http://www.tracefact.net/images/jimmy4.jpg");
	        

	        
	        //通过Handler启动线程   
	       
	    	new CanvasImageTaskCall(){
	            public boolean handleMessage(Message msg) {
	                    switch (msg.what) {
	                    case 0:
	                            Log.i("test", "load false");
	                            break;
	                    case 1:
	                            Log.i("test", "loaded success");
	                            break;
	                    default:
	                            break;
	                    }
	                    saveButton.setTextColor(Color.WHITE);
	                    saveButton.setClickable(true);
	                    //imageView.invalidate();//NOTE:invalidate not in UI thread
	                    //imageView.setImageBitmap((Bitmap) imageView.getTag());
	                    // bitmap = (Bitmap) imageView.getTag();//????
	                    return super.handleMessage(msg);
	            }
	    }.execute(imageView);
	    
			//获得全局数据 ok
/*			MyApp myApp=(MyApp) getApplication();
			imageView.setImageBitmap(myApp.getmBitmap());*/
	    
	    imageView.invalidate();//invalidate
	       
	    } 
	  public class CanvasImageTaskCall extends AsyncTask<ImageView, Void, Bitmap> implements Callback{
		    private ImageView gView ;
		    
		    protected Bitmap doInBackground(ImageView... views) {
		            Bitmap bmp = null ;
		            ImageView view = views[0];
		            // 根据iconUrl获取图片并渲染，iconUrl的url放在了view的tag中。
		            if (view.getTag() != null) {
		                    try {
		                       URL url = new URL(view.getTag().toString());
		                       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		                       conn.setDoInput(true);
		                       conn.connect();
		                       InputStream stream = conn.getInputStream();
		                       bmp = BitmapFactory.decodeStream(stream);
		                       stream.close();
		                    } catch (Exception e) {
		                            e.printStackTrace();
		                            Log.v("img", e.getMessage());
		                            Message msg = new Message();
		                            msg.what = 0;
		                            handleMessage(msg);
		                            return null;
		                    }
		            }
		            this.gView = view;
		            return bmp;
		    }

		    protected void onPostExecute(Bitmap bm) {
		            if (bm != null) {
		                this.gView.setImageBitmap(bm);
		             // this.gView.setTag(bm);
//		                this.gView = null ;
		                Message msg = new Message();
		                msg.what = 1;
		                handleMessage(msg);
		            }
		    }

		    public boolean handleMessage(Message msg) {
		        return false;
		    }
		    
		}

}
