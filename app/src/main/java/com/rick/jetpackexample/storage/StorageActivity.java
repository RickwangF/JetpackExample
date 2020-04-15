package com.rick.jetpackexample.storage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityStorageBinding;
import com.rick.jetpackexample.utils.DateUtils;
import com.rick.jetpackexample.utils.FileUtils;
import com.rick.jetpackexample.utils.ListUtils;
import com.rick.jetpackexample.utils.StringUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StorageActivity extends AppCompatActivity {

    ActivityStorageBinding mBind;

    public static final int EXTERNAL_STORAGE_PERMISSION = 2;

    Uri imageUri;

    Uri textUri;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_storage);
        mBind.selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromLibrary();
            }
        });
        mBind.saveImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(imageUri);
            }
        });
        mBind.textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveTextWithStream();
                }
                return false;
            }
        });
        mBind.saveTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTextWithStream();
            }
        });
        mBind.readTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTextFromStorage();
            }
        });
        mBind.listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllFilesInFilesDirectory();
            }
        });
        mBind.readFirstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFirstTextFilesInFilesDirectory();
            }
        });
        mBind.createDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDirecotry();
            }
        });
        mBind.createFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });
        mBind.copyFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyFile();
            }
        });
    }

    private void copyFile() {
        File dirFile = getExternalFilesDir(Environment.MEDIA_MOUNTED);
        String dirPath = "";
        String targetPath = "";
        if (dirFile != null) {
            dirPath = dirFile.getAbsolutePath();
        }
        List<String> files = FileUtils.ListFilesAt(dirPath, "text", false);
        if (ListUtils.isEmpty(files)) {
            Toast.makeText(this, "没有文本文件", Toast.LENGTH_SHORT).show();
            return;
        }

        String path = files.get(0);
        Log.e("Storage", "source text file is " + path);

        targetPath = dirPath + File.separator + "text" + File.separator + DateUtils.getDateStringAtNow() + ".text";
        Log.e("Storage", "target text file is " + targetPath);

        int result = FileUtils.copyFile(path, targetPath);
        switch (result) {
            case FileUtils.RESULT_SUCCESS:
                Log.e("Storage", "文件复制成功");
                break;
            case FileUtils.RESULT_FAILURE:
                Log.e("Storage", "文件复制失败");
                break;
        }
    }

    private void createDirecotry() {
        if (!FileUtils.sdCardIsMounted()) {
            Toast.makeText(this, "SD卡还未挂载", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = getExternalFilesDir(Environment.MEDIA_MOUNTED);
        // Environment.getExternalStorageDirectory(); Android10 不能在SD卡根目录创建文件夹
        Log.e("Storage", "root path is " + file.getAbsolutePath());

        String text = mBind.textInput.getText().toString();
        if (text.equals("")) {
            Toast.makeText(this, "请输入文件夹名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String dirPath = file.getAbsolutePath() + File.separator + text;
        Log.e("Storage", "要创建的文件夹是 " + dirPath);
        int result = FileUtils.createDirectory(dirPath);
        switch (result) {
            case FileUtils.RESULT_SUCCESS:
                Log.e("Storage", "文件夹创建成功");
                break;
            case FileUtils.RESULT_FAILURE:
                Log.e("Storage", "文件夹创建失败");
                break;
            case FileUtils.RESULT_EXISTS:
                Log.e("Storage", "文件夹已存在");
                break;
        }
    }

    private void createFile() {
        if (!FileUtils.sdCardIsMounted()) {
            Toast.makeText(this, "SD卡还未挂载", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = getExternalFilesDir(Environment.MEDIA_MOUNTED);

        String fileName = mBind.textInput.getText().toString();
        if (fileName.equals("")) {
            Toast.makeText(this, "请输入文件名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String fileNamePath = file.getAbsolutePath() + File.separator + "text" + File.separator + fileName;
        Log.e("Storage", "要创建的文件是 " + fileNamePath);
        int result = FileUtils.createFile(fileNamePath);
        switch (result) {
            case FileUtils.RESULT_SUCCESS:
                Log.e("Storage", "文件创建成功");
                break;
            case FileUtils.RESULT_FAILURE:
                Log.e("Storage", "文件创建失败");
                break;
            case FileUtils.RESULT_EXISTS:
                Log.e("Storage", "文件已存在");
                break;
        }
    }

    private void listAllFilesInFilesDirectory() {
        File dirFile = getExternalFilesDir(Environment.MEDIA_MOUNTED);
        String dirPath = "";
        if (dirFile != null) {
            dirPath = dirFile.getAbsolutePath();
        }
        List<String> files = FileUtils.ListFilesAt(dirPath, true);
        Log.e("Storage", "files in externalFilesDir is " + files);
    }

    private void readFirstTextFilesInFilesDirectory() {
        File dirFile = getExternalFilesDir(Environment.MEDIA_MOUNTED);
        String dirPath = "";
        if (dirFile != null) {
            dirPath = dirFile.getAbsolutePath();
        }
        List<String> files = FileUtils.ListFilesAt(dirPath, "text", false);
        if (ListUtils.isEmpty(files)) {
            Toast.makeText(this, "没有文本文件", Toast.LENGTH_SHORT).show();
            return;
        }

        String path = files.get(0);
        Log.e("Storage", "first text file is " + path);

        readTextFromPath(path);
    }

    private void selectTextFromStorage() {
        boolean permissionRes = requestReadAndWritePermissions();
        if (!permissionRes) {
            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 2);
    }

    private void readTextFromPath(String path) {
        if (StringUtils.isEmpty(path)) {
            Toast.makeText(this, "路径为空", Toast.LENGTH_SHORT).show();
            return;
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            char[] buffer = new char[1024];
            int temp = 0;
            StringBuilder text = new StringBuilder();
            do {
                String subText = new String(buffer, 0, temp);
                Log.e("Storage", "subText is " + subText);
                text.append(subText);
            } while ((temp = fileReader.read(buffer)) != -1);
            if (!StringUtils.isEmpty(text.toString())) {
                mBind.textInput.setText(text.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("Storage", "找不到文件");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Storage", "读取出错");
        }
    }

    private void readTextFromStream(Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptor != null) {
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                FileInputStream inputStream = new FileInputStream(fileDescriptor);

                byte[] buffer = new byte[1024];
                int temp = 0;
                StringBuilder text = new StringBuilder();
                do {
                    String subText = new String(buffer, 0, temp);
                    Log.e("Storage", "subText is " + subText);
                    text.append(subText);
                } while ((temp = inputStream.read(buffer)) != -1);
                if (!StringUtils.isEmpty(text.toString())) {
                    mBind.textInput.setText(text.toString());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("Storage", "获取ParcelFileDescriptor失败");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Storage", "读取失败");
        }

    }

    private void saveTextToInternalStorage() {
        String text = mBind.textInput.getText().toString();
        if (StringUtils.isEmpty(text)) {
            Toast.makeText(this, "请先输入文字", Toast.LENGTH_SHORT).show();
            return;
        }

        File dirFile = getExternalFilesDir(Environment.MEDIA_MOUNTED);
        String dirPath = "";
        if (dirFile != null) {
            dirPath = dirFile.getAbsolutePath();
        }
        String filePath = dirPath + "/" + "text-" + DateUtils.getDateStringAtNow() + ".text";
        Log.e("Storage", "text save path is " + filePath);

        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(text);
            fileWriter.flush();
            Log.e("Storage", "写入成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Storage", "写入失败");
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Storage", "fileWriter 关闭失败");
                }
            }
        }
    }

    private void saveTextWithStream() {
        String text = mBind.textInput.getText().toString();
        if (StringUtils.isEmpty(text)) {
            Toast.makeText(this, "请先输入文字", Toast.LENGTH_SHORT).show();
            return;
        }

        File dirFile = getExternalFilesDir(Environment.MEDIA_MOUNTED);
        String dirPath = "";
        if (dirFile != null) {
            dirPath = dirFile.getAbsolutePath();
        }
        String filePath = dirPath + "/" + "text-" + DateUtils.getDateStringAtNow() + ".text";
        Log.e("Storage", "text save path is " + filePath);

        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(filePath);
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(bytes);
            Log.e("Storage", "保存成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Storage", "保存失败");
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Storage", "关闭fileOutputStream失败");
                }
            }
        }
    }

    private void saveImage(Uri uri) {
        File dirFile = getExternalFilesDir(Environment.MEDIA_MOUNTED);
                // getExternalFilesDir(Environment.MEDIA_MOUNTED);
        String dirPath = "";
        if (dirFile != null) {
            dirPath = dirFile.getAbsolutePath();
        }
        String filePath = dirPath + "/" + "image-" + DateUtils.getDateStringAtNow() + ".png";
        Log.e("Storage", "image save path is " + filePath);

        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            FileOutputStream outputStream = new FileOutputStream(filePath);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
                outputStream.flush();
                outputStream.close();
                Log.e("Storage", "写入成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Storage", "写入失败");
        }
    }

    private void selectImageFromLibrary() {
        boolean permissionRes = requestReadAndWritePermissions();
        if (!permissionRes) {
            Toast.makeText(this, "请先同意权限申请", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    if (data != null && data.getData() != null) {
                        imageUri = data.getData();
                        Log.e("Storage", "data is " + data.getData().toString());
                        String path = FileUtils.getFilePathByUri(this, data.getData());
                        Log.e("Storage", "path is " + path);
                        loadImageFromPath(data.getData());
                    }
                    break;
                case 2:
                    if (data != null && data.getData() != null) {
                        textUri = data.getData();
                        Log.e("Storage", "data is " + data.getData().toString());
                        readTextFromStream(textUri);
                    }
            }
        }
    }

    private void loadImageFromPath(Uri uri) {
        if (uri == null) {
            Toast.makeText(this, "没有获取到图片的路径", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentResolver contentResolver = getContentResolver();
        try {
            //File file = new File(path);
            //Uri readUri = FileProvider.getUriForFile(this, "com.rick.jetpackexample.SimpleFileProvider", file);
            Log.e("Storage", "Uri is " + uri);
            Bitmap bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
            if (bitmap != null) {
                mBind.selectedImage.setVisibility(View.VISIBLE);
                mBind.selectedImage.setImageBitmap(bitmap);
            }

        } catch (FileNotFoundException e) {
            Log.e("Storage", "没有找到该文件");
        }
    }

    private boolean requestReadAndWritePermissions() {
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

            return false;
        } else {
            return true;
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
