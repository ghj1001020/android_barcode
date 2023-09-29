package com.ghj.barcode.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.FragmentImgscanBinding;
import com.ghj.barcode.databinding.FragmentScanBinding;

public class ImgScanFragment extends BaseFragment<FragmentImgscanBinding> {
    @Override
    public FragmentImgscanBinding newBinding(ViewGroup container) {
        return FragmentImgscanBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {

    }
}
