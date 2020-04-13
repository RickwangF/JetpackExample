package com.rick.jetpackexample.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityHandlerThreadBinding;

public class HandlerThreadActivity extends AppCompatActivity {

    private ActivityHandlerThreadBinding mBinding;

    private MainHandler mMainHandler;

    private WorkHandler mWorkHandler;

    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_handler_thread);

        mMainHandler = new MainHandler();
        mHandlerThread = new HandlerThread("handlerThread");
        mHandlerThread.start();

        mWorkHandler = new WorkHandler(mHandlerThread.getLooper(), mMainHandler, mBinding);

        mBinding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "";
                mWorkHandler.sendMessage(message);
            }
        });
        mBinding.delayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = 2;
                message.obj = "";
                mWorkHandler.sendMessage(message);
            }
        });
        mBinding.finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandlerThread.quit();
                mBinding.tvResult.setText("今天已停止叫号");
            }
        });
        mBinding.jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HandlerThreadActivity.this, IntentServiceActivity.class);
                startActivity(intent);
            }
        });
    }

    private static class MainHandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    private static class WorkHandler extends Handler{

        private MainHandler mainHandler;

        private ActivityHandlerThreadBinding binding;

        WorkHandler(Looper looper, MainHandler mMainHandler, ActivityHandlerThreadBinding mBinding) {
            super(looper);
            this.mainHandler = mMainHandler;
            this.binding = mBinding;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    try {
                        Thread.sleep(1000);
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.tvResult.setText("请1号顾客到服务台");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        Thread.sleep(1000);
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.tvResult.setText("请2号顾客5分钟后到服务台");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
