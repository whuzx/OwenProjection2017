
package com.MyAndroidCollection.Net.SSL;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;

import com.MyAndroidCollection.util.LogX;


public class AccountSSLSocketFactory implements LayeredSocketFactory {

    private static final String TAG = "SSLSocketFactory";
    public static final String TLS = "TLS";
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";

    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();

    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();

    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    /**
     * The factory using the default JVM settings for secure connections.
     */
    private static final AccountSSLSocketFactory DEFAULT_FACTORY = new AccountSSLSocketFactory();

    /**
     * Gets an singleton instance of the SSLProtocolSocketFactory.
     * 
     * @return a SSLProtocolSocketFactory
     */
    public static AccountSSLSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    private final SSLContext sslcontext;
    private final javax.net.ssl.SSLSocketFactory socketfactory;
    private final HostNameResolver nameResolver;
    //默认为 BROWSER_COMPATIBLE_HOSTNAME_VERIFIER，不能为ALLOW_ALL_HOSTNAME_VERIFIER  带进一步验证
    private X509HostnameVerifier hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;

    public AccountSSLSocketFactory(String algorithm, final KeyStore keystore,
            final String keystorePassword, final KeyStore truststore, final SecureRandom random,
            final HostNameResolver nameResolver, Context context) throws NoSuchAlgorithmException,
            KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super();
        if (null == algorithm) {
            algorithm = TLS;
        }
        KeyManager[] keymanagers = null;
        if (null != keystore) {
            keymanagers = createKeyManagers(keystore, keystorePassword);
        }
        TrustManager[] trustmanagers = null;
        if (null != truststore) {
            try {
                trustmanagers = createTrustManagers(truststore, context);
            } catch (Exception e) {
                LogX.e(TAG, "Exception / " + e.toString(), e);
            }
        }
        this.sslcontext = SSLContext.getInstance(algorithm);
        this.sslcontext.init(keymanagers, trustmanagers, random);
        this.socketfactory = this.sslcontext.getSocketFactory();
        this.nameResolver = nameResolver;
    }

    public AccountSSLSocketFactory(final KeyStore keystore, final String keystorePassword,
            final KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException,
            KeyStoreException, UnrecoverableKeyException {
        this(TLS, keystore, keystorePassword, truststore, null, null, null);
    }

    public AccountSSLSocketFactory(final KeyStore keystore, final String keystorePassword)
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException,
            UnrecoverableKeyException {
        this(TLS, keystore, keystorePassword, null, null, null, null);
    }

    public AccountSSLSocketFactory(final KeyStore truststore, Context context)
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException,
            UnrecoverableKeyException {

        this(TLS, null, null, truststore, null, null, context);
    }

    /**
     * Constructs an HttpClient SSLSocketFactory backed by the given JSSE
     * SSLSocketFactory.
     * 
     * @hide
     */
    public AccountSSLSocketFactory(javax.net.ssl.SSLSocketFactory socketfactory) {
        super();
        this.sslcontext = null;
        this.socketfactory = socketfactory;
        this.nameResolver = null;
    }

    /**
     * Creates the default SSL socket factory. This constructor is used
     * exclusively to instantiate the factory for {@link #getSocketFactory
     * getSocketFactory}.
     */
    private AccountSSLSocketFactory() {
        super();
        this.sslcontext = null;
        this.socketfactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        this.nameResolver = null;
    }

    private static KeyManager[] createKeyManagers(final KeyStore keystore, final String password)
            throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        if (null == keystore) {
            throw new IllegalArgumentException("Keystore may not be null");
        }
        KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory
                .getDefaultAlgorithm());
        kmfactory.init(keystore, null != password ? password.toCharArray() : null);
        return kmfactory.getKeyManagers();
    }

    private static TrustManager[] createTrustManagers(final KeyStore keystore, Context context)
            throws Exception {
        if (null == keystore) {
            throw new IllegalArgumentException("Keystore may not be null");
        }
        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance("X509");
        tmfactory.init(keystore);
        return tmfactory.getTrustManagers();
    }

    // non-javadoc, see interface org.apache.http.conn.SocketFactory
    public Socket createSocket() throws IOException {
 
        return (SSLSocket) this.socketfactory.createSocket();
    }

    // non-javadoc, see interface org.apache.http.conn.SocketFactory
    public Socket connectSocket(final Socket sock, final String host, final int port,
            final InetAddress localAddress, int localPort, final HttpParams params)
            throws IOException {

        if (null == host) {
            throw new IllegalArgumentException("Target host may not be null.");
        }
        if (null == params) {
            throw new IllegalArgumentException("Parameters may not be null.");
        }

        SSLSocket sslsock = (SSLSocket) ((null != sock) ? sock : createSocket());
        
        //设置安全的算法
        HttpClientConnetManager.setEnableSafeCipherSuites(sslsock);

        try{
            if ((null != localAddress) || (localPort > 0)) {
    
                // we need to bind explicitly
                if (localPort < 0)
                    localPort = 0; // indicates "any"
    
                InetSocketAddress isa = new InetSocketAddress(localAddress, localPort);
                sslsock.bind(isa);
            }
    
            int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
            int soTimeout = HttpConnectionParams.getSoTimeout(params);
    
            InetSocketAddress remoteAddress;
            if (null != this.nameResolver) {
                remoteAddress = new InetSocketAddress(this.nameResolver.resolve(host), port);
            } else {
                remoteAddress = new InetSocketAddress(host, port);
            }
    
            sslsock.connect(remoteAddress, connTimeout);
    
            sslsock.setSoTimeout(soTimeout);
        } catch (IOException ioe) {
            try {
                sslsock.close();
            } catch (IOException x) { 
                LogX.e(TAG, x.toString(), x);
            }
            throw ioe;
        }
        
        return sslsock;
    }



	/**
     * Checks whether a socket connection is secure. This factory creates
     * TLS/SSL socket connections which, by default, are considered secure. <br/>
     * Derived classes may override this method to perform runtime checks, for
     * example based on the cypher suite.
     * 
     * @param sock
     *            the connected socket
     * 
     * @return <code>true</code>
     * 
     * @throws IllegalArgumentException
     *             if the argument is invalid
     */
    public boolean isSecure(Socket sock) throws IllegalArgumentException {

        if (null == sock) {
            throw new IllegalArgumentException("Socket may not be null.");
        }
        // This instanceof check is in line with createSocket() above.
        if (!(sock instanceof SSLSocket)) {
            throw new IllegalArgumentException("Socket not created by this factory.");
        }
        // This check is performed last since it calls the argument object.
        if (sock.isClosed()) {
            throw new IllegalArgumentException("Socket is closed.");
        }

        return true;

    } // isSecure

    // non-javadoc, see interface LayeredSocketFactory
    public Socket createSocket(final Socket socket, final String host, final int port,
            final boolean autoClose) throws IOException, UnknownHostException {
        SSLSocket sslSocket = (SSLSocket) this.socketfactory.createSocket(socket, host, port,
                autoClose);
        

        return sslSocket;
    }

    public void setHostnameVerifier(X509HostnameVerifier hostnameVerifier) {
        if (null == hostnameVerifier) {
            throw new IllegalArgumentException("Hostname verifier may not be null");
        }
        this.hostnameVerifier = hostnameVerifier;
    }

    public X509HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

}
