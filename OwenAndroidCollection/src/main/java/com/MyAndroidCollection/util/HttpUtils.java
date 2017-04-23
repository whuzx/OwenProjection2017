package com.MyAndroidCollection.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;




public class HttpUtils {

	
	/**
	    * Google DNS Server
	    */
	    private static final String GOOLE_DNS_SERVER = "8.8.8.8";

	/**
	    * Return the ip address of the host resolved by google server.
	    * @param host the host
	    * @return ip address
	    */
/*	    public static String resolveDNSByGoogle(String host) {
	        if (host == null || "".equals(host)) {
	            return null;
	        }

	        try {
	            SimpleResolver res = new SimpleResolver(GOOLE_DNS_SERVER);
	            Name name = Name.fromString(host, Name.root);
	            Record rec = Record.newRecord(name, Type.A, DClass.IN);
	            Message query = Message.newQuery(rec);
	            Message response = res.send(query);
	            Record[] records = response.getSectionArray(Section.ANSWER);
	            response.getHeader().getCount(Section.ANSWER);
	            String ip = null;

	            for (Record record : records) {
	                ip = ((ARecord) record).getAddress().getHostAddress();
	                if(ip != null && !"".equals(ip)) {
	                    return ip;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	    }*/
	    
	    
	    /**
	     * 封装下载图片方法
	     * @param url 下载地址
	     * @param filename 
	     * @return 下载成功为true
	     */
	    
	    public boolean downfile(String url,String filename,String filePath){        
	        InputStream is=null;
	        FileOutputStream out=null;
	        HttpGet httpGet = new HttpGet(url); 
	        try {
	        HttpParams httpParameters = new BasicHttpParams();  
	        HttpConnectionParams.setConnectionTimeout(httpParameters, 20000); 
	        
	        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters); 
	        HttpResponse response;
	            response = httpClient.execute(httpGet);
	        
	        
	        if (response.getStatusLine().getStatusCode() ==  HttpStatus.SC_OK){
	            HttpEntity entity = response.getEntity();          
	            is = entity.getContent(); 
	            long fileSize = entity.getContentLength();//根据响应获取文件大小
	            if (fileSize <= 0) throw new RuntimeException("无法获知文件大小 ");
	            if (is == null) throw new RuntimeException("stream is null");
	            File file = new File(filePath);        
	            if(!file.exists()){
	                file.mkdir();
	            }
	            file = new File(filePath);    
	            if(!file.exists()){
	                file.mkdir();
	            }
	            File fileOut = new File(filename);
	            if(!fileOut.exists()){
//	                new File(filePath).mkdir();
	                fileOut.createNewFile(); 
	            }
	            out = new FileOutputStream(fileOut);   
	            byte[] buf = new byte[1024];                 
	            do{
	                int numread = is.read(buf);
	                if (numread == -1)
	                {
	                    break;
	                }
	                out.write(buf, 0, numread);
	                
	            
	            } while (true);
	         }else{
	             return false;
	         }                   
	        is.close();   
	        out.close();
	        return true;
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return false;
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return false;
	        } 
	    }

}
