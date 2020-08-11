package com.rick.jetpackexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.utils.StringUtils;

public class SimpleSwitch extends Switch {

    private Paint textPaint;
    private String offTitle;
    private String onTitle;

    public SimpleSwitch(Context context) {
        super(context);
    }

    public SimpleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOnOffTitle(context, attrs);
    }

    public SimpleSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initOnOffTitle(context, attrs);
    }

    public SimpleSwitch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initOnOffTitle(context, attrs);
    }

    private void initOnOffTitle(Context context, AttributeSet attrs) {
        TypedArray onOffArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleSwitch);
        offTitle = onOffArray.getString(R.styleable.SimpleSwitch_off_title);
        onTitle = onOffArray.getString(R.styleable.SimpleSwitch_on_title);
        if (!StringUtils.isEmpty(offTitle)) {
            Log.e("View", "offTitle is " + offTitle);
        }
        if (!StringUtils.isEmpty(onTitle)) {
            Log.e("View", "onTitle is " + onTitle);
        }
        onOffArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
