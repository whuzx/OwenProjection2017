package com.MyAndroidCollection.util;



import android.content.Context;
import android.content.Intent;

import com.MyAndroidCollection.util.HwAccountConstants.FINGERPRINT;

/**
 * 鍙戦?骞挎挱缁熶竴澶勭悊绫?
 *
 */
public class BroadcastUtil {

	private static String TAG = "BroadcastUtil";

	/**
	 * 鍙戦?鐧诲綍鎴愬姛骞挎挱.
	 *
	 * @param context
	 * @param intent
	 */
	public static void sendLoginSuccessBroadcast(Context context, Intent intent) {
		if (intent == null || context == null) {
			return;
		}
		String action = HwAccountConstants.ACTION_LOGIN_SUCCESS;
		intent.setAction(action);
		LogX.v(TAG,
				"sendLoginSuccessBroadcast-->context = " + context.getClass().getName() + ", intent = "
						+ Proguard.getProguard(intent));
		context.sendBroadcast(intent);
	}

	/**
	 * 鍙戦?鍙栨秷鐧诲綍骞挎挱.
	 *
	 * @param context
	 * @param intent
	 */
	public static void sendLoginCancelBroadcast(Context context, Intent intent) {
		if (intent == null || context == null) {
			return;
		}
		String action = HwAccountConstants.ACTION_LOGIN_CANCEL;
		intent.setAction(action);
		LogX.v(TAG,
				"sendLoginCancelBroadcast-->context = " + context.getClass().getName() + ", intent = "
						+ Proguard.getProguard(intent));
		context.sendBroadcast(intent);
	}

	/**
	 * 鍙戦?鐧诲綍澶辫触骞挎挱.
	 *
	 * @param context
	 * @param intent
	 */
	public static void sendLoginFailedBroadcast(Context context, Intent intent) {
		if (intent == null || context == null) {
			return;
		}
		String action = HwAccountConstants.ACTION_LOGIN_FAILED;
		intent.setAction(action);
		LogX.v(TAG,
				"sendLoginFailedBroadcast-->context = " + context.getClass().getName() + ", intent = "
						+ Proguard.getProguard(intent));
		context.sendBroadcast(intent);
	}

	/**
	 *
	 *
	 * @param context
	 * @param intent
	 */
	public static void sendAccountRemoveBroadcast(Context context, Intent intent) {
		if (intent == null || context == null) {
			return;
		}
		String action = HwAccountConstants.ACTION_HWID_ACCOUNT_REMOVE;
		intent.setAction(action);
		LogX.v(TAG,
				"sendAccountRemoveBroadcast-->context = " + context.getClass().getName() + ", intent = "
						+ Proguard.getProguard(intent));
		context.sendBroadcast(intent);
	}

	/**
	 * 鍙戦?妫?煡瀵嗙爜骞挎挱.
	 *
	 * @param context
	 * @param intent
	 */
	public static void sendCheckPassSucBroadcast(Context context, Intent intent) {
		if (intent == null || context == null) {
			return;
		}
		String action = HwAccountConstants.ACTION_CHECKPSD_SUCCESS;
		intent.setAction(action);
		LogX.v(TAG,
				"sendCheckPassSucBroadcast-->context = " + context.getClass().getName() + ", intent = "
						+ Proguard.getProguard(intent));
		context.sendBroadcast(intent);
	}

	/**
	 * 鍙戦?鍙栨秷妫?煡瀵嗙爜骞挎挱.
	 *
	 * @param context
	 * @param intent
	 */
	public static void sendCheckPassCancelBroadcast(Context context, Intent intent) {
		if (intent == null || context == null) {
			return;
		}
		String action = HwAccountConstants.ACTION_CHECKPSD_CANCEL;
		intent.setAction(action);
		LogX.v(TAG, "sendCheckPassCancelBroadcast-->context = " + context.getClass().getName() + ", intent = "
				+ Proguard.getProguard(intent));
		context.sendBroadcast(intent);
	}

    /**
     * 鍙戦?妫?煡瀵嗙爜澶辫触骞挎挱.
     *
     * @param context
     * @param intent
     */
    public static void sendCheckPassFailedBroadcast(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        String action = HwAccountConstants.ACTION_CHECKPSD_FAILED;
        intent.setAction(action);
        LogX.v(TAG, "sendCheckPassCancelBroadcast-->context = " + context.getClass().getName() + ", intent = "
                + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }

	/**
     * 鍙戦?寮?惎浜戞湇鍔″箍鎾?
     *
     * @param context
     * @param intent
     */
    public static void sendOpenCloudServiceBroadcast(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogX.i(TAG, "sendOpenCloudServiceVroadcast, intent or context is null");
            return;
        }
        String action = HwAccountConstants.ACTION_OPEN_CLOUDSERVICE;
        intent.setAction(action);
        LogX.v(TAG, "sendOpenCloudServiceVroadcast-->context = " + context.getClass().getName() + ", intent = "
                + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }

    /**
     * 鍙戦?璐︽埛棰勫垹闄ゅ箍鎾?
     *
     * @param context
     * @param intent
     */
    public static void sendBeforeRemoveBroadcast(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogX.i(TAG, "sendBeforeRemoveBroadcast, intent or context is null");
            return;
        }
        String action = HwAccountConstants.ACTION_PREPARE_LOGOUT_ACCOUNT;
        intent.setAction(action);
        LogX.v(TAG, "sendBeforeRemoveBroadcast-->context = " + context.getClass().getName() + ", intent = "
                + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }

    /**
     * 鍙戦?璐︽埛娉ㄩ攢澶辫触骞挎挱.
     *
     * @param context
     * @param intent
     */
    public static void sendLogoutFailBroadcast(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogX.i(TAG, "sendLogoutFailBroadcast, intent or context is null");
            return;
        }
        String action = HwAccountConstants.ACTION_LOGOUT_FAIL;
        intent.setAction(action);
        LogX.v(TAG, "sendLogoutFailBroadcast-->context = " + context.getClass().getName() + ", intent = "
                + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }

    /**
     * 鍙戦?鍙栨秷鎸囩汗楠岃瘉骞挎挱.
     *
     * @param context
     * @param intent
     */
    public static void sendFingerCancelBroadcast(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        String action = FINGERPRINT.ACTION_FINGER_CANCEL;
        intent.setAction(action);
        LogX.v(TAG,
                "sendFingerCancelBroadcast-->context = " + context.getClass().getName() + ", intent = "
                        + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }

    /**
     * 鍙戦?鎸囩汗楠岃瘉鎴愬姛骞挎挱.
     *
     * @param context
     * @param intent
     */
    public static void sendFingerSuccessBroadcast(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        String action = FINGERPRINT.ACTION_FINGER_SUCCESS;
        intent.setAction(action);
        LogX.v(TAG, "sendFingerSuccessBroadcast-->context = " + context.getClass().getName() + ", intent = "
                        + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }

    /**
     * 鍙戦?鍙栨秷娉ㄩ攢骞挎挱.
     *
     * @param context
     * @param intent
     */
    public static void sendLogoutCancelBroadcast(Context context) {
        Intent intent = new Intent(HwAccountConstants.ACTION_LOGOUT_CANCEL);
        LogX.v(TAG,
                "sendLogoutCancelBroadcast-->context = " + context.getClass().getName() + ", intent = "
                        + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }

    /**
     * 鍙戦?骞挎挱閫氱煡涓氬姟澶村儚宸蹭慨鏀?
     *
     * @param context
     * @param intent
     */
    public static void sendHeadpicChangeBroadcast(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogX.i(TAG, "sendHeadpicChangeBroadcast intent or context is null");
            return;
        }
        String action = HwAccountConstants.Cloud.ACTION_HEAD_PIC_CHANGE;
        intent.setAction(action);
        LogX.v(TAG,
                "sendLogoutCancelBroadcast-->context = " + context.getClass().getName() + ", intent = "
                        + Proguard.getProguard(intent));
        context.sendBroadcast(intent);
    }
}
