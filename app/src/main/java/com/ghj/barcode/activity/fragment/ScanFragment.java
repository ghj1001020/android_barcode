package com.ghj.barcode.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ghj.barcode.R;
import com.ghj.barcode.common.Alert;
import com.ghj.barcode.common.Permission;
import com.ghj.barcode.databinding.FragmentScanBinding;
import com.ghj.barcode.util.AppUtil;
import com.journeyapps.barcodescanner.CaptureManager;

public class ScanFragment extends BaseFragment<FragmentScanBinding> {

    CaptureManager mCaptureManager;

    @Override
    public FragmentScanBinding newBinding(ViewGroup container) {
        return FragmentScanBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {
        mCaptureManager = new CaptureManager(getActivity(), mBinding.scanner);
        mCaptureManager.decode();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Permission.HasAppNeedPermission()) {
            if(mCaptureManager != null) {
                mCaptureManager.onResume();
            }
        }
        else {
            Alert.alert(getString(R.string.not_permission), (dialog, which) -> {
                AppUtil.AppClose();
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mCaptureManager != null) {
            mCaptureManager.onPause();
        }
    }
}
