package com.MyAndroidCollection.Net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HttpsDemo extends Activity {

	/** Called when the activity is first created. */

	private TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		text = new TextView(this);

		// setContentView(R.layout.main);

		// text = (TextView)findViewById(R.id.text);
		this.setContentView(text);
		GetHttps();
	}

	private void GetHttps() {

		String https = "https://setting.hicloud.com:8443/";

		try {

			SSLContext sc = SSLContext.getInstance("TLS");

			sc.init(null, new TrustManager[] { new MyTrustManager() },
					new SecureRandom());

			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());

			HttpsURLConnection
					.setDefaultHostnameVerifier(new MyHostnameVerifier());

			HttpsURLConnection conn = (HttpsURLConnection) new URL(https)
					.openConnection();

			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.connect();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			StringBuffer sb = new StringBuffer();

			String line;

			while ((line = br.readLine()) != null)

				sb.append(line);

			text.setText(sb.toString());

		} catch (Exception e) {

			Log.e(this.getClass().getName(), e.getMessage());

		}

	}

	private class MyHostnameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {

			// TODO Auto-generated method stub

			return true;

		}

	}

	private class MyTrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)

		throws CertificateException {

			// TODO Auto-generated method stub

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)

		throws CertificateException {

			// TODO Auto-generated method stub

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {

			// TODO Auto-generated method stub

			return null;

		}

	}

}

