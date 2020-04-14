package com.rick.jetpackexample.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.rick.jetpackexample.R;

import java.io.File;

public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        listInternalStorageDirectory();
        listExternalStorageDirectory();
        listSDCardStorageDirectory();
    }

    private void listInternalStorageDirectory() {
        String fileDir = this.getFilesDir().getAbsolutePath();
        String cacheDir = this.getCacheDir().getAbsolutePath();

        Log.e("Storage", "internal fileDir is " + fileDir);
        Log.e("Storage", "internal cacheDir is " + cacheDir);
    }

    private void listExternalStorageDirectory() {
        File[] files = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
        if (files.length > 0) {
            for (File file: files) {
                Log.e("Storage", "External fileDir is " + file.getAbsolutePath());
            }
        }

        File[] cacheFiles = getExternalCacheDirs();
        if (cacheFiles.length > 0) {
            for (File file: cacheFiles) {
                Log.e("Storage", "External cacheDir is " + file.getAbsolutePath());
            }
        }
    }

    private void listSDCardStorageDirectory() {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            String dcimDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            Log.e("Storage", "External SDCard DCIM Dir is " + dcimDir);
        }
    }
}
