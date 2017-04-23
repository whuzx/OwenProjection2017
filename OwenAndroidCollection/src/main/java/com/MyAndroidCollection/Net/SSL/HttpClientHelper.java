package com.MyAndroidCollection.Net.SSL;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.content.Context;

import com.MyAndroidCollection.Net.HttpConfig;
import com.MyAndroidCollection.util.LogX;

public class HttpClientHelper {

	private static HttpClient httpClient;
	private static final String TAG = "HttpClientHelper";

	private HttpClientHelper() {

	}

	private static final ConnPerRoute sConnPerRoute = new ConnPerRoute() {
		public int getMaxForRoute(HttpRoute route) {
			return HttpConfig.MAX_ROUTE;
		}
	};

	/**
	 * keystore 璇佷功璁よ瘉鏈嶅姟鍣?
	 * @param context
	 * @return
	 */
	public static synchronized HttpClient getSafeHttpClient(Context context) {
		LogX.i(TAG, "getSafeHttpClient");
		if (httpClient != null) {
			LogX.i(TAG, "httpClient is Ok ,return directly");
			return httpClient;
		} else {
			httpClient = new DefaultHttpClient(
					HttpClientConnetManager.getConnectionManager(context), null);
			return httpClient;
		}
	}

	/***
	 *  灏唖tore鏀惧叆鍒扮粓绔俊浠诲垪琛?
	 * @param context
	 * @return
	 */
	public static synchronized HttpClient getHttpClientByCer(Context context) {

		LogX.i(TAG, "getHttpClient1");

		InputStream ins = null;
		if (httpClient != null) {
			return httpClient;
		}

		try {
			ins = context.getAssets().open("hicloudroot.cer");
			LogX.i(TAG, "getHttpClientByCer");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} // 涓嬭浇鐨勮瘉涔︽斁鍒伴」鐩腑鐨刟ssets鐩綍涓?

		CertificateFactory cerFactory = null;
		Certificate cer = null;
		KeyStore keyStore = null;
		SSLSocketFactory socketFactory = null;

		try {
			cerFactory = CertificateFactory

			.getInstance("X.509");
			cer = cerFactory.generateCertificate(ins);
			LogX.i(TAG, "getHttpClient1 generateCertificate");

			keyStore = KeyStore.getInstance("PKCS12", "BC");
			
			keyStore.load(null, null);
			keyStore.setCertificateEntry("trust", cer);
			LogX.i(TAG, "getHttpClient1 setCertificateEntry");

			socketFactory = new SSLSocketFactory(keyStore);

			SchemeRegistry schReg = new SchemeRegistry();

			schReg.register(new Scheme("http", PlainSocketFactory

			.getSocketFactory(), HttpConfig.HTTP_PORT));

			schReg.register(new Scheme("https", socketFactory,
					HttpConfig.HTTPS_PORT));

			HttpParams params = new BasicHttpParams();
			params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS,
					HttpConfig.MAX_TOTAL_CONNECTIONS);
			params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE,
					sConnPerRoute);

			ClientConnectionManager conManager = new ThreadSafeClientConnManager(

			params, schReg);

			httpClient = new DefaultHttpClient(conManager, null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NoSuchProviderException e2) {
			e2.printStackTrace();
		}

		return httpClient;
	}

	/***
	 * 蹇界暐璇佷功妫?祴
	 * @return
	 */
	public static synchronized HttpClient getUnSafeHttpClient(int httpPort,int httpsPort) {
	    // 鐢变簬娴嬭瘯鏈嶅姟鍣ㄧ殑绔彛涓嶄竴鑷达紝鎵?互鍦ㄨ幏鍙杊ttpClient鏃舵瘡娆￠兘闇?閲嶆柊鑾峰彇
		// 鍒濆鍖栧伐浣?
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore

			.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); // 鍏佽鎵?湁涓绘満鐨勯獙璇?

			// 璁剧疆http https鏀寔
			SchemeRegistry schReg = new SchemeRegistry();

			schReg.register(new Scheme("http", PlainSocketFactory

			.getSocketFactory(), httpPort));

			schReg.register(new Scheme("https", sf, httpsPort));

			HttpParams params = new BasicHttpParams();
			params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS,
					HttpConfig.MAX_TOTAL_CONNECTIONS);
			params.setParameter(
					ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE,
					sConnPerRoute);

			ClientConnectionManager conManager = new ThreadSafeClientConnManager(

			params, schReg);

			httpClient = new DefaultHttpClient(conManager, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new DefaultHttpClient();
		}
        LogX.i(TAG, "httpPort: " + httpPort + " httpsPort: " + httpsPort);
		return httpClient;

	}

}

class SSLSocketFactoryEx extends org.apache.http.conn.ssl.SSLSocketFactory {
	SSLContext sslContext = SSLContext.getInstance("TLS");

	public SSLSocketFactoryEx(KeyStore truststore)

	throws NoSuchAlgorithmException, KeyManagementException,

	KeyStoreException, UnrecoverableKeyException {

		super(truststore);

		TrustManager tm = new X509TrustManager() {

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {

				return null;

			}

			@Override
			public void checkClientTrusted(

			java.security.cert.X509Certificate[] chain, String authType)

			throws java.security.cert.CertificateException {

			}

			@Override
			public void checkServerTrusted(

			java.security.cert.X509Certificate[] chain, String authType)

			throws java.security.cert.CertificateException {

			}

		};

		sslContext.init(null, new TrustManager[] { tm }, null);

	}

	@Override
	public Socket createSocket(Socket socket, String host, int port,

	boolean autoClose) throws IOException, UnknownHostException {

		return sslContext.getSocketFactory().createSocket(socket, host, port,

		autoClose);

	}

	@Override
	public Socket createSocket() throws IOException {

		return sslContext.getSocketFactory().createSocket();

	}

}
