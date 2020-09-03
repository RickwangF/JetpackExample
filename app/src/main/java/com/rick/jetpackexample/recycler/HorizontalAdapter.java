package com.rick.jetpackexample.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rick.jetpackexample.databinding.ItemHorizontalGridBinding;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<ItemHorizontalGridViewHolder> {

    public Context context;

    private List<SimpleModel> data = new ArrayList<>();

    public HorizontalAdapter(Context context, List<SimpleModel> data) {
        this.context = context;
        this.data = data;
    }

    public List<SimpleModel> getData() {
        return data;
    }

    public void setData(List<SimpleModel> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHorizontalGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHorizontalGridBinding bind = ItemHorizontalGridBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ItemHorizontalGridViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHorizontalGridViewHolder holder, int position) {
        holder.bindView(this, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
