package com.rick.jetpackexample.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rick.jetpackexample.databinding.ItemHorizontalGridBinding;

import java.util.List;

public class ItemHorizontalGridViewHolder extends RecyclerView.ViewHolder {

    ItemHorizontalGridBinding mBind;

    public ItemHorizontalGridViewHolder(ItemHorizontalGridBinding bind) {
        super(bind.getRoot());
        this.mBind = bind;
    }

    public void bindView(HorizontalAdapter adapter, int position) {
        List<SimpleModel> data = adapter.getData();
        if (data == null && data.size() == 0) {
            return;
        }

        SimpleModel model = data.get(position);
        mBind.setModel(model);
    }
}
