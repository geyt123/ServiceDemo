package com.gyt.servicedemo.service.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TwoWayMessengerService extends Service {

    public static final String TAG = TwoWayMessengerService.class.getSimpleName();

    private Messenger mServerMessenger = new Messenger(new MyHandler());

    private Messenger mClientMessenger;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate " + Thread.currentThread().getName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return mServerMessenger.getBinder();
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

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "obj: " + msg.obj.toString());
            Message message = Message.obtain();
            message.obj = "I am fine";
            mClientMessenger = msg.replyTo;
            try {
                mClientMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
