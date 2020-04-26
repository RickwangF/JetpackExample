package com.rick.jetpackexample.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityBroadcastBinding;

public class BroadcastActivity extends AppCompatActivity {

    ActivityBroadcastBinding mBind;

    SimpleBroadcastReceiver broadcastReceiver;

    public static final String Broadcast_Action = "action_broadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_broadcast);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new SimpleBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Broadcast_Action);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void initView () {
        mBind.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Broadcast_Action);
                sendBroadcast(intent);
            }
        });
    }
}
