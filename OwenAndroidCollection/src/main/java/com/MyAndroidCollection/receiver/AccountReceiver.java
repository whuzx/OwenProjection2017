package com.MyAndroidCollection.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AccountReceiver extends BroadcastReceiver {

    private static final String TAG = "AccountReceiver";
    private static final String ACTION_OPEN_CLOUDSERVICE = "com.huawei.hwid.ACTION_LOGIN_OPEN_CLOUDSERVICE";
    private static final String ACTION_PREPARE_LOGOUT_ACCOUNT = "com.huawei.hwid.ACTION_PREPARE_LOGOUT_ACCOUNT";
    private static final String ACTION_LOGOUT_FAIL = "com.huawei.hwid.ACTION_LOGOUT_FAIL";
    private static final String ACTION_HWID_ACCOUNT_REMOVE = "com.huawei.hwid.ACTION_REMOVE_ACCOUNT";
    private static final String ACTION_HEAD_PIC_CHANGE = "com.huawei.hwid.ACTION_HEAD_PIC_CHANGE";
    private static final String ACTION_LOGOUT_CANCEL = "com.huawei.hwid.ACTION_LOGOUT_CANCEL";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == context || null == intent) {
            Log.i(TAG, "no context or intent");
            return;
        }
        String action = intent.getAction();



        if (ACTION_OPEN_CLOUDSERVICE.equals(action)) {
            int openState = intent.getIntExtra("openCloud", 0);
            Log.d(TAG, "receive open cloud broadcast, state :" + openState);
        } else if (ACTION_PREPARE_LOGOUT_ACCOUNT.equals(action)) {
            String userId = intent.getStringExtra("userId");
            Log.d(TAG, "receive prepare logout broadcast, userId :" + userId);
        } else if (ACTION_LOGOUT_FAIL.equals(action)) {
            String userId = intent.getStringExtra("userId");
            Log.d(TAG, "receive logout fail broadcast, userId :" + userId);
        } else if (ACTION_HWID_ACCOUNT_REMOVE.equals(action)) {
            String userId = intent.getStringExtra("userId");

            Log.d(TAG, "receive account removed broadcast, userId :" + userId);

            PendingResult pendingResult = goAsync();
            Bundle bundle = new Bundle();
            bundle.putBoolean("ID",true);
            new EventThread(context,bundle,pendingResult).start();


        } else if (ACTION_LOGOUT_CANCEL.equals(action)) {
            Log.d(TAG, "receive logout cancel broadcast");
        } else if (ACTION_HEAD_PIC_CHANGE.equals(action)) {
            boolean isChange = intent.getBooleanExtra("headPicChange", false);
            String url = intent.getStringExtra("fileUrlB");
            Log.d(TAG, "receive headPic change broadcast, isChange :" + isChange
                    + ",url:" + url);
        }
    }



}
