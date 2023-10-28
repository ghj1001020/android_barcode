package com.ghj.barcode.activity.adapter;

public abstract class IItemListener<T> {
    public abstract void onItemClicked(int position, T data);
    public void onItemChecked(int position, T data){}
}
