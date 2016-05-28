package com.gyt.servicedemo.activity.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.gyt.servicedemo.R;
import com.gyt.servicedemo.service.messenger.SingleMessengerService;

/**
 * Created by Administrator on 2016/5/28.
 */
public class SingleMessengerServiceActivity extends Activity {

    public static final String TAG = SingleMessengerServiceActivity.class.getSimpleName();
    private boolean isBound = false;

    private Messenger mMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_messenger_single);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, SingleMessengerService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
        isBound = true;
    }

    public void unbindService(View view) {
        if(isBound) {
            unbindService(conn);
            isBound = false;
        }
    }

    public void sayHello(View view) {
        Message msg = Message.obtain();
        msg.arg1 = 1;
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mHandler.sendEmptyMessage(0);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("mm", "handleMessage");
        }
    };

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
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
            mMessenger = null;
            isBound = false;
        }
    };
}
