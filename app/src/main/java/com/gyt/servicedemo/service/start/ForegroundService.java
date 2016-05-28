package com.gyt.servicedemo.service.start;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gyt.servicedemo.R;

/**
 * Created by Administrator on 2016/5/27.
 */
public class ForegroundService extends Service {

    public static final String TAG = ForegroundService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");

        //启用前台服务，主要是startForeground()
        Notification notification = new Notification
                .Builder(this)
                .setContentTitle("快去休息！！！")
                .setContentText("一定保护眼睛,不然遗传给孩子，老婆跟别人跑啊。")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        startForeground(1, notification);



//        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        //读者可以修改此处的Minutes从而改变提醒间隔时间
//        //此处是设置每隔55分钟启动一次
//        //这是55分钟的毫秒数
//        int Minutes = 55 * 60 * 1000;
//        //SystemClock.elapsedRealtime()表示1970年1月1日0点至今所经历的时间
//        long triggerAtTime = SystemClock.elapsedRealtime() + Minutes;
//        //此处设置开启AlarmReceiver这个Service
//        Intent i = new Intent(this, AlarmReceiver.class);
//        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
//        //ELAPSED_REALTIME_WAKEUP表示让定时任务的出发时间从系统开机算起，并且会唤醒CPU。  manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");

//        //在Service结束后关闭AlarmManager
//        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);        Intent i = new Intent(this, AlarmReceiver.class);
//        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
//        manager.cancel(pi);

    }
}
