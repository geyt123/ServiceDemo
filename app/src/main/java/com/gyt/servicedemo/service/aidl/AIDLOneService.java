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
public class AIDLOneService extends Service implements Runnable {

    public static final String TAG = AIDLOneService.class.getSimpleName();

    public static String sActionBroadCast = "com.gyt.servicedemo.activity.aidl.MyBroadCastOne";

    private IBinder mBinder;
    private EventHandler mEventHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        mBinder = new MyBindOne();
        //诞生一个子线程和MQ
        Thread th = new Thread(this);
        th.start();
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
        mEventHandler.getLooper().quit();
    }

    @Override
    public void run() {
        Looper.prepare();
        mEventHandler = new EventHandler(Looper.myLooper());
        Looper.loop();
    }


    class EventHandler extends Handler {

        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String obj = msg.obj.toString();
            if(obj.equals("play")) {
                Log.e(TAG, "start play mp3");
                Intent intent = new Intent();
                intent.putExtra("Code", 1);
                intent.setAction(sActionBroadCast);
                AIDLOneService.this.sendBroadcast(intent);
            } else if(obj.equals("stop")){
                Log.e(TAG, "stop playing");
                Intent intent = new Intent();
                intent.putExtra("Code", 2);
                intent.setAction(sActionBroadCast);
                AIDLOneService.this.sendBroadcast(intent);
            }
        }
    }

    class MyBindOne extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    //将Message 丢到子线程的MQ中 to play mp3
                    Message msg1 = Message.obtain();
                    msg1.obj = "play";
                    mEventHandler.sendMessage(msg1);
                    break;
                case 2:
                    //将Message 丢到子线程的MQ中 to stop playing
                    Message msg2 = Message.obtain();
                    msg2.obj = "stop";
                    mEventHandler.sendMessage(msg2);
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }
}
