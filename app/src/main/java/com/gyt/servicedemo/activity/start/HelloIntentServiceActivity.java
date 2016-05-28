package com.gyt.servicedemo.activity.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gyt.servicedemo.R;
import com.gyt.servicedemo.service.start.HelloIntentService;
import com.gyt.servicedemo.service.start.LifeCycleService;

/**
 * startService方式启动服务
 * 生命周期
 */
public class HelloIntentServiceActivity extends Activity {

    public static final String TAG = HelloIntentServiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_start_intentservice);
        Log.e(TAG, Thread.currentThread().getName());
    }

    public void startService(View view) {
        Intent intent = new Intent(this, HelloIntentService.class);
        intent.putExtra("args",10);
        startService(intent);
    }
}
