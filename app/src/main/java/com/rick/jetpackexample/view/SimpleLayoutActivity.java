package com.rick.jetpackexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivitySimpleLayoutBinding;

public class SimpleLayoutActivity extends AppCompatActivity {

    ActivitySimpleLayoutBinding mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_simple_layout);
    }
}