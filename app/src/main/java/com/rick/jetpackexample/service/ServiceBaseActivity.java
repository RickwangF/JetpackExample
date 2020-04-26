package com.rick.jetpackexample.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityServiceBaseBinding;

public class ServiceBaseActivity extends AppCompatActivity {

    ActivityServiceBaseBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_base);
        mBinding.serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceBaseActivity.this, ServiceExeActivity.class);
                startActivity(intent);
            }
        });
    }
}
