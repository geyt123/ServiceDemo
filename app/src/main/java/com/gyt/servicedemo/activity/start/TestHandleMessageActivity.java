package com.gyt.servicedemo.activity.start;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.gyt.servicedemo.R;


/**
 * 针对于IntentService中
 * Handler测试
 */
public class TestHandleMessageActivity extends Activity {

    public static final String TAG = TestHandleMessageActivity.class.getSimpleName();

    private volatile Looper mServiceLooper;
    private volatile ServiceHandler mServiceHandler;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
           String str = msg.obj.toString();
            long count = 10;
            while (count > 0) {
                Log.e(TAG, str + "-" + count);
                count --;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, "run is over");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_start_test_handlerthread);
        Log.e(TAG, Thread.currentThread().getName());
    }

    public void handlerOne(View view) {
        HandlerThread thread = new HandlerThread("HandlerOne");
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
        Message msg = mServiceHandler.obtainMessage();
        msg.obj = "handlerOne";
        mServiceHandler.sendMessage(msg);
    }

    public void handlerTwo(View view) {
        HandlerThread thread = new HandlerThread("HandlerOne");
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
        Message msg = mServiceHandler.obtainMessage();
        msg.obj = "handlerTwo";
        mServiceHandler.sendMessage(msg);
    }
}
