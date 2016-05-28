package com.gyt.servicedemo.service.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/5/28.
 */
public class SingleMessengerService extends Service {

    public static final String TAG = SingleMessengerService.class.getSimpleName();

//    private Messenger mMessenger = new Messenger(new MyHandler());

    private Messenger mMessenger;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate " + Thread.currentThread().getName());

        HandlerThread th = new HandlerThread("ss");
        th.start();
        Looper looper = th.getLooper();
        MyHandler myHandler = new MyHandler(looper);
        mMessenger = new Messenger(myHandler);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "onRebind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 1) {
                Log.e(TAG, "sayHello");
                int i = 10;
                while (i > 0) {
                    Log.e(TAG, "i: " + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i --;
                }
            }
        }
    }
}
