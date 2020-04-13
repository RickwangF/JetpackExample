package com.rick.jetpackexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Bundle;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityItemDecorationBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemDecorationActivity extends AppCompatActivity {

    ActivityItemDecorationBinding mBind;

    SimpleAdapter adapter;

    List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_item_decoration);
        titleList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String title = "标题" + i;
            titleList.add(title);
        }

        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBind.recyclerView.setLayoutManager(layoutManager);
        mBind.recyclerView.setHasFixedSize(true);
        mBind.recyclerView.addItemDecoration(new SimpleItemDecoration(this, R.color.colorTitle, 10, 0));
        adapter = new SimpleAdapter(titleList, this);
        mBind.recyclerView.setAdapter(adapter);
    }
}
