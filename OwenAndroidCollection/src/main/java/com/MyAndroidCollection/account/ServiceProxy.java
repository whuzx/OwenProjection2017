
package com.MyAndroidCollection.account;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;

import com.MyAndroidCollection.util.LogX;


/**
 * 
 * @author z0020101
 *
 */
public abstract class ServiceProxy {
    private static final String TAG = "ServiceProxy";

    protected final Context mContext;
    protected final Intent mIntent;
    private Runnable mRunnable = new ProxyRunnable();
    private ProxyTask mTask;
    private String mName = " unnamed";
    private final ServiceConnection mConnection = new ProxyConnection();
    // Service call timeout (in seconds)
    private int mTimeout = 4;
    private boolean mDead = false;

    public abstract void onConnected(IBinder binder);

    public ServiceProxy(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
    }

    private class ProxyConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder binder) {
            onConnected(binder);
            LogX.v(TAG, "Connected: " + name.getShortClassName());

            // Run our task on a new thread
            new Thread(new Runnable() {
                public void run() {
                    Looper.prepare();
                   //杩愯run绋嬪簭锛屽紑濮嬫墽琛実etToken鏂规硶
                    runTask();
                    Looper.loop();
                }
            }).start();
        }

        public void onServiceDisconnected(ComponentName name) {
            LogX.v(TAG, "Disconnected: " + name.getShortClassName());
        }
    }

    public interface ProxyTask {
        public void run() throws RemoteException;
    }

    private class ProxyRunnable implements Runnable {
        @Override
        public void run() {
            Looper.prepare();
            try {
                mTask.run();
            } catch (RemoteException e) {
            }
            Looper.loop();
        }
    }

    public ServiceProxy setTimeout(int secs) {
        mTimeout = secs;
        return this;
    }

    public int getTimeout() {
        return mTimeout;
    }

    public void endTask() {
        try {
            mContext.unbindService(mConnection);
        } catch (IllegalArgumentException e) {
            // This can happen if the user ended the activity that was using the
            // service
            // This is harmless, but we've got to catch it
            
        }

        mDead = true;
        synchronized (mConnection) {
            LogX.v(TAG, "Task " + mName + " completed; disconnecting");
            mConnection.notify();
        }
    }

    private void runTask() {
        Thread thread = new Thread(mRunnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
    }

    public boolean setTask(ProxyTask task, String name) {
        mName = name;
        return setTask(task);
    }

    public boolean setTask(ProxyTask task) throws IllegalStateException {
        if (mDead) {
            throw new IllegalStateException();
        }
        mTask = task;

        LogX.v(TAG, "Bind requested for task " + mName);
        
        boolean bindResult=mContext.bindService(mIntent, mConnection,
                Context.BIND_AUTO_CREATE);
        
        LogX.i(TAG, "Bind requested for task " + mName  + "result is" + bindResult);

        return bindResult;
    }

}
