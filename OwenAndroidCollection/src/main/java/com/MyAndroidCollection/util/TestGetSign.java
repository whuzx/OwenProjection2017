package com.MyAndroidCollection.util;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;

public class TestGetSign {
    private static final String TAG = "TestGetSign";
    private static final int TESTKEY_HASH_CODE = -73404696;
    private static final int PLATFORM_HASH_CODE = -213010633;
    public static byte[] getAllSign(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm
                .getInstalledPackages(PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> iter = apps.iterator();

        while (iter.hasNext()) {
            PackageInfo info = iter.next();
            String packageName = info.packageName;

//            if (packageName.equals("com.test.test")) {
                return info.signatures[0].toByteArray();
//            } 
        }
        return null;
    }
    public static String getMySign(Context context){
        Signature[] sigs = null;
        try {
            sigs = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 64).signatures;
            StringBuffer sb = new StringBuffer();
            if(null != sigs){
                for(Signature si: sigs){
                    sb.append(si.toCharsString());
                }
            }
            Log.d(TAG, "sigs len:" + sigs.length);
            return sb.toString();
        } catch (NameNotFoundException e) {
            Log.e(TAG, e.toString(), e);
        }
        return "";
    }
    public static boolean test(Context context){
        String sign = getMySign(context);
        Log.d(TAG, "mysign hashCode:" + sign.hashCode());
        return TESTKEY_HASH_CODE == sign.hashCode();
    }

}
