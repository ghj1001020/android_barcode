package com.ghj.barcode.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ghj.barcode.activity.dialog.ResultDialog;
import com.ghj.barcode.databinding.FragmentScanBinding;
import com.ghj.barcode.util.LogUtil;
import com.ghj.barcode.util.PermissionUtil;
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
        initScan();
    }

    public void initScan() {
        mBinding.scanner.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                mBinding.scanner.pause();
                LogUtil.d("결과 " + result.toString());
                ResultDialog dialog = new ResultDialog(result.toString(), btnType -> {
                    initScan();
                    mBinding.scanner.resume();
                });
                dialog.show(getActivity().getSupportFragmentManager(), "SCAN");
            }
        });
        mCaptureManager = new CaptureManager(getActivity(), mBinding.scanner);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("onResume");
        if(PermissionUtil.HasAppNeedPermission()) {
            if(mCaptureManager != null) {
                mCaptureManager.onResume();
            }
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
