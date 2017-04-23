package com.MyAndroidCollection.util.muticard;



import android.app.PendingIntent;


/***
 * 
 * @author z0020101
 *
 */
public interface MutiCard {
	
	  public enum SupportMode
	    {
	        /**
	         * 状�?未知
	         */
	        MODE_SUPPORT_UNKNOWN,

	        /**
	         * 不支持双�?
	         */
	        MODE_NOT_SUPPORT_GEMINI,

	        /**
	         * 支持华为归一化双卡接�?
	         */
	        MODE_SUPPORT_HW_GEMINI,

	        /**
	         * 支持MTK双卡接口
	         */
	        MODE_SUPPORT_MTK_GEMINI
	    };

	    /**
	     * 双卡的状�?
	     */
	    public static final int CARD_INVALID = 0;

	    public static final int CARD_VALID = 1;

	    public static final int CARD_NOT_INSERT = 2;
	    
	    
	    
	    /**
	     * 手机制式
	     */
	    public static final int DSDS_MODE_SINGLE = 0;

	    public static final int DSDS_MODE_CDMA_GSM = 1;

	    public static final int DSDS_MODE_UMTS_GSM = 2;

	    public static final int DSDS_MODE_TDSCDMA_GSM = 3;
	    
	    public static final String FEATURE_ENABLE_MMS = "enableMMS";
	    
	
	    
	    /**
	     * 
	     * 获取SIM卡状态（可用/不可�?未插卡）
	     * @param subscription
	     * @return
	     * int
	     */
	    int getIccCardStatus(int subscription);
	    
	    
	   
	    /**
	     * 
	     * 发�?文本消息
	     * @param destinationAddress
	     * @param scAddress
	     * @param text
	     * @param sentIntent
	     * @param deliveryIntent
	     * @param subscription
	     * void
	     */
	    void sendTextMessage(String destinationAddress, String scAddress, String text, PendingIntent sentIntent,
	            PendingIntent deliveryIntent, int subscription);
	    
	    

	    
	    /**
	     * 
	     * 获取默认�?
	     * @return
	     * int
	     */
	    int getDefaultSubscription();

	    /**
	     * 
	     * 获取设备IMEI/MEID号码
	     * @param sub
	     * @return
	     * String
	     */
	    String getDeviceId(int sub);

	    /**
	     * 
	     * 获取设备IMSI号码
	     * @param sub
	     * @return
	     * String
	     */
	    String getSubscriberId(int sub);

	    
	    int getPreferredDataSubscription();
	    

	    int getSimState(int sub);

	    
	    int getPhoneType(int sub);
	    
	    String getSimOperator(int sub);
	    
}
