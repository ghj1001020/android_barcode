package com.ghj.barcode.activity.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ghj.barcode.activity.fragment.BaseFragment;
import com.ghj.barcode.activity.fragment.GeneratorFragment;
import com.ghj.barcode.activity.fragment.HistoryFragment;
import com.ghj.barcode.activity.fragment.ImgScanFragment;
import com.ghj.barcode.activity.fragment.ScanFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<BaseFragment> mFragments = new ArrayList<>();


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        initLayout();
    }

    public void initLayout() {
        mFragments.add(new ScanFragment());
        mFragments.add(new ImgScanFragment());
        mFragments.add(new GeneratorFragment());
        mFragments.add(new HistoryFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
