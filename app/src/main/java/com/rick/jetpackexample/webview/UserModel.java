package com.rick.jetpackexample.webview;

import com.alibaba.fastjson.annotation.JSONField;

public class UserModel {

    private int uid;

    private String userName;

    private String token;

    private String accessToken;

    @JSONField(name = "isBeta")
    private boolean isBeta;

    public UserModel() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isBeta() {
        return isBeta;
    }

    public void setBeta(boolean beta) {
        isBeta = beta;
    }
}
