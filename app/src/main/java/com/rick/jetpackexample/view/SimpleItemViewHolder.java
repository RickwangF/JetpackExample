package com.rick.jetpackexample.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rick.jetpackexample.databinding.ItemSimpleOneBinding;

public class SimpleItemViewHolder extends RecyclerView.ViewHolder {

    ItemSimpleOneBinding mBind;

    public SimpleItemViewHolder(ItemSimpleOneBinding mBind) {
        super(mBind.getRoot());
        this.mBind = mBind;
    }

    public void bindView(SimpleAdapter adapter, int position) {
        String title = adapter.getmTitleList().get(position);
        mBind.tvTitle.setText(title);
    }
}
