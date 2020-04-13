package com.rick.jetpackexample.activity;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

public class RoundRectOutlineProvider extends ViewOutlineProvider {
    @Override
    public void getOutline(View view, Outline outline) {
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 35);
    }
}
