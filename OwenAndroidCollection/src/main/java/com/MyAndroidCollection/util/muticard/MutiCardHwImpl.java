package com.MyAndroidCollection.util.muticard;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.PendingIntent;
import android.telephony.TelephonyManager;

import com.MyAndroidCollection.util.LogX;

/**
 * 海�? ，高�?双卡方案
 * 
 * @author z0020101
 * 
 */
public class MutiCardHwImpl implements MutiCard {

	private static final String TAG = "MutiCardHwImpl";
	private static final int SUB0 = -1;

	/**
	 * 对象静�?实例
	 */
	private static MutiCardHwImpl instance;

	/**
	 * 返回对象实例
	 * 
	 * @return
	 */
	public synchronized static MutiCardHwImpl getInstance() {

		if (null == instance) {
			instance = new MutiCardHwImpl();
		}
		return instance;
	}

	@Override
	public int getIccCardStatus(int subscription) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
   

    public static void sendTextMessage1(String destinationAddress,
            String scAddress, String text, PendingIntent sentIntent,
            PendingIntent deliveryIntent, int subscription) {
        Class<?>[] clsArray = new Class<?>[] { String.class, String.class,
                String.class, PendingIntent.class, PendingIntent.class,
                int.class };
        Object[] objArray = new Object[] { destinationAddress, scAddress, text,
                sentIntent, deliveryIntent, subscription };
        try {
            Class<?> MSimSmsManagerClass = Class.forName("android.telephony.MSimSmsManager");
            Method sendTextMessage = MSimSmsManagerClass.getDeclaredMethod("sendTextMessage", clsArray);
            sendTextMessage.invoke(getMSimSmsManager(), objArray);
        } catch (Exception e) {
            LogX.e(TAG, " sendTextMessage wrong " + e.toString(), e);
        }
    }
    

	//	  MSimSmsManager.getDefault().sendTextMessage(destinationAddress, scAddress, text, sentIntent, deliveryIntent,
	//                subscription);
	@Override
	public void sendTextMessage(String destinationAddress, String scAddress,
			String text, PendingIntent sentIntent,
			PendingIntent deliveryIntent, int subscription) {
		  
		
		 Class<?>[] clsArray = new Class<?>[] { String.class, String.class,
	                String.class, PendingIntent.class, PendingIntent.class,
	                int.class };
	        Object[] objArray = new Object[] { destinationAddress, scAddress, text,
	                sentIntent, deliveryIntent, subscription };
	        
		Method sendTextMessage;
		try {
		    Object simSmsManager = getDefaultMSimSmsManager();
		    if(null != simSmsManager) {
	            sendTextMessage = simSmsManager.getClass()
	                    .getDeclaredMethod("sendTextMessage",clsArray);
	            sendTextMessage.invoke(simSmsManager,objArray);
		    }
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	
		
		
	}


	/***
      * 反射方法�?MSimTelephonyManager.getDefault().getDefaultSubscription();
      * 原来写的方法不对，有可能类型会发生变化�?
      * @return
      */

	@Override
	public int getDefaultSubscription() {
		int defaultSubscription = 0;
		try {
			Object object = getDefaultMSimTelephonyManager();
			if (null != object) {
				Method getDefaultSubscription = object.getClass().getMethod(
						"getDefaultSubscription");

				defaultSubscription = (Integer) getDefaultSubscription
						.invoke(object);
			}

		} catch (Exception e) {
			LogX.d(TAG, " getDefaultSubscription wrong " + e.toString(),e);
			defaultSubscription = -1;
		}
		return defaultSubscription;
	}

	@Override
	public String getDeviceId(int sub) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 反射方法�?MSimTelephonyManager.getDefault().getSubscriberId(sub);
	 * 原来写的方法不对，有可能类型会发生变化�?
	 */
	@Override
	public String getSubscriberId(int sub) {
		String subscriberId = "";
		Class<?>[] claArray = new Class<?>[] { int.class };
		Object[] objArray = new Object[] { sub };

		try {

			Object object = getDefaultMSimTelephonyManager();
			if (null != object) {
				Method getSubscriberId = object.getClass().getMethod(
						"getSubscriberId", claArray);
				subscriberId = (String) getSubscriberId
						.invoke(object, objArray);

			}

		} catch (Exception e) {
			LogX.d(TAG, "getSubscriberId exception:" + e.toString(),e);
		}
		if (null == subscriberId) {
			subscriberId = "";
		}

		return subscriberId;

	}

	@Override
	public int getPreferredDataSubscription() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	/****
	 * 反射方法�?MSimTelephonyManager.getDefault().getSubscriberId(sub);
	 * 原来写的方法不对，有可能类型会发生变化�?
	 */
	public int getSimState(int subscrption) {
	
		int simState = 0;
		if (subscrption == SUB0) {
			simState = TelephonyManager.SIM_STATE_READY;
		}
		Class<?>[] clsArray = new Class<?>[] { int.class };
		Object[] objArray = new Object[] { subscrption };
		try {
			Object object=getDefaultMSimTelephonyManager();
			
			if ( null != object ) {
				Method getSimState = object.getClass().getDeclaredMethod(
						"getSimState", clsArray);
				simState = (Integer) getSimState.invoke(
						object, objArray);	
			}
		
		} catch (Exception e) {
			LogX.d(TAG, " getSimState wrong " + e.toString(),e);
		}
		return simState;
	}

	@Override
	public int getPhoneType(int sub) {
		// TODO Auto-generated method stub
		return 0;
	}

	//MSimTelephonyManager.getDefault()
	public static Object getDefaultMSimTelephonyManager() {
		
	
		
		Object mSimTelephonyManager = null;
		try {
			Class<?> MSimTelephonyManagerClass = Class
					.forName("android.telephony.MSimTelephonyManager");
			Method getDefault = MSimTelephonyManagerClass
					.getDeclaredMethod("getDefault");
			mSimTelephonyManager = getDefault.invoke(MSimTelephonyManagerClass);
		} catch (Exception e) {
			LogX.d(TAG, " getDefaultMSimTelephonyManager wrong " + e.toString(),e);
		}
		return mSimTelephonyManager;
	}
	
	
	//  MSimTelephonyManager.getDefault();
	public static Object getDefaultMSimSmsManager() {
		Object mSimSmsManager = null;
		try {
			Class<?> MSimSmsManager = Class
					.forName("android.telephony.MSimSmsManager");
			Method getDefault = MSimSmsManager
					.getDeclaredMethod("getDefault");
			mSimSmsManager = getDefault.invoke(MSimSmsManager);
		} catch (Exception e) {
			LogX.d(TAG, " getDegaultMSimSmsManager wrong " + e.toString(),e);
		}
		return mSimSmsManager;
	}
	
	
	 public static Object getMSimSmsManager() {
	        Object mSimSmsManager = null;
	        try {
	            Class<?> MSimSmsManagerClass = Class.forName("android.telephony.MSimSmsManager");
	            Method getDefault = MSimSmsManagerClass.getDeclaredMethod("getDefault");
	            mSimSmsManager = getDefault.invoke(MSimSmsManagerClass);
	        } catch (Exception e) {
	            LogX.d(TAG, " getMSimSmsManager wrong " + e.toString(),e);
	        }
	        return mSimSmsManager;
	        
	       
	        
	       
	  }


	@Override
	/****
	 * 反射方法：SimTelephonyManager.getDefault().getSimOperator(sub);
	 * 原来写的方法不对，有可能类型会发生变化�?
	 */
	public String getSimOperator(int sub) {
		String simOperator = "";
		Class<?>[] claArray = new Class<?>[] { int.class };
		Object[] objArray = new Object[] { sub };

		try {
			Object object = getDefaultMSimTelephonyManager();
			if (null != object) {
				Method getSimOperator = object.getClass().getMethod(
						"getSimOperator", claArray);
				simOperator = (String) getSimOperator.invoke(object, objArray);
			}

		} catch (Exception e) {
			LogX.d(TAG, "getSimOperator exception:" + e.toString(),e);
		}
		if (null == simOperator) {
			simOperator = "";
		}

		return simOperator;
	}

}
