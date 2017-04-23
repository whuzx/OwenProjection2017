package com.MyAndroidCollection.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.MyAndroidCollection.util.muticard.MutiCard;
import com.MyAndroidCollection.util.muticard.MutiCardFactory;

public class BaseUtil {

    private static final String TAG = "BaseUtil";
    
    public static final String TYPE_USER_NAME = "0";
    public static final String TYPE_EMAIL = "1";
    public static final String TYPE_PHONE = "2";
    
    private static List<String> appIDWhitelist = new ArrayList<String>();
    private static List<String> tokenTypeWhitelist = new ArrayList<String>();

    public static final boolean IS_TABLET = Build.VERSION.SDK_INT >= 11;

    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof String) {
            if (obj.toString().trim().length() == 0) {
                return true;
            }
        } else if (obj instanceof List<?>) {
            if (((List<?>) obj).isEmpty()) {
                return true;
            }
        } else if (obj instanceof Map<?, ?>) {
            if (((Map<?, ?>) obj).isEmpty()) {
                return true;
            }
        } else if (obj instanceof Set<?>) {
            if (((Set<?>) obj).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static String getPhoneNumber(Context context) {
        TelephonyManager phoneMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = phoneMgr.getLine1Number();
        if (null == phoneNumber) {
            return "";
        } else {
            return phoneNumber;
        }
    }

    public static boolean isSimCardOk(Context context, int subId) {
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        int simState = -1;
        if(MutiCardFactory.isMultiSimEnabled()) {
            MutiCard mutiCard=MutiCardFactory.createIfGemini();
            if(subId == -1) {
                subId = mutiCard.getDefaultSubscription();
            }
            simState = mutiCard.getSimState(subId);
        } else {
            if(null != manager) {
                simState = manager.getSimState();
            }
        }
        if(simState == TelephonyManager.SIM_STATE_READY){
            return true;
        }
        return false;
    }

    public static boolean networkIsAvaiable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == connectivity) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (null == info) {
                return false;
            }
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

//    public static int getConnectType(Context context) {
//        ConnectivityManager con = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkinfo = con.getActiveNetworkInfo();
//        if (null == networkinfo || !networkinfo.isAvailable()) {
//            return HttpStatusCode.NO_CONNECT;
//        }
//        if (con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
//                .isConnectedOrConnecting()) {
//            return HttpStatusCode.CONNECT_BY_WIFI;
//        }
//        return HttpStatusCode.CONNECT_BY_GPRS;
//
//    }

    public static String getPackageNameEx(Context context) {
        return context.getPackageName();
    }

    public static String getLanguage(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        String language = configuration.locale.getLanguage();
        return language.toLowerCase(Locale.getDefault());
    }
    
    
    public static String getCountry(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        String country = configuration.locale.getCountry();
        return country.toLowerCase(Locale.getDefault());
    }

 
 
    
   
	public static boolean isAppIDInwhiteList(Context context, String appID,
			String serviceType) {
		if (appIDWhitelist.isEmpty()) {	
			String[] whiteappIDStr= new String[]{		
					"com.android.paydemo",
					"com.huawei.hwpay",
					"com.huawei.cloudplus.pay",
					"6204", //鏀粯
					"com.huawei.cloudservice" //鍐呴儴SDK娴嬭瘯
			};
			
			String[] whiteTokenTypeStr= new String[]{
				"6204", 
				"com.huawei.gallery",
				"com.huawei.android.ds"
			};
			appIDWhitelist.addAll(Arrays.asList(whiteappIDStr));
			tokenTypeWhitelist.addAll(Arrays.asList(whiteTokenTypeStr));
		}
		// 鍖呭悕鍦╯solist 鎴?TokenType涓?6204
		if (appIDWhitelist.contains(appID)
				|| tokenTypeWhitelist.contains(serviceType)) {
			return true;
		} else {
			return false;
		}
	}
    
  

    public static boolean checkIsInstallHuaweiAccount(Context context) {
        PackageManager packMgr = context.getPackageManager();
        try {
            return (null != packMgr.getApplicationInfo("com.huawei.hwid",
                    PackageManager.GET_META_DATA));
        } catch (NameNotFoundException e) {
            return false;
        }
    }
    
    public static String getVersionName(Context context,String packageName){
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					packageName, 0);
			String versionName =  packageInfo.versionName;
			LogX.i("BaseUtil", "versionName " +versionName) ;
			return versionName;
			
					
		} catch (NameNotFoundException e) {
			LogX.e("BaseUtil", "getVersionTag error", e);
		}
		return "";
    }
    
   

}
