package com.MyAndroidCollection.util.muticard;



import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.MyAndroidCollection.util.LogX;
import com.MyAndroidCollection.util.muticard.MutiCard.SupportMode;


public class MutiCardFactory {
	
	
	private static final  String TAG="mutiCardFactory";
	
	 /**
     * 支持双卡双待的制�?
     */
    private static SupportMode IS_Multi_SIM_ENABLED = SupportMode.MODE_SUPPORT_UNKNOWN;
    
    
    /**
     * mutiCard 接口实现�?
     */
    private static MutiCard instance;
    
    
    

    /**
     * @param context
     * @return
     * 
     */
    public static MutiCard createIfGemini()
    {
        isMultiSimEnabled();
     
        // 防止createIfGemini返回instance为NULL，导致上次调用崩溃，改成这种写法，默认返回IfGeminiHwImpl
        if (IS_Multi_SIM_ENABLED == SupportMode.MODE_SUPPORT_MTK_GEMINI)
        {
            instance = MutiCardMTKImpl.getInstance();
        }
        else
        {
            instance = MutiCardHwImpl.getInstance();
        }
        
        return instance;
        
    }
    
    
    /**
     * 是否支持双卡功能
     * 
     * @return
     */
     public static boolean isMultiSimEnabled()
    {
        boolean support = false;

        if (IS_Multi_SIM_ENABLED != SupportMode.MODE_SUPPORT_UNKNOWN)
        {
            if(IS_Multi_SIM_ENABLED == SupportMode.MODE_SUPPORT_HW_GEMINI || IS_Multi_SIM_ENABLED == SupportMode.MODE_SUPPORT_MTK_GEMINI)
            {
                support = true;
            }
        }
        else
        {
            try
            {
                if (isMtkGeminiSupport())
                {
                    IS_Multi_SIM_ENABLED = SupportMode.MODE_SUPPORT_MTK_GEMINI;
                    support = true;
                }
                else if (isHwGeminiSupport())
                {
                    IS_Multi_SIM_ENABLED = SupportMode.MODE_SUPPORT_HW_GEMINI;
                    support = true;
                }
                else
                {
                    IS_Multi_SIM_ENABLED = SupportMode.MODE_NOT_SUPPORT_GEMINI;
                }
            }
            catch (Exception e)
            {
                // NoExtAPIException
                LogX.d(TAG, " " +e.toString(),e);
            }
            catch (Error error)
            {
                // NoClassDefFoundError
                LogX.d(TAG, "" +error.toString(),error);
            }
        }
        return support;
    }
     
 
   
 
     /***
      * 反射方法�?MSimTelephonyManager.getDefault().isMultiSimEnabled();
      * 原来写的方法不对，有可能类型会发生变化�?
      * @return
      */
	public static boolean isHwGeminiSupport() {
		boolean support = false;
		try {
			Object o = MutiCardHwImpl.getDefaultMSimTelephonyManager();
			if (null != o) {
				Method isMultiSimEnabled = o.getClass().getMethod(
						"isMultiSimEnabled");
				support = (Boolean) isMultiSimEnabled.invoke(o);
			}

		} catch (Exception e) {
			// NoExtAPIException
			LogX.d(TAG,
					"MSimTelephonyManager.getDefault().isMultiSimEnabled()?"
							+ e.toString(),e);
		} catch (Error e) {
			// NoClassDefFoundError
			LogX.d(TAG, "MSimTelephonyManager.getDefault().isMultiSimEnabled()"
					+ e.toString(),e);
		}
		LogX.i(TAG, "isHwGeminiSupport1 " + support);
		return support;
	}
     
    
  
  
    private static boolean isMtkGeminiSupport()
    {
    	
        boolean support = false;
        try
        {
            Class<?> clazz = Class
                    .forName("com.mediatek.common.featureoption.FeatureOption");
            Field field = clazz.getDeclaredField("MTK_GEMINI_SUPPORT");
            field.setAccessible(true);
            support = field.getBoolean(null);
        }
        catch (Exception e)
        {
            // NoExtAPIException
            LogX.d(TAG,"FeatureOption.MTK_GEMINI_SUPPORT"+e.toString(),e);
        }
        catch (Error error)
        {
            // NoClassDefFoundError
            LogX.d(TAG,"FeatureOption.MTK_GEMINI_SUPPORT"+ error.toString(),error);
        }
        LogX.i(TAG, "isMtkGeminiSupport " + support);
        return support;
    }

}
