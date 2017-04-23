package com.MyAndroidCollection.Net.SSL;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import android.content.Context;

import com.MyAndroidCollection.util.LogX;

public class AccountX509TrustManager implements X509TrustManager {
   
    private static final String TRUST_FILE = "hicloudroot.bks";
    private static final String TRUST_PASSWORD="";
    private static final String KEY_TYPE="bks";
    private static final String TRUST_MANAGER_TYPE="X509";
    private static final String TAG = "AccountX509TrustManager";
    /*
     * The default X509TrustManager returned by SunX509. We'll delegate
     * decisions to it, and fall back to the logic in this class if the
     * default X509TrustManager doesn't trust it.
     */
    protected ArrayList<X509TrustManager> m509TrustManager = new ArrayList<X509TrustManager>();
    
    AccountX509TrustManager(Context context) {
        InputStream is =null;
        try {
            LogX.v(TAG, "new AccountX509TrustManager start");
            // get the X509 trust manager.
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TRUST_MANAGER_TYPE);
            // get the BKS key store instance.
            
            KeyStore trustKeyStore = KeyStore.getInstance(KEY_TYPE);
            is = context.getAssets().open(TRUST_FILE);
            is.reset();
            trustKeyStore.load(is, TRUST_PASSWORD.toCharArray());
            is.close();
            trustManagerFactory.init(trustKeyStore);
            TrustManager tms [] = trustManagerFactory.getTrustManagers();
            for (int i = 0; i < tms.length; i++) {
                if (tms[i] instanceof X509TrustManager) {
                    m509TrustManager.add((X509TrustManager) tms[i]);
                }
            }
            
            if(m509TrustManager.isEmpty()){
                throw new RuntimeException("Couldn't find a X509TrustManager!");
            }
            LogX.v(TAG, "new AccountX509TrustManager end");
        } catch (IOException e) {
            LogX.e(TAG, "IOException / " + e.toString(), e);
        } catch (NoSuchAlgorithmException e) {
            LogX.e(TAG, "NoSuchAlgorithmException / " + e.toString(), e);
        } catch (CertificateException e) {
            LogX.e(TAG, "CertificateException / " + e.toString(), e);
        }
        // load the certificate and private key by open and read assets file.
        // init trust manager.
        catch (KeyStoreException e) {
            LogX.e(TAG, "KeyStoreException / " + e.toString(), e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    LogX.i(TAG, e.toString());
                }
            }
        }
        
        /*
         * Iterate over the returned trustmanagers, look
         * for an instance of X509TrustManager. 
         * to any that are X509TrustManagers
         */
        
    }

    /*
     * Delegate to the default trust manager.
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType)
    throws CertificateException {
        try {
            LogX.d(TAG, "checkClientTrusted start");
            X509TrustManager defaultX509 = m509TrustManager.get(0);
            defaultX509.checkClientTrusted(chain, authType);
        } catch (CertificateException excep) {
            // do any special handling here, or rethrow exception.
            LogX.e(TAG, "CertificateException:"+excep.getMessage() , excep);
        }
    }

    /*
     * Loop over the trustmanagers until we find one that accepts our server.
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException{
    	LogX.d(TAG, "checkServerTrusted  start authType=" + authType);
    	//瀵规湇鍔″櫒杩涜璁よ瘉锛屽繀椤诲湪璇佷功鏈夋晥锛屼笖鍦ㄦ湁鏁堟湡鍐呴兘鍙兘閫氳繃
		X509TrustManager defaultX509 = m509TrustManager.get(0);
		defaultX509.checkServerTrusted(chain, authType);
		LogX.d(TAG, "checkServerTrusted end ");
        return;
    }

    /*
     * 妫?煡璇佷功鏄惁鍙俊鐨凜A涓績绛惧彂鐨勶紱
     */
    public X509Certificate[] getAcceptedIssuers() {
        LogX.v(TAG, "getAcceptedIssuers start");
        
    //    return  m509TrustManager.get(0).getAcceptedIssuers();
        
        ArrayList<X509Certificate> list = new ArrayList<X509Certificate>();
        for(X509TrustManager tm : m509TrustManager){
            list.addAll(Arrays.asList(tm.getAcceptedIssuers()));
        }
        return list.toArray(new X509Certificate[list.size()]);
   }
}
