package com.gyt.servicedemo.service.start;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/5/27.
 */
public class HelloService extends Service {

    public static final String TAG = HelloService.class.getSimpleName();

    private DrawHandler mDrawHandler;
    private PlayHandler mPlayHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate " + Thread.currentThread().getName());

        HandlerThread drawThread = new HandlerThread("Draw", Process.THREAD_PRIORITY_BACKGROUND);
        drawThread.start();
        Looper drawLooper = drawThread.getLooper();
        mDrawHandler = new DrawHandler(drawLooper);

        HandlerThread playThread = new HandlerThread("Play", Process.THREAD_PRIORITY_BACKGROUND);
        playThread.start();
        Looper playLooper = playThread.getLooper();
        mPlayHandler = new PlayHandler(playLooper);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand startId: " + startId);
        Message msg = Message.obtain();
        msg.obj = startId;
        int code = intent.getIntExtra("Code", 0);
        if(code == 0) {
            mDrawHandler.sendMessage(msg);
        } else {
            mPlayHandler.sendMessage(msg);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestory");
    }


    private class DrawHandler extends Handler {
        public DrawHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            long count = 10;
            while (count > 0) {
                Log.e(TAG, "drawing " + count);
                count --;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, "drawing is over");

            //stopSelf(int startId)：在其参数startId跟最后启动该service时生成的ID相等时才会执行停止服务。
            //stopSelf()：直接停止服务。
            stopSelf((Integer) msg.obj);
        }
    }

    private class PlayHandler extends Handler {
        public PlayHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            long count = 10;
            while (count > 0) {
                Log.e(TAG, "playing " + count);
                count --;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, "playing is over");

            //stopSelf(int startId)：在其参数startId跟最后启动该service时生成的ID相等时才会执行停止服务。
            //stopSelf()：直接停止服务。
            stopSelf((Integer) msg.obj);
        }
    }
}
