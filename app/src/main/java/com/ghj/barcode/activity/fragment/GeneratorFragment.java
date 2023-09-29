package com.ghj.barcode.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.FragmentGeneratorBinding;
import com.ghj.barcode.databinding.FragmentHistoryBinding;

public class GeneratorFragment extends BaseFragment<FragmentGeneratorBinding> {

    @Override
    public FragmentGeneratorBinding newBinding(ViewGroup container) {
        return FragmentGeneratorBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {

    }
}
