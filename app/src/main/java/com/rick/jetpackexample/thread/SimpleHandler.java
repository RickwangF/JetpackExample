package com.rick.jetpackexample.thread;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SimpleHandler extends Handler {

    private SimpleHandlerHandleMessage messageHandler;

    public SimpleHandlerHandleMessage getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(SimpleHandlerHandleMessage messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if (messageHandler != null) {
            messageHandler.handleHandleMessage(msg);
        }
    }
}
