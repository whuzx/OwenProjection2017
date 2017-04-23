package com.MyAndroidCollection.util;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

public class MyLocation {

	private static final String TAG="MyLocation";
	 public static String getCityName(Context context) { 
	        LocationManager locationManager; 
	        String contextString = Context.LOCATION_SERVICE; 
	        locationManager = (LocationManager) context.getSystemService(contextString); 
	        Criteria criteria = new Criteria(); 
	        criteria.setAccuracy(Criteria.ACCURACY_FINE); 
	        criteria.setAltitudeRequired(false); 
	        criteria.setBearingRequired(false); 
	        criteria.setCostAllowed(false); 
	        criteria.setPowerRequirement(Criteria.POWER_LOW); 
	        String cityName = null; 
	        // 取得效果最好的criteria 
	        String provider = locationManager.getBestProvider(criteria, true); 
	   
	        if (provider == null) { 
	            return null; 
	        } 
	        // 得到坐标相关的信息 
	       Location location = locationManager.getLastKnownLocation(provider); 
	        if (location == null) { 
	            return null; 
	        } 
	 
	        if (location != null) { 
	            double latitude = location.getLatitude(); 
	            double longitude = location.getLongitude(); 
	            LogX.i(TAG, "latitude = " +latitude  +"longitude"  +longitude);
	            // 更具地理环境来确定编码 
	            Geocoder gc = new Geocoder(context,Locale.ENGLISH); 
	            try { 
	                // 取得地址相关的一些信息\经度、纬度 
	                List<Address> addresses = gc.getFromLocation(latitude, longitude, 1); 
	                StringBuilder sb = new StringBuilder(); 
	                if (addresses.size() > 0) { 
	                    Address address = addresses.get(0);
	                    sb.append(address.getAdminArea()).append(""); 
	                    sb.append(address.getFeatureName()).append(""); 
	                    sb.append(address.getCountryCode()).append(""); 
	                    sb.append(address.getLocality()).append("\n"); 
	                    
	                    cityName = sb.toString(); 
	                } 
	                LogX.i(TAG, "cityName =" + cityName);
	            } catch (IOException e) { 
	            } 
	        } 
	        return cityName; 
	    } 


}
