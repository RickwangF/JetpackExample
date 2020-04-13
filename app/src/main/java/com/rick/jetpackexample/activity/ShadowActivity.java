package com.rick.jetpackexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityShadowBinding;

public class ShadowActivity extends AppCompatActivity {

    ActivityShadowBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shadow);
        // 剪裁视图
        mBinding.clCenter.setOutlineProvider(new RoundRectOutlineProvider());
        mBinding.clCenter.setClipToOutline(true);
    }
}
