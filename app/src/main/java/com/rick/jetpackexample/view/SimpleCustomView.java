package com.rick.jetpackexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SimpleCustomView extends View {

    public SimpleCustomView(Context context) {
        super(context);
    }

    public SimpleCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
