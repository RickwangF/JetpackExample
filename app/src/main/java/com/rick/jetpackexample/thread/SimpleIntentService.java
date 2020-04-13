package com.rick.jetpackexample.thread;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class SimpleIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SimpleIntentService() {
        super("IntentServerOne");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("IntentService", "onCreate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        int taskName = intent.getIntExtra("taskName", 0);
        Log.e("IntentService", "do task " + String.valueOf(taskName));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("IntentService", "onBind");
        return super.onBind(intent);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("IntentService", "onStart");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e("IntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("IntentService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("IntentService", "onDestroy");
    }
}
