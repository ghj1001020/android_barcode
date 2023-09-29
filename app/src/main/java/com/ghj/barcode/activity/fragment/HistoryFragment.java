package com.ghj.barcode.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.FragmentHistoryBinding;
import com.ghj.barcode.databinding.FragmentImgscanBinding;
import com.ghj.barcode.databinding.FragmentScanBinding;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {
    @Override
    public FragmentHistoryBinding newBinding(ViewGroup container) {
        return FragmentHistoryBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {

    }
}
