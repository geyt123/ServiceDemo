package com.gyt.servicedemo.activity.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.gyt.servicedemo.R;
import com.gyt.servicedemo.service.start.HelloService;


/**
 * 针对于IntentService中
 * Handler测试
 */
public class HelloServiceActivity extends Activity {

    public static final String TAG = HelloServiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_start_helloservice);
        Log.e(TAG, Thread.currentThread().getName());
    }

    public void draw(View view) {
        Intent intent = new Intent(this, HelloService.class);
        intent.putExtra("Code", 0);
        startService(intent);
    }

    public void play(View view) {
        Intent intent = new Intent(this, HelloService.class);
        intent.putExtra("Code", 1);
        startService(intent);
    }
}
