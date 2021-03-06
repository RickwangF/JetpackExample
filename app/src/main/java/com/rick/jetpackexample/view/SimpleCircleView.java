package com.rick.jetpackexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.rick.jetpackexample.R;

public class SimpleCircleView extends View {

    private int mWidth = 200;

    private int mHeight = 200;

    private int mCirclrColor;

    private Paint mPaint;

    public SimpleCircleView(Context context) {
        super(context);
        initPaint();
    }

    public SimpleCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attrTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleCircleView);
        mCirclrColor = attrTypedArray.getColor(R.styleable.SimpleCircleView_circle_color, Color.GRAY);
        attrTypedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mCirclrColor);
        mPaint.setStrokeWidth(10.0f);
        mPaint.setStyle(Paint.Style.STROKE);
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

        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float height = getHeight() - getPaddingTop() - getPaddingBottom();

        float r = Math.min(width, height)/2;
        canvas.drawCircle(getPaddingLeft() + width/2, getPaddingTop() + height/2, r, mPaint);
    }
}
