package com.ghj.barcode.activity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ghj.barcode.data.HistoryData;
import com.ghj.barcode.databinding.ItemHistoryBinding;
import com.ghj.barcode.util.ClipboardUtil;

import java.util.List;

public class HistoryAdapter extends BaseRecyclerViewAdapter<HistoryData> {

    public HistoryAdapter(Context context, List<HistoryData> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(getContext()), parent, false));
    }

    class HistoryViewHolder extends BaseRecyclerViewHolder<ItemHistoryBinding> {

        public HistoryViewHolder(ItemHistoryBinding binding) {
            super(binding);
        }

        @Override
        public void onBindViewHolder(int position) {
            HistoryData data = getItem(position);
            mBinding.txtDate.setText(data.getDate());
            mBinding.txtValue.setText(data.getValue());
            mBinding.divider.setVisibility(getData().size() -1 == position ? View.GONE : View.VISIBLE);
            mBinding.btnCopy.setOnClickListener(v -> {
                if(TextUtils.isEmpty(data.getValue())) return;
                ClipboardUtil.CopyText(data.getValue());
            });
        }
    }
}
