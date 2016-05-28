package com.gyt.servicedemo.activity.bind;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.gyt.servicedemo.R;
import com.gyt.servicedemo.service.bind.LifeCycleService;
import com.gyt.servicedemo.service.bind.StartBindLifeCycleService;

/**
 * Created by Administrator on 2016/5/28.
 */
public class StartBindLifeCycleServiceActivity extends Activity {

    public static final String TAG = StartBindLifeCycleServiceActivity.class.getSimpleName();
    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_bind_start_lifecycle);
    }

    public void startService(View view) {
        Intent intent = new Intent(this, StartBindLifeCycleService.class);
        startService(intent);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, StartBindLifeCycleService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
        isBound = true;
    }

    public void unbindService(View view) {
        if(isBound) {
            unbindService(conn);
            isBound = false;
        }
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, StartBindLifeCycleService.class);
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBound) {
            unbindService(conn);
            isBound = false;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
        }
    };

}
