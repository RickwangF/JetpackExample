package com.rick.jetpackexample.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.SimpleImageButtonBinding;

public class SimpleImageButton extends ConstraintLayout {

    private Context mContext;

    private SimpleImageButtonBinding mBind;

    private ImageView imageView;

    private TextView textView;

    private Paint mPaint;

    private int backgroundColor;

    private int textColor;

    private String text;

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SimpleImageButton(Context context) {
        super(context);
        setWillNotDraw(false);
        this.mContext = context;
        initView();
    }

    public SimpleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleImageButton);
        backgroundColor = typedArray.getColor(R.styleable.SimpleImageButton_background_color, Color.argb(255, 222, 212, 247));
        text = typedArray.getText(R.styleable.SimpleImageButton_text).toString();
        textColor = typedArray.getColor(R.styleable.SimpleImageButton_text_color, Color.argb(255, 117, 64, 238));
        typedArray.recycle();
        initView();
    }

    private void initView() {
        mBind = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.simple_image_button, this, true);
        //LayoutInflater.from(mContext).inflate(R.layout.simple_image_button, this);
        //textView = findViewById(R.id.tv_text);
        //imageView = findViewById(R.id.iv_img);
        mBind.tvText.setText(text);
        mBind.tvText.setTextColor(textColor);
        //textView.setText(text);
        //textView.setTextColor(textColor);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(backgroundColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画布的原点在坐上角（0，0）处
        float right = getWidth();
        float bottom = getHeight();

        canvas.drawRoundRect(0, 0, right, bottom, 5, 5, mPaint);
    }
}
