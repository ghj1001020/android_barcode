package com.ghj.barcode.activity.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewHolder<VB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public VB mBinding;

    public BaseRecyclerViewHolder(VB binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public abstract void onBindViewHolder(int position);
}
