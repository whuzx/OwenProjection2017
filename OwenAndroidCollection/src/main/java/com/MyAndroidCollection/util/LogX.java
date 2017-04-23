
package com.MyAndroidCollection.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.MyAndroidCollection.Constants.Consts;

public class LogX {
	private static String Log_Level_Control_tag = "MyAndroidCollection";
	private static final int CALL_LOG_LEVEL = 2;
    private static String lastPkgName = "MyAndroidCollection";
    private static String tag_extend = "";
    
    public static void init(Context context)
    {
        String  pkgName = context.getPackageName();
        if(null != pkgName)
        {
        	String[] splitName = pkgName.split("\\.");
        	if(null != splitName && splitName.length > 0)
        	{
        		lastPkgName = splitName[splitName.length - 1];
        	}
        	if(TextUtils.isEmpty(lastPkgName)){
        		Log_Level_Control_tag = lastPkgName;
        	}
        }
        tag_extend = getVersionTag(context);
    }
    
    public static String getVersionTag(Context context) {
    	return Consts.version;
     
    }
    /**
     * 鍚勭被绾у埆鏃ュ織杈撳嚭鍑芥暟
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg){
        writeLog(android.util.Log.DEBUG, tag, msg, null, CALL_LOG_LEVEL);
    }
    public static void d(String tag, String msg, Throwable e){
        writeLog(android.util.Log.DEBUG, tag, msg, e, CALL_LOG_LEVEL);
    }
    public static void i(String tag, String msg){
        writeLog(android.util.Log.INFO, tag, msg, null, CALL_LOG_LEVEL);
    }
    public static void i(String tag, String msg, Throwable  e){
        writeLog(android.util.Log.INFO, tag, msg, e, CALL_LOG_LEVEL);
    }
    public static void w(String tag, String msg){
        writeLog(android.util.Log.WARN, tag, msg, null, CALL_LOG_LEVEL);
    }
    public static void w(String tag, String msg, Throwable e){
        writeLog(android.util.Log.WARN, tag, msg, e, CALL_LOG_LEVEL);
    }
    public static void e(String tag, String msg){
        writeLog(android.util.Log.ERROR, tag, msg, null, CALL_LOG_LEVEL);
    }    
    public static void e(String tag, String msg, Throwable e){
        writeLog(android.util.Log.ERROR, tag, msg, e, CALL_LOG_LEVEL);
    }
    public static void v(String tag, String msg){
        writeLog(android.util.Log.VERBOSE, tag, msg, null, CALL_LOG_LEVEL);
    }
    public static void v(String tag, String msg, Throwable e){
        writeLog(android.util.Log.VERBOSE, tag, msg, e, CALL_LOG_LEVEL);
    }
    
    /**
     * 鑾峰彇寮傚父淇℃伅鍫嗘爤瀛楃涓?
     * @param ex
     * @return
     */
    public static String getStackTraceString(Throwable ex)
    {
        return android.util.Log.getStackTraceString(ex);
    }

    /**
     * 杈撳嚭鏃ュ織锛屼笖鍦ㄦ棩蹇椾腑浣撶幇绾跨▼ID锛屾棩蹇楀唴瀹规湯灏炬坊鍔犺皟鐢ㄦ棩蹇楃殑婧愭枃浠跺悕銆佽鍙蜂俊鎭?
     * 渚嬪锛?br>
     * <pre>
     * 06-27 09:08:1.708 I/Phone+  (14077): [main-1]PHONE_STATE(Phone.java:212)
     * </pre>
     * @param priority 鏃ュ織绾у埆
     * @param tag 鏍囩
     * @param msg 鏃ュ織鏂囨湰鍐呭
     * @param th 寮傚父淇℃伅
     * @param bt 鍫嗘爤娣卞害
     */
	private static synchronized void writeLog(int priority, String tag, String msg, Throwable th, int bt)
    {
		if (!android.util.Log.isLoggable(Log_Level_Control_tag, priority)){
			return;
		}
			
    	try
    	{
    	    // 鎷兼帴绾跨▼ID
    	    msg = "[" + Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + "]" + msg;
    	    
    	    // 鎷兼帴婧愭枃浠躲?琛屽彿
            StackTraceElement[] st = new Throwable().getStackTrace();
            if (st.length > bt)
            {
                msg += "(" + lastPkgName + "/" + st[bt].getFileName() + ":" + st[bt].getLineNumber() + ")";
            }
            else
            {
                msg += "(" + lastPkgName + "/unknown source)";
            }
    	    
            // 濡傛湁寮傚父锛屾嫾鎺ュ紓甯镐俊鎭?
            if (null != th)
            {
                msg += '\n' + getStackTraceString(th);
            }
            
    	    android.util.Log.println(priority, tag_extend + tag, msg);
	    	
    	} catch (Exception we) {
    		Log.e(Log_Level_Control_tag, "call writeLog cause:" + we.toString(), we);
		}
    }
}
