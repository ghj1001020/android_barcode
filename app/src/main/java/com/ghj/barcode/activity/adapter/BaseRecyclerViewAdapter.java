package com.ghj.barcode.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private Context mContext;
    private List<T> mData;
    public IItemListener<T> Listener;

    public BaseRecyclerViewAdapter(Context context, List<T> data) {
        super();
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Context getContext() {
        return mContext;
    }

    public List<T> getData() {
        return mData;
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        holder.onBindViewHolder(position);
    }
}
