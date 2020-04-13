package com.rick.jetpackexample.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityToolbarMainBinding;

public class ToolbarMainActivity extends AppCompatActivity {

    ActivityToolbarMainBinding mBinding;

    Toolbar toolbar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_toolbar_main);

        toolbar = (Toolbar) mBinding.toolBar;
        toolbar.setTitle("");
//        toolbar.setTitle("工具栏");
//        toolbar.setTitleTextColor(R.color.colorTitle);
//        toolbar.setSubtitle("副标题");
//        toolbar.setSubtitleTextColor(R.color.colorSubTitle);
//        toolbar.setLogo(R.drawable.ic_earth_128f60100);
        setSupportActionBar(mBinding.toolBar);
        registerForContextMenu(mBinding.tvCenter);
        mBinding.tvPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ToolbarMainActivity.this, v);
                popupMenu.inflate(R.menu.menu_context);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.context_one:
                                Toast.makeText(ToolbarMainActivity.this, "one", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.context_two:
                                Toast.makeText(ToolbarMainActivity.this, "two", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.context_three:
                                Toast.makeText(ToolbarMainActivity.this, "three", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
//        toolbar.setNavigationIcon(R.drawable.ic_list_128f60100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_edit:
                Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_one:
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_two:
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_three:
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
