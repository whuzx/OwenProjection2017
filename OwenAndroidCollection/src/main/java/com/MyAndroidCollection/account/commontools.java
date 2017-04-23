package com.MyAndroidCollection.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class commontools {

	/**
     * @param context
     * @return return true when the device install the HwId apk ,or false
     */
    public static boolean checkIsInstallHwID(Context context) {
        PackageManager packMgr = context.getPackageManager();
        try {
            return (null != packMgr.getApplicationInfo("com.huawei.hwid",
                    PackageManager.GET_META_DATA));
        } catch (NameNotFoundException e) {
            return false;
        }
    }
	
	
	 /**
     * check if the application have already login HwID
     * 
     * @param context
     * @return return true when already have accounts logined,or false
     */
    public static boolean hasLoginAccountInHwID(Context context) {
        
		//判断是否安装有HwID
		if(!checkIsInstallHwID(context)){
			return  false;
		 }
 
	
	//判断HwID是否有已登录的帐号
	   AccountManager  AccMgr=AccountManager.get(context);
	   Account [] accs = AccMgr.getAccountsByType("com.huawei.hwid");
        if(accs != null && accs.length > 0){
            return true;
        } else {
           return false;
        }
	}
}
