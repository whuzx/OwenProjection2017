package com.MyAndroidCollection.Net.SSL;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.os.Build;

import com.MyAndroidCollection.Net.HttpConfig;
import com.MyAndroidCollection.util.LogX;

public class HttpClientConnetManager {

    private static ClientConnectionManager sClientConnectionManager;
    private static javax.net.ssl.SSLSocketFactory sSSLSocketFactoryInsecure;
    private static javax.net.ssl.SSLSocketFactory sSSLSocketFactorySecure;
    private static final String TAG = "HttpClientConnetManager";
    
    private static final ConnPerRoute sConnPerRoute = new ConnPerRoute() {
        public int getMaxForRoute(HttpRoute route) {
            return HttpConfig.MAX_ROUTE;
        }
    };


    /***
     * 
     * @param context
     * @return
     * 娉ㄦ剰绔彛璁剧疆锛屼笉鑳芥悶閿?
     */
    public static ClientConnectionManager getConnectionManager(Context context) {
        if (null == sClientConnectionManager) {
            SchemeRegistry registry = new SchemeRegistry();
            MySSLSocketFactory mysslSocketFactory = null;
            AccountSSLSocketFactory sslSocketFactory = null;
           
            try {
            	mysslSocketFactory = new MySSLSocketFactory(null, context);
            } catch (KeyManagementException e) {
                LogX.e(TAG, "getConnectionManager Exception " + "KeyManagementException", e);
            } catch (NoSuchAlgorithmException e) {
                LogX.e(TAG, "getConnectionManager Exception " + "NoSuchAlgorithmException", e);
            } catch (KeyStoreException e) {
                LogX.e(TAG, "getConnectionManager Exception " + "KeyStoreException", e);
            } catch (UnrecoverableKeyException e) {
                LogX.e(TAG, "getConnectionManager Exception " + "UnrecoverableKeyException", e);
            }
            if(null == mysslSocketFactory && Build.VERSION.SDK_INT > 7){
            	LogX.d(TAG, "mysslSocketFactory is null, use SSLSocketFactory");
            	sslSocketFactory = new AccountSSLSocketFactory(getSSLSocketFactory(true));
            	sslSocketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            	// Register the httpts scheme with our factory
            	
            	registry.register(new Scheme("https", sslSocketFactory, HttpConfig.HTTPS_PORT));
            } else if(null != mysslSocketFactory){
            	LogX.d(TAG, "mysslSocketFactory is not null");
            	mysslSocketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            	// Register the httpts scheme with our factory
            	 registry.register(new Scheme("https", mysslSocketFactory, HttpConfig.HTTPS_PORT));
            }
           
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(),
            		HttpConfig.HTTP_PORT)); 
            
            HttpParams params = new BasicHttpParams();
            params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS,
                    HttpConfig.MAX_TOTAL_CONNECTIONS);
            params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, sConnPerRoute);

            // And create a ccm with our registry
            sClientConnectionManager = new ThreadSafeClientConnManager(params, registry);
        }
        return sClientConnectionManager;
    }

    private static final javax.net.ssl.SSLSocketFactory getSSLSocketFactory(boolean insecure) {
    	if (insecure) {
    		if (null == sSSLSocketFactoryInsecure) {
    			sSSLSocketFactoryInsecure = SSLCertificateSocketFactory.getInsecure(0, null);
    		}
    		return sSSLSocketFactoryInsecure;
    	} else {
    		if (null == sSSLSocketFactorySecure) {
    			sSSLSocketFactorySecure = SSLCertificateSocketFactory.getInsecure(0, null);
    		}
    		return sSSLSocketFactorySecure;
    	}
    }
    
    
    /***
     * 瀵筍SL鐨勭畻娉曞垪琛ㄨ繘琛岄厤缃?
     * @param sslsock
     */
	public static void setEnableSafeCipherSuites(SSLSocket sslsock) {

		LogX.i(TAG, "enter setEnableSafeCipherSuites");

		// 褰撳墠璁惧鏀寔鐨勬墍鏈夌畻娉曞垪琛?
		String ENABLED_CIPHERS[] = sslsock.getEnabledCipherSuites();

		LogX.i(TAG, " current EnabledCipherSuites size"
				+ ENABLED_CIPHERS.length);

		List<String> ENABLED_CIPHERS_List = new ArrayList<String>();
		for (String string : ENABLED_CIPHERS) {
			// 灏嗗寘鍚玆C4 鐨勭畻娉曞共鎺?
			if (!string.contains("RC4")) {
				ENABLED_CIPHERS_List.add(string);
			}
		}

		LogX.i(TAG, "get safe EnabledCipherSuites list " + "size ="
				+ ENABLED_CIPHERS_List.size());

		// list 杞寲涓烘暟缁?
		String SAFE_ENABLED_CIPHERS[] = ENABLED_CIPHERS_List
				.toArray(new String[ENABLED_CIPHERS_List.size()]);

		LogX.i(TAG, "get safe EnabledCipherSuites Array " + "length ="
				+ SAFE_ENABLED_CIPHERS.length);

		sslsock.setEnabledCipherSuites(SAFE_ENABLED_CIPHERS);

	}
    
}

/***
 * 1銆佹鏌ヨ瘉涔︽槸鍚﹀彲淇＄殑CA涓績绛惧彂鐨勶紱
 * 2銆佹鏌ヨ瘉涔︽槸鍚﹀湪鏈夋晥鏈熷唴
 * 3銆佹鏌ヨ瘉涔︾殑鎵?湁鑰呭拰鏈嶅姟鍣ㄥ煙鍚嶆槸鍚︿竴鑷?
 * 4銆佹鏌ヨ瘉涔︽槸鍚﹀湪榛戣〃鍗曪紙CRL锛変腑
 * @author z00201051
 *
 */
class MySSLSocketFactory extends org.apache.http.conn.ssl.SSLSocketFactory{
    private static final String TAG = "MySSLSocketFactory";
    
    SSLContext sslContext = SSLContext.getInstance("TLS");
    Context mContext;
    
    public MySSLSocketFactory(KeyStore truststore)
            throws NoSuchAlgorithmException, KeyManagementException,
            KeyStoreException, UnrecoverableKeyException {
        super(truststore);
        
    }
    
    public MySSLSocketFactory(KeyStore truststore,Context context) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException{
        super(truststore);
        this.mContext = context;
        TrustManager tm =null;
        try {
            tm = new AccountX509TrustManager(mContext);
            
        } catch (Exception e) {
            LogX.e(TAG, "Initialize AccountX509TrustManager failed.", e);
        }

        this.sslContext.init(null, new TrustManager[] { tm }, new SecureRandom());
    }
    
    @Override
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        SSLSocket mySocket=  (SSLSocket) sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
    	
        //璁剧疆瀹夊叏SSL绠楁硶
        HttpClientConnetManager.setEnableSafeCipherSuites(mySocket);
        return mySocket;
    }

    @Override
    public Socket createSocket() throws IOException {
    	
    	SSLSocket mySocket=  (SSLSocket) sslContext.getSocketFactory().createSocket();
    	 //璁剧疆瀹夊叏SSL绠楁硶
    	HttpClientConnetManager.setEnableSafeCipherSuites(mySocket);
        return mySocket;
    }
    
    
}