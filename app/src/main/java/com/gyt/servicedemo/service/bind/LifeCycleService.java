package com.gyt.servicedemo.service.bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * BindService
 * 生命周期
 */
public class LifeCycleService extends Service {

    public static final String TAG = LifeCycleService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate  " + Thread.currentThread().getName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return new MyBind();
    }

    private class MyBind extends Binder {}

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
}
