package com.rick.jetpackexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityStartBinding;
import com.rick.jetpackexample.databinding.FragmentTargetOneBinding;

import java.lang.annotation.Target;

public class StartActivity extends AppCompatActivity implements TargetOneFragment.TargetOneFragmentClickListener, TargetTwoFragment.TargetTwoFragmentClickLisnter {

    FragmentManager mFragmentManager;

    ActivityStartBinding mBinding;

    TargetOneFragment oneFragment;

    TargetTwoFragment twoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Life Circle", "Activity ----------------------------- onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_start);
        initFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life Circle", "Activity ----------------------------- onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life Circle", "Activity ----------------------------- onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life Circle", "Activity ----------------------------- onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life Circle", "Activity ----------------------------- onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life Circle", "Activity ----------------------------- onDestroy");
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        TargetOneFragment fragmentOne = new TargetOneFragment();
        fragmentOne.listener = this;
        transaction.add(R.id.fl_container, fragmentOne);
        transaction.commit();
    }

    @Override
    public void tvFragmentTwoClicked() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        TargetTwoFragment fragmentTwo = new TargetTwoFragment();
        fragmentTwo.lisnter = this;
        transaction.replace(R.id.fl_container, fragmentTwo);
        transaction.commit();
    }

    @Override
    public void fragmentOneBtnClicked() {

    }
}
