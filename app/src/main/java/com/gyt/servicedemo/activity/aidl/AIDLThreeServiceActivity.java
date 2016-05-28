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
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gyt.servicedemo.R;
import com.gyt.servicedemo.service.aidl.AIDLOneService;
import com.gyt.servicedemo.service.aidl.AIDLThreeService;
import com.gyt.servicedemo.service.aidl.PlayerProxy;

/**
 * Created by Administrator on 2016/5/28.
 */
public class AIDLThreeServiceActivity extends Activity {

    public static final String TAG = AIDLThreeServiceActivity.class.getSimpleName();

    private boolean isBound = false;

    private AIDLThree mAIDLThree;

    private MyBroadCast mMyBroadCast = new MyBroadCast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_aidl_two_service);
        IntentFilter intentFilter = new IntentFilter(AIDLOneService.sActionBroadCast);
        registerReceiver(mMyBroadCast, intentFilter);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, AIDLThreeService.class);
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
            try {
                mAIDLThree.play();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(View view) {
        if(isBound) {
            try {
                mAIDLThree.stop();
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
            mAIDLThree = AIDLThree.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected()");
            mAIDLThree = null;
            isBound = false;
        }
    };


    class MyBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra("Code", 1);
            if(code == 1) {
                Toast.makeText(AIDLThreeServiceActivity.this, "Play", Toast.LENGTH_SHORT).show();
            } else if(code == 2){
                Toast.makeText(AIDLThreeServiceActivity.this, "Stop", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
