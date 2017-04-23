package com.MyAndroidCollection.Net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class HttpGetDemo extends Activity {
	
	private TextView showtTextView=null;
	private static final String LOG_TAG="HttpGetDemo";
	private static final String queryString="https://www4.hicloud.com";
		//"http://www.google.com/ig/api?&weather=hangzhou";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showtTextView=new TextView(this);
		
		//setProxy();
		//getDataViaHttpGet();
		//getDataURLConection();
		doInBackground();
		//getHead();
		this.setContentView(showtTextView);
	}

	private void getDataViaHttpGet() {
		// TODO Auto-generated method stub
		try {
			 HttpGet httpGet=new HttpGet(queryString);
			 
			 HttpParams httpParams=new BasicHttpParams();
			 HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
			 HttpConnectionParams.setSoTimeout(httpParams, 30000);
			 
			 HttpHost proxy=new HttpHost("172.29.8.29",80);
			 httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			 
			 DefaultHttpClient client=new DefaultHttpClient(httpParams);
			 
			 HttpResponse httpResponse=client.execute(httpGet);
			 final int statusCode=httpResponse.getStatusLine().getStatusCode();
			 Log.d(LOG_TAG, "has connected");
			 
			 if(statusCode ==HttpStatus.SC_OK)
			 {
				 HttpEntity responseEntity=httpResponse.getEntity();
				 Long lengthLong=responseEntity.getContentLength();
				 String resultString=EntityUtils.toString(responseEntity);
				 Log.i(LOG_TAG,resultString);
				 showtTextView.setText(resultString);
				 
			 }
			 else {
				throw new IOException("http server error,code="+statusCode);
			}  
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(LOG_TAG, e.getMessage());
		}
	
		 
		
	}


	private void getDataURLConection() {
		String myString;
		try {

			/* 定义获取文件内容的URL */
			URL myURL = new URL("http://www.google.com/ig/api?&weather=hangzhou");
			/* 打开URL链接 */
			URLConnection ucon = myURL.openConnection();

			/* 使用InputStreams，从URLConnection读取数据 */
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);

			/* 用ByteArrayBuffer做缓存 */
			ByteArrayBuffer baf = new ByteArrayBuffer(0);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}

			/* 将缓存的内容转化为String， 用UTF-8编码 */
			myString = EncodingUtils.getString(baf.toByteArray(), "UTF-8");
			// myString = new String(baf.toByteArray());

		} catch (Exception e) {
			/* 捕获异常 */
			myString = e.getMessage();

		}
		/* 设置屏幕显示 */
		showtTextView.setText(myString);
		try {
			FileOutputStream myfileFileOutputStream = openFileOutput(
					"myweatherinfo.txt", MODE_PRIVATE);
			OutputStreamWriter mywrite = new OutputStreamWriter(
					myfileFileOutputStream);
			mywrite.write(myString);
			mywrite.flush();
			Toast.makeText(HttpGetDemo.this, "写入文件成功", Toast.LENGTH_SHORT)
					.show();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(HttpGetDemo.this, "写入文件失败", Toast.LENGTH_SHORT)
					.show();

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setProxy() {
		// TODO Auto-generated method stub
		Properties properties=System.getProperties();
		properties.setProperty("http.proxyHost", "172.29.8.29");
		properties.setProperty("http.proxyPort", "80");	
		
	}


	public String doInBackground() {

		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
			HttpConnectionParams.setSoTimeout(httpParams, 30000);

		//	HttpHost proxy = new HttpHost("172.29.8.29", 80);
		//	httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

			DefaultHttpClient client = new DefaultHttpClient(httpParams);
	
			String aString= "https://www4.hicloud.com";
				//"http://www.google.com/ig/api?&weather=hangzhou";
			HttpGet get = new HttpGet(aString);

			HttpResponse response = client.execute(get);
		
			HttpEntity entity = response.getEntity();

			long length = entity.getContentLength();
		
			Log.e("param0", Long.toString(length));
			InputStream is = entity.getContent();
			String s = null;
			if (is != null) {
				Log.e("param111","doInBackground1");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[128];
				int ch = -1;
				int count = 0;
				while ((ch = is.read(buf)) != -1) {
					baos.write(buf, 0, ch);
					count += ch;
			/*		if (length > 0) {
						// 如果知道响应的长度，调用publishProgress（）更新进度
						publishProgress((int) ((count / (float) length) * 100));
					}
					// 为了在模拟器中清楚地看到进度，让线程休眠100ms
					Thread.sleep(10);*/
				}
				s = new String(baos.toByteArray());
			}
			// 返回结果
			Log.e("result", s.toString());
			showtTextView.setText(s);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("error", e.getMessage());
		}
		return null;
	}
	
	public void getHead()
	{
		StringBuffer s = null ;
		

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
		HttpConnectionParams.setSoTimeout(httpParams, 30000);

		HttpHost proxy = new HttpHost("172.29.8.29", 80);
		httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);

		//创建HttpGet实例
		HttpGet request = new HttpGet("http://www.baidu.com");
		    try 
		    {
		        //连接服务器
		        HttpResponse response =httpClient.execute(request);
		        
		        //读取所有头数据
		        Header[] header = response.getAllHeaders();
		        
		        HashMap<String, String> hm = new HashMap<String, String>();
		        for (int i = 0; i < header.length; i++)
		        {
		          hm.put(header[i].getName(), header[i].getValue());
		        }
		        
		        //取得数据记录
		        HttpEntity entity = response.getEntity();
		        //取得数据记录内容
		        InputStream is = entity.getContent();
		        //显示数据记录内容
		        BufferedReader in = new BufferedReader(new InputStreamReader(is));
		        String str = "";//in.readLine();
		        s = new StringBuffer("");
		        while((str = in.readLine()) != null){ 
		        	System.out.println(str);
		           s.append(str);   
		      }
		        //释放连接
		        httpClient.getConnectionManager().shutdown();
		    }
		    catch (ClientProtocolException e) 
		    {
		        e.printStackTrace();
		        Toast.makeText(getBaseContext(),"ClientProtocolException",Toast.LENGTH_SHORT).show();
		      
		    } 
		    catch (IOException e)
		    {
		        e.printStackTrace();
		        Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
		    }
		    showtTextView.setText(s.toString());
		    Log.e("TAG", s.toString());
		}

}
