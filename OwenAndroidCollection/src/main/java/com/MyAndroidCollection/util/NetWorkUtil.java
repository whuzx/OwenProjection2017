package com.MyAndroidCollection.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetWorkUtil {

	
	 //获取当前IP
    public static String getLocalIpAddress() {   
        try {   
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {   
                NetworkInterface intf = en.nextElement();   
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {   
                    InetAddress inetAddress = enumIpAddr.nextElement();   
                    if (!inetAddress.isLoopbackAddress()) {   
                        return inetAddress.getHostAddress().toString();   
                    }   
                }   
            }   
        } catch (SocketException ex) {   
            LogX.e("activitysDemo", ex.toString());   
        }   
        return null;   
    }  
}
