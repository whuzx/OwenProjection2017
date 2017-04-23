package com.MyAndroidCollection.Net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.MyAndroidCollection.R;

public class HttpAsyncGetImage extends Activity {
	private TextView textView;
	private String myString;
	private ImageView img;
	private String imgpath="http://www.tracefact.net/images/jimmy4.jpg";
	private  static String TAG="HttpGET";

	public void onCreate(Bundle onSavedInstanceState) {
		super.onCreate(onSavedInstanceState);
		setContentView(R.layout.net);

		textView = (TextView) findViewById(R.id.txtviewnet);
		textView.setText(" Test AnsynTask ");

		// img=(ImageView) findViewById(R.id.netImageView);
		// img.setImageResource(R.drawable.icon);

	/*	Properties prop = System.getProperties();
		prop.setProperty("http.proxyHost", "172.21.134.1");
		prop.setProperty("http.proxyPort", "80");
*/
		PageTask task = new PageTask();
		task.execute("http://www.google.com/ig/api?&weather=hangzhou");

	}
	
	// 设置三种类型参数分别为String,Integer,String
	class PageTask extends AsyncTask<String, Integer, String> {
		private String myString;
		

		// 可变长的输入参数，与AsyncTask.exucute()对应
		/*
		 * @Override protected String doInBackground(String... params) {
		 * 
		 * try {
		 * 
		 * URL myURL = new URL(params[0]); 打开URL链接 URLConnection ucon =
		 * myURL.openConnection();
		 * 
		 * 使用InputStreams，从URLConnection读取数据 InputStream is =
		 * ucon.getInputStream();
		 * 
		 * BufferedInputStream bis = new BufferedInputStream(is);
		 * 
		 * 
		 * 用ByteArrayBuffer做缓存 ByteArrayBuffer baf = new ByteArrayBuffer(0);
		 * int current = 0; int count=0; while((current = bis.read()) != -1){
		 * baf.append((byte)current); count+=current; // if (Length > 0) { // //
		 * 如果知道响应的长度，调用publishProgress（）更新进度 //publishProgress((int) ((count /
		 * (float) Length) * 100)); // } // 为了在模拟器中清楚地看到进度，让线程休眠100ms
		 * //Thread.sleep(100); }
		 * 
		 * 将缓存的内容转化为String， 用UTF-8编码 myString =
		 * EncodingUtils.getString(baf.toByteArray(), "UTF-8"); //myString = new
		 * String(baf.toByteArray());
		 * 
		 * } catch (Exception e) { } return myString;
		 * 
		 * }
		 */
		@Override
		protected String doInBackground(String... params) {

			long length=-1 ;
			try {

				HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
				HttpConnectionParams.setSoTimeout(httpParams, 30000);

			/*	HttpHost proxy = new HttpHost("172.29.8.29", 80);
				httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
*/
				DefaultHttpClient client = new DefaultHttpClient(httpParams);

				String aString = "http://www.google.com/ig/api?&weather=hangzhou";
				HttpGet get = new HttpGet(params[0]);

				HttpResponse response = client.execute(get);

				HttpEntity entity = response.getEntity();

				 length = entity.getContentLength();

				Log.e("param0", Long.toString(length));
				InputStream is = entity.getContent();
				String s = null;
				if (is != null) {
					Log.e("param111", "doInBackground1");
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buf = new byte[128];
					int ch = -1;
					int count = 0;
					while ((ch = is.read(buf)) != -1) {
						baos.write(buf, 0, ch);
						count += ch;
						  if (length > 0) { //
						  //如果知道响应的长度，调用publishProgress（）更新进度
						   publishProgress((int) ((count / (float) length) *100)); 
						   } 
						  // 为了在模拟器中清楚地看到进度，让线程休眠100ms
						  Thread.sleep(200);
						
					}
					s = new String(baos.toByteArray());
				}
				// 返回结果
				Log.e("result", s.toString());
				return s;
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("error", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result) {
			// 返回HTML页面的内容
			textView.setText(result);
		}

		@Override
		protected void onPreExecute() {
			// 任务启动，可以在这里显示一个对话框，这里简单处理
			textView.setText("started");
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// 更新进度
			textView.setText(values[0]);
		}
     }
	
	}

	

