package com.rick.jetpackexample.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.rick.jetpackexample.MainActivity;
import com.rick.jetpackexample.R;
import com.rick.jetpackexample.thread.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleService extends Service {

    private List<Ticket> mTickets;

    private Timer timer;

    private boolean pause;

    private MyBinder myBinder;

    public SimpleService() {
        this.mTickets = new ArrayList<>();
        init();
    }

    private void init() {
        for (int i = 0; i < 100; i++) {
            Ticket ticket = new Ticket();
            ticket.setName("票"+ (i+1));
            ticket.setNumber(i+1);
            mTickets.add(ticket);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.myBinder = new MyBinder();
        Log.e("Service", "Service onCreate");
//        //添加下列代码将后台Service变成前台Service
//        //构建"点击通知后打开MainActivity"的Intent对象
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
//
//        //新建Builer对象
//        Notification.Builder builer = new Notification.Builder(this);
//        builer.setContentTitle("前台服务通知的标题");//设置通知的标题
//        builer.setContentText("前台服务通知的内容");//设置通知的内容
//        builer.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
//        builer.setContentIntent(pendingIntent);//设置点击通知后的操作
//
//        Notification notification = builer.build();//将Builder对象转变成普通的notification
//        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Service", "Service onBind");
        return myBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "Service onStartCommand");
        int operation = intent.getIntExtra("operation", 0);
        switch (operation) {
            case 1:
                start();
                Log.e("Service", "Service start()");
                break;
            case 2:
                pause();
                Log.e("Service", "Service pause()");
                break;
            case 3:
                stop();
                Log.e("Service", "Service stop()");
                break;
            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service", "Service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Service", "Service Destroy");
        pause();
        stop();
    }

    private void start() {
        timer = new Timer("timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (pause) {
                    return;
                }

                if (mTickets.size() == 0) {
                    return;
                }
                int length = mTickets.size();
                Ticket ticket = mTickets.get(length - 1);
                mTickets.remove(ticket);
                Log.e("Service", "CurrentThread is " + Thread.currentThread() + " now sales ticket is " + JSON.toJSON(ticket));
            }
        }, 0, 1000);
    }

    private void pause() {
        pause = true;
    }

    private void stop() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    class MyBinder extends Binder {
        public void service_conenect_Activity(int params) {
            Log.e("Service", "Service bind Activity and Activity execute Service Function And Params is " + params);
        }
    }
}
