package com.rick.jetpackexample.thread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityIntentServiceBinding;

public class IntentServiceActivity extends AppCompatActivity {

    ActivityIntentServiceBinding mBinding;

    private int callNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intent_service);
        mBinding.mannulCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentServiceActivity.this, SimpleIntentService.class);
                intent.putExtra("taskName", callNumber);
                startService(intent);
                mBinding.tvCall.setText("请" + callNumber + "号顾客到服务台");
                callNumber++;
            }
        });
        mBinding.finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.tvCall.setText("今天已停止叫号");
            }
        });
    }


}
