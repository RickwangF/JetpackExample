package com.rick.jetpackexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SimpleLayout extends ConstraintLayout {
    public SimpleLayout(Context context) {
        super(context);
    }

    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
