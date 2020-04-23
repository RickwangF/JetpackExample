package com.rick.jetpackexample.webview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityWebViewBinding;
import com.rick.jetpackexample.utils.KeyboardChangeListener;

import java.util.Arrays;

public class WebViewActivity extends AppCompatActivity implements KeyboardChangeListener.KeyboardListener{

    ActivityWebViewBinding mBind;

    WebSettings webSettings;

    ValueCallback<Uri[]> fileCallback;

    JavascriptTools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_web_view);

        initWebView();
        KeyboardChangeListener.create(this).setKeyboardListener(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mBind.webView.setDrawingCacheEnabled(true);
        mBind.webView.buildDrawingCache();
        mBind.webView.buildLayer();
        mBind.webView.resumeTimers();

        webSettings = mBind.webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setJavaScriptEnabled(true);
        tools = new JavascriptTools();
        tools.setInteractionHandler(new JavascriptTools.JavascriptToolsInteractionHandler() {
            @Override
            public void back(boolean isAll) {
                finish();
            }
        });
        mBind.webView.addJavascriptInterface(tools, "AndroidNative");
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setNeedInitialFocus(false);
        WebView.setWebContentsDebuggingEnabled(true);
        mBind.webView.requestFocus(View.FOCUS_DOWN);
        mBind.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("html","开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("html","加载结束");
            }

            // 链接跳转都会走这个方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("html","Url："+ url );
                view.loadUrl(url);// 强制在当前 WebView 中加载 url
                return true;
            }
        });
        mBind.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                Log.e("html", "filePathCallback is " + filePathCallback);
                fileCallback = filePathCallback;
                String[] acceptTypes = fileChooserParams.getAcceptTypes();
                String acceptString = "";
                if (acceptTypes != null && acceptTypes.length > 0) {
                    acceptString = Arrays.toString(acceptTypes);
                    Log.e("html", "acceptString is " + acceptString);
                }
                if (acceptString.contains("image")) {
                    showFileChooser(true);
                } else {
                    showFileChooser(false);
                }

                return true;
            }
        });
        mBind.webView.loadUrl("file:///android_asset/htmls/index.html#/article/detail");  // "file:///android_asset/htmls/index.html"
    }

    private void showFileChooser(boolean isImage) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        if (isImage) {
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        } else {
            intent.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*");
        }
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            fileCallback.onReceiveValue(null);
            fileCallback = null;
            return;
        }

        if (requestCode != 1) {
            fileCallback.onReceiveValue(null);
            fileCallback = null;
            return;
        }

        if (fileCallback == null) {
            return;
        }

        assert data != null;
        Uri uri = data.getData();
        if (uri != null) {
            fileCallback.onReceiveValue(new Uri[]{uri});
            Log.e("html", "select media path is " + uri);
            fileCallback = null;
        }
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        Log.e("html", "keyboardHeight is " + keyboardHeight);
        mBind.webView.evaluateJavascript("window.JDApp.KeyboardHideShow()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        mBind.webView.evaluateJavascript("window.JDApp.NativeBack()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });
    }
}
