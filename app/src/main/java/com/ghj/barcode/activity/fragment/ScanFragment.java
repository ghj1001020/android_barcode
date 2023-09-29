package com.ghj.barcode.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.FragmentScanBinding;
import com.ghj.barcode.util.AlertUtil;
import com.ghj.barcode.util.AppUtil;
import com.ghj.barcode.util.LogUtil;
import com.ghj.barcode.util.PermissionUtil;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;

public class ScanFragment extends BaseFragment<FragmentScanBinding> {

    CaptureManager mCaptureManager;


    @Override
    public FragmentScanBinding newBinding(ViewGroup container) {
        return FragmentScanBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {
        mBinding.scanner.setStatusText("");
        mBinding.scanner.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                mBinding.scanner.pause();
                LogUtil.d("결과 " + result.toString());
            }
        });
        mCaptureManager = new CaptureManager(getActivity(), mBinding.scanner);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(PermissionUtil.HasAppNeedPermission()) {
            if(mCaptureManager != null) {
                mCaptureManager.onResume();
            }
        }
        else {
            AlertUtil.alert(getString(R.string.not_permission), (dialog, which) -> {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("AAAA", "ADFADF");
    }
}
