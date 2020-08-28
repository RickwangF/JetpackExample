package com.rick.jetpackexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityCustomViewBinding;

public class CustomViewActivity extends AppCompatActivity {

    ActivityCustomViewBinding mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_custom_view);
        mBind.centerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "center view clicked");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String eventName = "";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            eventName = "ACTION_DOWN";
            Log.e("Touch", "Activity onTouchEvent motion event down");
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            eventName = "ACTION_MOVE";
            Log.e("Touch", "Activity onTouchEvent motion event move");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            eventName = "ACTION_UP";
            Log.e("Touch", "Activity onTouchEvent motion event up");
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            eventName = "ACTION_CANCEL";
            Log.e("Touch", "Activity onTouchEvent motion event cancel");
        }
        boolean result = super.onTouchEvent(event);
        Log.e("Touch", "Activity onTouchEvent " + eventName + " result is " + result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        String eventName = "";
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            eventName = "ACTION_DOWN";
            Log.e("Touch", "Activity dispatchTouchEvent motion event down");
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            eventName = "ACTION_MOVE";
            Log.e("Touch", "Activity dispatchTouchEvent motion event move");
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            eventName = "ACTION_UP";
            Log.e("Touch", "Activity dispatchTouchEvent motion event up");
        } else if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
            eventName = "ACTION_CANCEL";
            Log.e("Touch", "Activity dispatchTouchEvent motion event cancel");
        }
        boolean result = super.dispatchTouchEvent(ev);
        Log.e("Touch", "Activity dispatchTouchEvent " + eventName + " result is " + result);
        return result;
    }
}
