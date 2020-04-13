package com.rick.jetpackexample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rick.jetpackexample.R;

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;

    private Paint mPaint;

    private int mColor;

    private int mHorizontalDivider = 0;

    private int mVerticalDivider = 0;

    private Bitmap mIcon;

    public SimpleItemDecoration(Context context,int mColor) {
        this.mContext = context;
        this.mColor = mColor;
        this.initPaint();
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_cut_128_f60100);
    }

    public SimpleItemDecoration(Context context, int mColor, int mHorizontalDivider, int mVerticalDivider) {
        this.mContext = context;
        this.mColor = mColor;
        this.mHorizontalDivider = mHorizontalDivider;
        this.mVerticalDivider = mVerticalDivider;
        this.initPaint();
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_cut_128_f60100);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(0, 0, 0, 10);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = parent.getPaddingLeft() + layoutParams.getMarginStart();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int right = parent.getWidth() - parent.getPaddingRight();
            int bottom = top + mHorizontalDivider;

            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)child.getLayoutParams();
            int left = child.getLeft();
            int top = child.getTop();
            c.drawBitmap(mIcon, left, top, mPaint);
        }
    }
}
