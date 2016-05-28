package com.gyt.servicedemo.service.aidl;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by Administrator on 2016/5/28.
 */
public abstract class PLayerStub extends Binder implements IPlayer {

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        reply.writeString(data.readString());
        if (code == 1) {
            this.play();
        } else if(code == 2) {
            this.stop();
        }
        return true;
    }

    public abstract void play();
    public abstract void stop();
}
