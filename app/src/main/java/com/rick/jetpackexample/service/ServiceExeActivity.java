package com.rick.jetpackexample.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityServiceExeBinding;

public class ServiceExeActivity extends AppCompatActivity {

    ActivityServiceExeBinding mBinding;

    SimpleService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (SimpleService.MyBinder) service;
            myBinder.service_conenect_Activity(5);
            Log.e("Service", "Service Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Service", "Service Disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_exe);
        mBinding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceExeActivity.this, SimpleService.class);
                intent.putExtra("operation", 1);
                startService(intent);
            }
        });
        mBinding.pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceExeActivity.this, SimpleService.class);
                intent.putExtra("operation", 2);
                startService(intent);
            }
        });
        mBinding.finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceExeActivity.this, SimpleService.class);
                intent.putExtra("operation", 3);
                startService(intent);
            }
        });
        mBinding.bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceExeActivity.this, SimpleService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
        mBinding.unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceExeActivity.this, SimpleService.class);
                unbindService(connection);
            }
        });
        mBinding.stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceExeActivity.this, SimpleService.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(ServiceExeActivity.this, SimpleService.class);
//        unbindService(connection);
        stopService(intent);
    }
}
