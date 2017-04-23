package com.MyAndroidCollection.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HttpPostConnection extends Activity {
	private boolean isAuthenticated;
	private ArrayList<BasicNameValuePair> pairs;
	private DefaultHttpClient httpclient;
	private HttpPost httppost;
	private InputStream content;
	private String returnConnection;
	private String postdataString = null;
	private String refString = "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp";
	private String contentType = "application/x-www-form-urlencoded";
	private String accept = "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/x-silverlight, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/x-silverlight-2-b1, */*";
	private String userAgent = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT .1; .NET CLR 2.0.0727; .NET CLR 3.0.0406.648; .NET CLR 3..21022)";
	private String queryString = "http://dynamic.12306.cn/TrainQuery/iframeLeftTicketByStation.jsp";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * Properties prop = System.getProperties();
		 * prop.setProperty("http.proxyHost", "172.29.8.29");
		 * prop.setProperty("http.proxyPort", "80");
		 */

		TextView tv = new TextView(this);
		Map<String, String> vars = new HashMap<String, String>();
		/* post请求的内容mydata="Hello, Android" */
		// vars.put("mydata", "Hello, Android");

		try {
			postdataString = "lx=00&nmonth3="
					+ "11"
					+ "&nmonth3_new_value=true&nday3="
					+ "0"
					+ "&nday3_new_value=false&startStation_ticketLeft="
					+ URLEncoder.encode("杭州", HTTP.UTF_8)
					+ "&startStation_ticketLeft_new_value=true&arriveStation_ticketLeft="
					+ URLEncoder.encode("武汉", HTTP.UTF_8)
					+ "&arriveStation_ticketLeft_new_value=true&trainCode="
					+ "&trainCode_new_value=true&rFlag=1&name_ckball=value_ckball&tFlagDC=DC&tFlagZ=Z&tFlagT=T&tFlagK=K&tFlagPK=PK&tFlagPKE=PKE&tFlagLK=LK";
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// vars.put("postdata", postdataString);
		// parameterHttp("http://www.google.com/ig/api?&weather=hangzhou",
		// vars);

		doPost();
		tv.setText(this.returnConnection);
		this.setContentView(tv);
	}

	public void parameterHttp(String url, Map<String, String> variables) {

		String paString = null;
		this.httpclient = new DefaultHttpClient();
		this.httppost = new HttpPost(url);
		this.pairs = new ArrayList();
		if (variables != null) {
			Set keys = variables.keySet();
			for (Iterator i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				paString = variables.get(key);
				Log.e("result", variables.get(key));
				System.out.println(variables.get(key));
				pairs.add(new BasicNameValuePair(key, variables.get(key)));
			}

			/*
			 * FileOutputStream fileOutputStream = null; try { fileOutputStream
			 * = this.openFileOutput("param.txt", MODE_WORLD_WRITEABLE); } catch
			 * (FileNotFoundException e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); } try {
			 * fileOutputStream.write(paString.getBytes()); } catch (IOException
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}
	}

	public String doPost() {
		try {

			this.httpclient = new DefaultHttpClient();
			this.httppost = new HttpPost(queryString);

			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
			HttpConnectionParams.setSoTimeout(httpParams, 30000);

			HttpHost proxy = new HttpHost("172.29.8.29", 80);
			httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

			DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);

			/** 将POST数据放入http请求 */

			httppost.addHeader("Content-Type",
					"application/x-www-form-urlencoded");
			httppost.addHeader("Accept", accept);
			httppost.addHeader("Referer", refString);
			httppost.addHeader("userAgent", userAgent);
			httppost.addHeader("Host", "dynamic.12306.cn");
			httppost.addHeader("Accept-Encoding", "gzip, deflate");
			// httppost.addHeader("connection","Keep-Alive");
			httppost.addHeader("cache-control", "no-cache");
			httppost.addHeader("Accept-Language", "zh-CN");

			StringEntity ent = new StringEntity(postdataString);
			httppost.setEntity(ent);

			/** 发出实际的HTTP POST请求 */
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			content = entity.getContent();
			this.returnConnection = convertStreamToString(content);
			Log.d("HttpPostConnection", ">>>>>>>>>>>>>>> " + returnConnection);
			int status_code = response.getStatusLine().getStatusCode();
			if (status_code >= 300) {
				this.isAuthenticated = false;
			} else {
				this.isAuthenticated = true;
			}
		} catch (UnsupportedEncodingException uee) {
			// 异常处理
		} catch (IOException ioe) {
			// 异常处理
		} catch (IllegalStateException ise) {
			// 异常处理
		}
		return returnConnection;
	}

	public String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}
}