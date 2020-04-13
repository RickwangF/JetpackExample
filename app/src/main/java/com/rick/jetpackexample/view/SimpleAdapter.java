package com.rick.jetpackexample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ItemSimpleOneBinding;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleItemViewHolder> {

    private List<String> mTitleList;

    private Context mContext;

    public SimpleAdapter(List<String> mTitleList, Context mContext) {
        this.mTitleList = mTitleList;
        this.mContext = mContext;
    }

    public List<String> getmTitleList() {
        return mTitleList;
    }

    public void setmTitleList(List<String> mTitleList) {
        this.mTitleList = mTitleList;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SimpleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSimpleOneBinding bind = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_simple_one, parent, false);
        return new SimpleItemViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleItemViewHolder holder, int position) {
        holder.bindView(this, position);
    }

    @Override
    public int getItemCount() {
        if (mTitleList == null || mTitleList.size() == 0) {
            return 0;
        } else {
            return mTitleList.size();
        }
    }
}
