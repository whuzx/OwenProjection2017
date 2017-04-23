package com.MyAndroidCollection.Net;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.MyAndroidCollection.util.LogX;



public class HttpClientUtils {
	
	//private static Log log=LogFactory.getLog(HttpClientUtils.class);   
    private static final String KEY_STORE_TYPE_JKS = "jks";   
	private static final String KEY_STORE_TYPE_P12 = "PKCS12";   
	private static final String SCHEME_HTTPS = "https";   
	private static final int HTTPS_PORT = 8443;   
	private static final String HTTPS_URL = "https://127.0.0.1:8443/TestDemo/sslServlet";   
	private static final String KEY_STORE_CLIENT_PATH = "D:/key/client.p12";   
	private static final String KEY_STORE_TRUST_PATH = "D:/key/client.truststore";   
	private static final String KEY_STORE_PASSWORD = "aaaaaa";   
	private static final String KEY_STORE_TRUST_PASSWORD = "aaaaaa";   
	     
	  /**  
	    * 使用httpClient 发出http请求  
	    * 均为POST请求  
	    * @param url  
	     * @param xmlStr  
	    * @return  
	    */  
   public static String  httpClient(String url,String xmlStr) {   
	       String returnStr="";   
	       HttpClient client = new DefaultHttpClient();   
	        HttpPost post=new HttpPost(url);   
	       post.setHeader("content-type", "text/xml; charset=UTF-8");   
      StringEntity strEntity;   
        try {   
           strEntity = new StringEntity(xmlStr, "UTF-8");   
           post.setEntity(strEntity);   
           HttpResponse response = client.execute(post);   
	           HttpEntity entity = response.getEntity();   
	            if(null!=entity){   
               BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));   
	                StringBuffer buff=new StringBuffer();   
	               while((returnStr=reader.readLine())!=null){   
                  buff.append(returnStr);   
              }   
               returnStr=buff.toString();   
//              returnStr=EntityUtils.toString(entity);  
            
               LogX.i("ssl","returnStr:"+returnStr);   
              
               post.abort();   
                   
               if(response != null) {   
                    Header headers[] = response.getAllHeaders();   
                   int i = 0;   
                    while (i < headers.length) {   
                    	 LogX.i("ssl",headers[i].getName() + ":  "+ headers[i].getValue());   
                        i++;   
                   }   
                }   
           }   
       } catch (UnsupportedEncodingException e) {   
    	   LogX.i("ssl","程序出错" +e.getMessage());   
        } catch (ClientProtocolException e) {   
        	 LogX.i("ssl","程序出错" +e.getMessage());   
       } catch (IOException e) {   
    	   LogX.i("ssl","程序出错" +e.getMessage());   
       }finally{   
           client.getConnectionManager().shutdown();   
        }   
          
       return returnStr;   
   }   
      
   /**  
	    * 发�?https请求  
	    * 为单向请�? 
     * @param url  
    * @param xmlStr  
    * @return  
     */  
   public static String httpsClient(String url,String xmlStr) {   
       long responseLength = 0;                         //响应长度     
       String responseContent = "";                     //响应内容     
       HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例     
       X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager     
          public java.security.cert.X509Certificate[] getAcceptedIssuers() {    
                return null;   //return new java.security.cert.X09Certificate[0];     
           }
		@Override
		public void checkClientTrusted(
				java.security.cert.X509Certificate[] chain, String authType)
				throws java.security.cert.CertificateException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void checkServerTrusted(
				java.security.cert.X509Certificate[] chain, String authType)
				throws java.security.cert.CertificateException {
			// TODO Auto-generated method stub
			
		}   
       };     
        try {     
           //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承�?，但它们使用的是相同的SSLContext     
            SSLContext ctx = SSLContext.getInstance("TLS");     
                
           //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket�?���?    
            ctx.init(null, new TrustManager[]{xtm}, null);     
                
          //创建SSLSocketFactory     
        
          // SSLSocketFactory socketFactory = new SSLSocketFactory(truststore);
        		   //new SSLSocketFactory(ctx);     
                
           //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient�?    
            //httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));     
	                
           HttpPost httpPost = new HttpPost(url);                        //创建HttpPost     
//            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //构建POST请求的表单参�?    
//            for(Map.Entry<String,String> entry : params.entrySet()){     
	//                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));     
	//            }     
           httpPost.setEntity(new StringEntity(xmlStr, "UTF-8"));     
                
            HttpResponse response = httpClient.execute(httpPost); //执行POST请求     
           HttpEntity entity = response.getEntity();             //获取响应实体     
                 
            if (null != entity) {     
                responseLength = entity.getContentLength();     
               responseContent = EntityUtils.toString(entity, "UTF-8");     
              //  EntityUtils.consume(entity); //Consume response content     
           }     
           System.out.println("请求地址: " + httpPost.getURI());     
           System.out.println("响应状: " + response.getStatusLine());     
          System.out.println("响应长度: " + responseLength);     
            System.out.println("响应内容: " + responseContent);     
       } catch (KeyManagementException e) {     
           e.printStackTrace();     
       } catch (NoSuchAlgorithmException e) {     
	           e.printStackTrace();     
        } catch (UnsupportedEncodingException e) {     
            e.printStackTrace();     
       } catch (ClientProtocolException e) {     
            e.printStackTrace();     
        } catch (ParseException e) {     
           e.printStackTrace();     
        } catch (IOException e) {     
           e.printStackTrace();     
       } finally {     
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源     
       }   
        return responseContent;   
   }   
       
    /**  
	    * 发�?https请求  
   * 为双向请�? 
   * @param url  
    * @param xmlStr  
	    * @return  
    */  
   private static void ssl() throws Exception {   
	        HttpClient httpClient = new DefaultHttpClient();   
        try {   
           KeyStore keyStore  = KeyStore.getInstance(KEY_STORE_TYPE_P12);   
           KeyStore trustStore  = KeyStore.getInstance(KEY_STORE_TYPE_JKS);   
           InputStream ksIn = new FileInputStream(KEY_STORE_CLIENT_PATH);   
            InputStream tsIn = new FileInputStream(new File(KEY_STORE_TRUST_PATH));   
            try {   
	               keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());   
	               trustStore.load(tsIn, KEY_STORE_TRUST_PASSWORD.toCharArray());   
	          } finally {   
	              try { ksIn.close(); } catch (Exception ignore) {}   
               try { tsIn.close(); } catch (Exception ignore) {}   
	           }   
	          SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, KEY_STORE_PASSWORD, trustStore);   
	           Scheme sch = null ;
	           //= new Scheme(SCHEME_HTTPS, HTTPS_PORT, socketFactory);   
           httpClient.getConnectionManager().getSchemeRegistry().register(sch);   
	           HttpGet httpget = new HttpGet(HTTPS_URL);   
          System.out.println("executing request" + httpget.getRequestLine());   
           HttpResponse response = httpClient.execute(httpget);   
           HttpEntity entity = response.getEntity();   
	            System.out.println("----------------------------------------");   
           System.out.println(response.getStatusLine());   
	            if (entity != null) {   
	               System.out.println("Response content length: " + entity.getContentLength());   
               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));   
	               String text;   
               while ((text = bufferedReader.readLine()) != null) {   
	                   System.out.println(text);   
	               }   
              bufferedReader.close();   
            }   
	             
           // EntityUtils.consume(entity);   
       } finally {   
           httpClient.getConnectionManager().shutdown();   
       }   
   }   

}
