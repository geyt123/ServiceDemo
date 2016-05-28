package com.gyt.servicedemo.service.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/5/28.
 */
public class AIDLTwoService extends Service {

    public static final String TAG = AIDLTwoService.class.getSimpleName();

    public static String sActionBroadCast = "com.gyt.servicedemo.activity.aidl.MyBroadCastOne";

    private IBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        mBinder = new MyBindTwo();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    class MyBindTwo extends PLayerStub {

        @Override
        public void play() {
            Log.e(TAG, "start play mp3");
            Intent intent = new Intent();
            intent.putExtra("Code", 1);
            intent.setAction(sActionBroadCast);
            AIDLTwoService.this.sendBroadcast(intent);
        }

        @Override
        public void stop() {
            Log.e(TAG, "stop playing");
            Intent intent = new Intent();
            intent.putExtra("Code", 2);
            intent.setAction(sActionBroadCast);
            AIDLTwoService.this.sendBroadcast(intent);
        }
    }
}
