package com.gyt.servicedemo.service.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gyt.servicedemo.activity.aidl.AIDLThree;

/**
 * Created by Administrator on 2016/5/28.
 */
public class AIDLThreeService extends Service {

    public static final String TAG = AIDLThreeService.class.getSimpleName();

    public static String sActionBroadCast = "com.gyt.servicedemo.activity.aidl.MyBroadCastOne";

    private IBinder mBinder = new AIDLThree.Stub() {
        @Override
        public void play() throws RemoteException {
            Log.e(TAG, "start play mp3");
            Intent intent = new Intent();
            intent.putExtra("Code", 1);
            intent.setAction(sActionBroadCast);
            AIDLThreeService.this.sendBroadcast(intent);
        }

        @Override
        public void stop() throws RemoteException {
            Log.e(TAG, "stop playing");
            Intent intent = new Intent();
            intent.putExtra("Code", 2);
            intent.setAction(sActionBroadCast);
            AIDLThreeService.this.sendBroadcast(intent);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
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


}
