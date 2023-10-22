package com.ghj.barcode.activity.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.FragmentImgscanBinding;
import com.ghj.barcode.util.IntentUtil;
import com.ghj.barcode.util.LogUtil;

public class ImgScanFragment extends BaseFragment<FragmentImgscanBinding> {
    @Override
    public FragmentImgscanBinding newBinding(ViewGroup container) {
        return FragmentImgscanBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {
        mBinding.setData(this);
        initLayout();
    }

    public void initLayout() {
        mBinding.image.setImageResource(R.drawable.no_image);
        mBinding.txtFilename.setText("파일명");
        mBinding.txtFilename.setTextColor(ContextCompat.getColor(getContext(), R.color.placeHolder));
        mBinding.txtMsg.setText("바코드 결과");
        mBinding.txtMsg.setTextColor(ContextCompat.getColor(getContext(), R.color.placeHolder));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPickImage) {
            initLayout();
            onRequestActivity(IntentUtil.OpenImagePicker());
        }
        else if(v.getId() == R.id.btnCopy) {

        }
    }

    @Override
    public void onRequestActivityResult(int requestCode, int resultCode, Intent data) {
        // 이미지선택
        if(data != null) {
            Uri uri = data.getData();
            LogUtil.d(uri.toString());
            mBinding.txtFilename.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
            mBinding.txtMsg.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
        }
    }
}
