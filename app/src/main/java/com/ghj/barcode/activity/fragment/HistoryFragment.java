package com.ghj.barcode.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ghj.barcode.R;
import com.ghj.barcode.activity.adapter.HistoryAdapter;
import com.ghj.barcode.data.HistoryData;
import com.ghj.barcode.databinding.FragmentHistoryBinding;
import com.ghj.barcode.databinding.FragmentImgscanBinding;
import com.ghj.barcode.databinding.FragmentScanBinding;
import com.ghj.barcode.sqlite.YJSQLiteService;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {

    HistoryAdapter mAdapter;
    List<HistoryData> mData = new ArrayList<>();


    @Override
    public FragmentHistoryBinding newBinding(ViewGroup container) {
        return FragmentHistoryBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {
        mAdapter = new HistoryAdapter(getContext(), mData);
        mBinding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.list.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mData.clear();
        mData.addAll(YJSQLiteService.SelectHistory());
        mAdapter.notifyDataSetChanged();
    }
}
