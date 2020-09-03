package com.rick.jetpackexample.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityHorizontalGridBinding;

import java.util.ArrayList;
import java.util.List;

public class HorizontalGridActivity extends AppCompatActivity {

    ActivityHorizontalGridBinding mBind;
    HorizontalAdapter adapter;
    GridLayoutManager layoutManager;
    List<SimpleModel> itemList;

    private void initProperty() {
        itemList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_horizontal_grid);
        initProperty();
        initView();
        initViewModel();
    }

    private void initView() {
        layoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        adapter = new HorizontalAdapter(this, itemList);
        mBind.itemList.setLayoutManager(layoutManager);
        mBind.itemList.setAdapter(adapter);
    }

    private void initViewModel() {
        for (int i = 0; i < 20; i++) {
            SimpleModel simpleModel = new SimpleModel();
            simpleModel.setTitle("标题" + i);
            simpleModel.setImageUrl("");
            itemList.add(simpleModel);
        }
        adapter.setData(itemList);
    }
}