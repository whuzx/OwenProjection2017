package com.MyAndroidCollection.util;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.R.bool;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;


public class Proguard {
	public static final char PROGUARD_CHAR = '*'; 
	public static final int PROGUARD_RATE = 30; 
	public static final boolean DEBUG=true;
	
	public static final String DONT_PRAGUARD_CHARS = "{:=@}/#?%\"(),/\\<>| &";
			
	private static String repeat(char c, int times){
		StringBuffer sb = new StringBuffer(times);
		for(int i= 0; i < times; i++){
			sb.append(c);
		}
		return sb.toString();
	}
	public static String getProguard(Object o){
		return getProguard(String.valueOf(o));
	}
	public static String getProguard(String info){
		if (DEBUG) {
			return info;
		}
		if(TextUtils.isEmpty(info)){
			return "";
		}
		int unProguardPos = (int) Math.ceil(info.length() * PROGUARD_RATE / 100.0) ;
		
		return repeat(PROGUARD_CHAR, unProguardPos) +  info.substring(unProguardPos);
	}
	public static String getProguard(Bundle bundle){
		if(null == bundle){
			return "";
		}
	
		
		Set<String> keys = bundle.keySet();
		StringBuffer sb = new StringBuffer();
		for(String k:keys){
			String proguardVal = getProguard(bundle.get(k));
			sb.append(k).append("=").append(proguardVal).append(" ");
		}
		return sb.toString();
	}
	public static String getProguard(Intent intent){
		if(null == intent){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if(!TextUtils.isEmpty(intent.getAction())){
			sb.append("act:" + intent.getAction()).append(" ");
		}
		sb.append(" flag:" + intent.getFlags()).append(" ");
		if(null != intent.getExtras()){
			sb.append(getProguard(intent.getExtras()));
		}
		
		
		return sb.toString();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getProguard(Map map){
		if(null == map || map.isEmpty()){
			return "";
		}

		StringBuffer sb = new StringBuffer();
		Iterator<Entry> it=  map.entrySet().iterator();
		while(it.hasNext()){
			Entry en = it.next();
			sb.append(en.getKey()).append("=")
				.append(Proguard.getProguard(String.valueOf(en.getValue())))
				.append(" ");
		}
		return sb.toString();
	}

	public static String getProguard(String content, boolean isLongStr){
	    if(!isLongStr){
	        return getProguard(content);
	    }
		if(TextUtils.isEmpty(content)){
			return "";
		}
		char[] val = content.toCharArray();
		for(int i = 0; i< val.length;  i+=2){
			if(!DONT_PRAGUARD_CHARS.contains(String.valueOf(val[i]))){
				val[i] = PROGUARD_CHAR;
			}
		}
		return String.valueOf(val);
	}

	
	public static String getBaseUrl(String fullurl) {
		if (TextUtils.isEmpty(fullurl)) {
			return "";
		}
		try {

			int equalPos = fullurl.indexOf("=");
			if (equalPos > 0) {
				return fullurl.substring(0, equalPos)
						+ getProguard(fullurl, true).substring(equalPos);
			} else {
				return fullurl;
			}

		} catch (Exception e) {
		}
		return fullurl;

	}
}
