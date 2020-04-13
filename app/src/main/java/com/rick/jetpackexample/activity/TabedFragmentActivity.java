package com.rick.jetpackexample.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.util.Log;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityTabedFragmentBinding;

public class TabedFragmentActivity extends AppCompatActivity {

    ActivityTabedFragmentBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Life Circle", "Tab Activity ----------------------------- onCreate");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tabed_fragment);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life Circle", "Tab Activity ----------------------------- onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life Circle", "Tab Activity ----------------------------- onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life Circle", "Tab Activity ----------------------------- onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life Circle", "Tab Activity ----------------------------- onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life Circle", "Tab Activity ----------------------------- onDestroy");
    }

    private void initView() {
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(), R.id.vp_main);
        mBinding.vpMain.setAdapter(adapter);
        mBinding.tlTab.setupWithViewPager(mBinding.vpMain);
    }
}
