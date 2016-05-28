package com.gyt.servicedemo.activity.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gyt.servicedemo.R;
import com.gyt.servicedemo.service.start.ForegroundService;

/**
 * Created by Administrator on 2016/5/28.
 */
public class ForegroundServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_start_foreground);
    }

    public void startService(View view) {
        Intent intent = new Intent(this, ForegroundService.class);
        startService(intent);
    }
}
