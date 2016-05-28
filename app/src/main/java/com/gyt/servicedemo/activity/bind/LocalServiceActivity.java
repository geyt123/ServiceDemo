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
import com.gyt.servicedemo.service.bind.LocalService;

/**
 * Created by Administrator on 2016/5/28.
 */
public class LocalServiceActivity extends Activity {

    public static final String TAG = LocalServiceActivity.class.getSimpleName();

    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_bind_local_service);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
        isBound = true;
    }

    public void unbindService(View view) {
        if(isBound) {
            unbindService(conn);
            isBound = false;
        }
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
            Log.e(TAG, "onServiceConnected()");
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            binder.getService().sayHello();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected()");
            isBound = false;
        }
    };


}
