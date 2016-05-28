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

/**
 * Created by Administrator on 2016/5/28.
 */
public class BActivity extends Activity {

    public static final String TAG = BActivity.class.getSimpleName();

    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_bind_b);
    }

    public void startService(View view) {
        isBound = true;
        Intent intent = new Intent(this, LifeCycleService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }


    public void stopService(View view) {
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
            Log.e(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
            isBound = false;
        }
    };
}
