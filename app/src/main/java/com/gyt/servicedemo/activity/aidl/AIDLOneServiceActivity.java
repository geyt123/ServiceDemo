package com.gyt.servicedemo.activity.aidl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gyt.servicedemo.R;
import com.gyt.servicedemo.service.aidl.AIDLOneService;
import com.gyt.servicedemo.service.bind.LocalService;

/**
 * Created by Administrator on 2016/5/28.
 */
public class AIDLOneServiceActivity extends Activity {

    public static final String TAG = AIDLOneServiceActivity.class.getSimpleName();

    private boolean isBound = false;

    private IBinder mBinder;

    private MyBroadCast mMyBroadCast = new MyBroadCast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_aidl_one_service);
        IntentFilter intentFilter = new IntentFilter(AIDLOneService.sActionBroadCast);
        registerReceiver(mMyBroadCast, intentFilter);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, AIDLOneService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
        isBound = true;
    }

    public void unbindService(View view) {
        if(isBound) {
            unbindService(conn);
            isBound = false;
        }
    }

    public void play(View view) {
        if(isBound) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                mBinder.transact(1, data, reply, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(View view) {
        if(isBound) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                mBinder.transact(2, data, reply, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBound) {
            unbindService(conn);
            isBound = false;
        }
        unregisterReceiver(mMyBroadCast);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected()");
           mBinder = service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected()");
            mBinder = null;
            isBound = false;
        }
    };


    class MyBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra("Code", 1);
            if(code == 1) {
                Toast.makeText(AIDLOneServiceActivity.this, "Play", Toast.LENGTH_SHORT).show();
            } else if(code == 2){
                Toast.makeText(AIDLOneServiceActivity.this, "Stop", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
