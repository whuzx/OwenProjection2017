package com.MyAndroidCollection.util.muticard;


import java.lang.reflect.Method;
import java.util.ArrayList;

import android.app.PendingIntent;

import com.MyAndroidCollection.util.LogX;

/**
 * MTK 双卡方案
 *
 * @author z0020101
 */
public class MutiCardMTKImpl implements MutiCard {

    private static final String TAG = "mutiCardMTKImpl";

    /**
     * 对象静�?实例
     */
    private static MutiCardMTKImpl instance;


    /**
     * 返回对象实例
     *
     * @return
     */
    public synchronized static MutiCardMTKImpl getInstance() {

        if (null == instance) {
            instance = new MutiCardMTKImpl();
        }
        return instance;
    }

    private MutiCardMTKImpl() {

    }

    @Override
    public int getIccCardStatus(int subscription) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void sendTextMessage(String destinationAddress, String scAddress,
                                String text, PendingIntent sentIntent,
                                PendingIntent deliveryIntent, int subscription) {

        ArrayList<String> parts = new ArrayList<String>();
        parts.add(text);
        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
        sentIntents.add(sentIntent);
        ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();
        deliveryIntents.add(deliveryIntent);
        sendMultipartTextMessageMTK(destinationAddress, scAddress, parts,
                subscription, sentIntents, deliveryIntents);

    }

    //	GeminiSmsManager.sendMultipartTextMessageGemini(destinationAddress,
    //	scAddress, parts, destinationPort, sentIntents,
    //	deliveryIntents);
    private void sendMultipartTextMessageMTK(String destinationAddress,
                                             String scAddress, ArrayList<String> parts, int destinationPort,
                                             ArrayList<PendingIntent> sentIntents,
                                             ArrayList<PendingIntent> deliveryIntents) {
        try {

            Class<?>[] clsArray = new Class<?>[]{String.class, String.class,
                    ArrayList.class, int.class, ArrayList.class,
                    ArrayList.class};

            Class<?> geminiSmsManager = Class
                    .forName("android.telephony.gemini.GeminiSmsManager");
            Method sendMultipartTextMessageGemini = geminiSmsManager
                    .getDeclaredMethod("sendMultipartTextMessageGemini", clsArray);
            sendMultipartTextMessageGemini.invoke(geminiSmsManager, destinationAddress,
                    scAddress, parts, destinationPort, sentIntents,
                    deliveryIntents);


        } catch (Exception e) {
            // NoExtAPIException
            LogX.d(TAG, "" + e.toString(), e);
        } catch (Error error) {
            // NoClassDefFoundError
            LogX.d(TAG, "" + error.toString(), error);
        }
    }

    @Override
    public int getDefaultSubscription() {
        return getDefaultSimMTK();
    }


    private static int getDefaultSimMTK() {
        try {
            Class<?> mSimTelephonyManagerClazz = Class
                    .forName("android.telephony.TelephonyManager");
            Method getDefaultSim = mSimTelephonyManagerClazz.getDeclaredMethod(
                    "getDefaultSim", null);
            Method getDefault = mSimTelephonyManagerClazz.getDeclaredMethod(
                    "getDefault", null);
            Object instance = getDefault.invoke(null, null);
            getDefaultSim.setAccessible(true);
            return (Integer) getDefaultSim.invoke(instance, null);
        } catch (Exception e) {
            // NoExtAPIException
            LogX.d(TAG, "" + e.toString(), e);
        } catch (Error error) {
            // NoClassDefFoundError
            LogX.d(TAG, "" + error.toString(), error);
        }
        return -1;
    }

    @Override
    public String getDeviceId(int sub) {
        // TODO Auto-generated method stub
        return null;
    }

    //	com.mediatek.telephony.TelephonyManagerEx.getDefault().getSubscriberId(arg0);
    @Override
    public String getSubscriberId(int sub) {
        String subscriberId = "";
        Class<?>[] claArray = new Class<?>[]{int.class};
        Object[] objArray = new Object[]{sub};

        try {
            Object object = getDefaultTelephonyManagerEx();

            if (null != object) {
                Method getSubscriberId = object.getClass().getMethod(
                        "getSubscriberId", claArray);
                subscriberId = (String) getSubscriberId
                        .invoke(object, objArray);
            }

        } catch (Exception e) {
            LogX.d(TAG, "getSubscriberId exception:" + e.toString(), e);
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
     * 反射方法：com.mediatek.telephony.TelephonyManagerEx.getDefault().getSimState(arg0);
     * 原来写的方法不对，有可能类型会发生变化�?
     */
    public int getSimState(int sub) {

        int simState = 0;

        Class<?>[] clsArray = new Class<?>[]{int.class};
        Object[] objArray = new Object[]{sub};
        try {
            Object object = getDefaultTelephonyManagerEx();
            if (null != object) {
                Method getSimState = object.getClass().getDeclaredMethod(
                        "getSimState", clsArray);
                simState = (Integer) getSimState.invoke(object, objArray);

            }

        } catch (Exception e) {
            LogX.d(TAG, " getSimState wrong " + e.toString(), e);
        }
        return simState;

    }

    @Override
    public int getPhoneType(int sub) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getSimOperator(int sub) {
        // TODO Auto-generated method stub
        return "";

    }

    private static Object getDefaultTelephonyManagerEx() {
        Object TelephonyManagerEx = null;
        try {
            Class<?> TelephonyManagerExClass = Class
                    .forName("com.mediatek.telephony.TelephonyManagerEx");
            Method getDefault = TelephonyManagerExClass
                    .getDeclaredMethod("getDefault");
            TelephonyManagerEx = getDefault.invoke(TelephonyManagerExClass);
        } catch (Exception e) {
            LogX.d(TAG, " getDefaultTelephonyManagerEx wrong " + e.toString(), e);
        }
        return TelephonyManagerEx;
    }
}
