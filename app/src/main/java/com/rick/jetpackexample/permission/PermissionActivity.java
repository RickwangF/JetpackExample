package com.rick.jetpackexample.permission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rick.jetpackexample.MainActivity;
import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityPermissionBinding;
import com.rick.jetpackexample.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends AppCompatActivity {

    ActivityPermissionBinding mBind;

    public static final int WRITE_EXTERNAL_STORAGE_PERMISSION = 1;

    public static final int EXTERNAL_STORAGE_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_permission);
        mBind.requestSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSinglePermission();
            }
        });
        mBind.requestMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMultiplePermissions();
            }
        });
        mBind.selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromLibrary();
            }
        });
    }

    private void requestSinglePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION);
                } else {
                    gotoPermissionActivity();
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION);
            }

        } else {
            Toast.makeText(this, "你已经有了该权限", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestMultiplePermissions() {
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (permissionList.size() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), EXTERNAL_STORAGE_PERMISSION);
                } else {
                    gotoPermissionActivity();
                }
            } else {
                ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), EXTERNAL_STORAGE_PERMISSION);
            }
        } else {
            Toast.makeText(this, "你已经有了该权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已同意写SD卡的权限", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "写SD卡的权限没有获取允许", Toast.LENGTH_LONG).show();
                }
                break;
            case EXTERNAL_STORAGE_PERMISSION:
                for (int i = 0; i < grantResults.length; i++) {
                    int result = grantResults[i];
                    String permission = permissions[i];
                    if (result != PackageManager.PERMISSION_GRANTED && permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "不同意读SD卡的权限", Toast.LENGTH_LONG).show();
                    }
                    if (result != PackageManager.PERMISSION_GRANTED && permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "不同意写SD卡的权限", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void gotoPermissionActivity() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("SD卡写权限申请")
                .setMessage("写SD卡的权限需要您的同意，否则应用的功能无法正常使用")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goHuaWeiMainager();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    private void goHuaWeiMainager() {
        try {
            Intent intent = new Intent(getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(comp);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "跳转失败", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void selectImageFromLibrary() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // both of two work
        //intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                if (data.getData() != null) {
                    String path = FileUtils.getFilePathByUri(this, data.getData());
                    Log.e("ActivityResult", "path is " + path);
                }
            }
        }
    }
}
