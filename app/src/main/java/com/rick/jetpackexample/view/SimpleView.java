package com.rick.jetpackexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.rick.jetpackexample.R;

public class SimpleView extends View {

    private Context context;

    private int backgroundColor = Color.parseColor("#66000000");

    private int mWidth = 200;

    private int mHeight = 200;

    private Paint mPaint;

    private Paint shadowPaint;

    private Bitmap bitmap;

    public SimpleView(Context context) {
        super(context);
        initPaint();
    }

    public SimpleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleImageButton);
        backgroundColor = typedArray.getColor(R.styleable.SimpleImageButton_background_color, Color.argb(255, 0, 0, 0));
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(backgroundColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5.0f);

        shadowPaint = new Paint();
        shadowPaint.setShadowLayer(20, 20, 20, backgroundColor);
        bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.argb(255, 153, 153, 153));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        canvas.drawRoundRect(0, 0, width, height, 10, 10, mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        String eventName = "";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            eventName = "ACTION_DOWN";
            Log.e("Touch", "View dispatchTouchEvent motion event down");
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            eventName = "ACTION_MOVE";
            Log.e("Touch", "View dispatchTouchEvent motion event move");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            eventName = "ACTION_UP";
            Log.e("Touch", "View dispatchTouchEvent motion event up");
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            eventName = "ACTION_CANCEL";
            Log.e("Touch", "View dispatchTouchEvent motion event cancel");
        }
        boolean result = super.dispatchTouchEvent(event);
        Log.e("Touch", "View dispatchTouchEvent " + eventName + " result is " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String eventName = "";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            eventName = "ACTION_DOWN";
            Log.e("Touch", "View onTouchEvent motion event down");
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            eventName = "ACTION_MOVE";
            Log.e("Touch", "View onTouchEvent motion event move");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            eventName = "ACTION_UP";
            Log.e("Touch", "View onTouchEvent motion event up");
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            eventName = "ACTION_CANCEL";
            Log.e("Touch", "View onTouchEvent motion event cancel");
        }
        boolean result = super.onTouchEvent(event);
        Log.e("Touch", "View onTouchEvent " + eventName + " result is " + result);
        return result;
    }
}
