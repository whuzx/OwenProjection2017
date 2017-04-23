package com.MyAndroidCollection.receiver;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class EventThread extends Thread {

    private static final String TAG="EventThread";
    Context context;
    Bundle bundle;
    PendingResult pendingResult;
    public EventThread(Context context, Bundle bundle )
    {
        super("EventRunable");

        this.context = context;
        this.bundle = bundle;
    }
    public EventThread(Context context, Bundle bundle,
            PendingResult pendingResult) {
       super("EventRunable");
       this.context = context;
       this.bundle = bundle;
       this.pendingResult=pendingResult;
    }
    @Override
    public void run() {
        try {
            if (null == bundle) {
                return;
            }
            sleep(20000);
            Log.i(TAG, "sleeping...");
            pendingResult.finish();

        }catch(Exception exception){

        }
}
}
