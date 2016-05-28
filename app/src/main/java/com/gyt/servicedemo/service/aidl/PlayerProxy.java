package com.gyt.servicedemo.service.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by Administrator on 2016/5/28.
 */
public class PlayerProxy implements IPlayer {

    public IBinder mBinder;

    public PlayerProxy(IBinder iBinder) {
        mBinder = iBinder;
    }

    @Override
    public void play() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeString("playing");
        try {
            mBinder.transact(1, data, reply, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeString("stop");
        try {
            mBinder.transact(2, data, reply, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
