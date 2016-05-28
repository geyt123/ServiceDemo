package com.gyt.servicedemo.service.start;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2016/5/27.
 */
public class HelloIntentService extends IntentService {

    public static final String TAG = HelloIntentService.class.getSimpleName();

    public HelloIntentService() {
        super("HelloIntentService");
        Log.e(TAG, "HelloIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int args = intent.getIntExtra("args",0);
        while (args > 0) {
            Log.e(TAG, "args: " + args);
            args --;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate " +  Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand startId: " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}
