package com.rick.jetpackexample.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class SimpleLayout extends ConstraintLayout {

    private int mBackgroundColor = Color.RED;

    private int mWidth;

    private int mHeight;

    private List<View> subViews = new ArrayList<>();

    public SimpleLayout(Context context) {
        super(context);
    }

    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        SimpleView viewOne = new SimpleView(context);
        viewOne.setLayoutParams(layoutParams);
        subViews.add(viewOne);
        addView(viewOne);

        SimpleView viewTwo = new SimpleView(context);
        viewTwo.setLayoutParams(layoutParams);
        subViews.add(viewTwo);
        addView(viewTwo);

        SimpleView viewThree = new SimpleView(context);
        viewThree.setLayoutParams(layoutParams);
        subViews.add(viewThree);
        addView(viewThree);

        SimpleView viewFour = new SimpleView(context);
        viewFour.setLayoutParams(layoutParams);
        subViews.add(viewFour);
        addView(viewFour);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        int padding = 10;
        int separator = 10;
        int itemWidth = (mWidth - 30)/2;
        int itemHeight = (mHeight - 30)/2;
        for (int i = 0; i < subViews.size(); i++) {
            View subView = subViews.get(i);
            int mLeft = 0;
            int mTop = 0;
            int mRight = 0;
            int mBottom = 0;
            if (i == 0 || i == 2) {
                mLeft = padding;
                if (i == 0) {
                    mTop = padding;
                    mBottom = padding + itemHeight;
                } else {
                    mTop = padding + itemHeight + separator;
                    mBottom = mTop + itemHeight;
                }
                mRight = padding + itemWidth;
            } else {
                mLeft = padding + separator + itemWidth;
                if (i == 1) {
                    mTop = padding;
                    mBottom = padding + itemHeight;
                } else {
                    mTop = padding + itemHeight + separator;
                    mBottom = mTop + itemHeight;
                }
                mRight = mLeft + itemWidth;
            }
            subView.layout(mLeft, mTop, mRight, mBottom);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String eventName = "";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            eventName = "ACTION_DOWN";
            Log.e("Touch", "ViewGroup onTouchEvent motion event down");
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            eventName = "ACTION_MOVE";
            Log.e("Touch", "ViewGroup onTouchEvent motion event move");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            eventName = "ACTION_UP";
            Log.e("Touch", "ViewGroup onTouchEvent motion event up");
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            eventName = "ACTION_CANCEL";
            Log.e("Touch", "ViewGroup onTouchEvent motion event cancel");
        }
        boolean result = super.onTouchEvent(event);
        Log.e("Touch", "ViewGroup onTouchEvent " + eventName + "result is " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        String eventName = "";
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            eventName = "ACTION_DOWN";
            Log.e("Touch", "ViewGroup onInterceptTouchEvent motion event down");
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            eventName = "ACTION_MOVE";
            Log.e("Touch", "ViewGroup onInterceptTouchEvent motion event move");
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            eventName = "ACTION_UP";
            Log.e("Touch", "ViewGroup onInterceptTouchEvent motion event up");
        } else if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
            eventName = "ACTION_CANCEL";
            Log.e("Touch", "ViewGroup onInterceptTouchEvent motion event cancel");
        }
        boolean result = super.onInterceptTouchEvent(ev);
        Log.e("Touch", "ViewGroup onInterceptTouchEvent " + eventName + " result is " + result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        String eventName = "";
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            eventName = "ACTION_DOWN";
            Log.e("Touch", "ViewGroup dispatchTouchEvent motion event down");
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            eventName = "ACTION_MOVE";
            Log.e("Touch", "ViewGroup dispatchTouchEvent motion event move");
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            eventName = "ACTION_UP";
            Log.e("Touch", "ViewGroup dispatchTouchEvent motion event up");
        } else if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
            eventName = "ACTION_CANCEL";
            Log.e("Touch", "ViewGroup dispatchTouchEvent motion event cancel");
        }
        boolean result = super.dispatchTouchEvent(ev);
        Log.e("Touch", "ViewGroup dispatchTouchEvent " + eventName + " result is " + result);
        return result;
    }
}
