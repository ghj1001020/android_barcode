package com.ghj.barcode.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ActivityMainBinding newBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
