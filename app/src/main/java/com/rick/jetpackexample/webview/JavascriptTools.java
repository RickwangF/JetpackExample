package com.rick.jetpackexample.webview;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

public class JavascriptTools {

    @JavascriptInterface
    public String JD_GetUserInfo() {
        UserModel userModel = new UserModel();
        userModel.setUid(1);
        userModel.setUserName("Rick");
        userModel.setToken("1283102jkasdkjahsd012893jasdkj");
        userModel.setAccessToken("aljsdli123kasd0912jk31lk2j3");
        userModel.setBeta(true);
        String userJSONString = JSON.toJSONString(userModel);
        Log.e("html", "userInfo is " + userJSONString);
        return userJSONString;
    }

}
