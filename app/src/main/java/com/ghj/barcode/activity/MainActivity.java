package com.ghj.barcode.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.ghj.barcode.R;
import com.ghj.barcode.activity.adapter.ViewPagerAdapter;
import com.ghj.barcode.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ViewPagerAdapter(this);
        mBinding.viewPager.setAdapter(mAdapter);

        new TabLayoutMediator(mBinding.tab, mBinding.viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("스캔");
                        tab.setIcon(R.drawable.ic_scan);
                        break;
                    case 1:
                        tab.setText("이미지 스캔");
                        tab.setIcon(R.drawable.ic_imgscan);
                        break;
                    case 2:
                        tab.setText("QR코드 생성");
                        tab.setIcon(R.drawable.ic_generator);
                        break;
                    case 3:
                        tab.setText("히스토리");
                        tab.setIcon(R.drawable.ic_history);
                        break;
                }
            }
        }).attach();
    }

    @Override
    public ActivityMainBinding newBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
