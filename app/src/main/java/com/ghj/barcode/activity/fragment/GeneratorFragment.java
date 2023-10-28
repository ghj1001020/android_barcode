package com.ghj.barcode.activity.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.FragmentGeneratorBinding;
import com.ghj.barcode.databinding.FragmentHistoryBinding;
import com.ghj.barcode.util.DateUtil;
import com.ghj.barcode.util.ImageUtil;
import com.ghj.barcode.util.LogUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GeneratorFragment extends BaseFragment<FragmentGeneratorBinding> {

    Bitmap mImage;

    @Override
    public FragmentGeneratorBinding newBinding(ViewGroup container) {
        return FragmentGeneratorBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initFragment(Bundle bundle) {
        mBinding.setData(this);
        mBinding.editInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    onGenerateQRCode();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPickImage) {
            onGenerateQRCode();
        }
        else if(v.getId() == R.id.btnSave) {
            onSaveQRCode();
        }
    }

    public void onGenerateQRCode() {
        String input = mBinding.editInput.getText().toString();
        if(TextUtils.isEmpty(input)) return;

        mImage = null;
        try {
            MultiFormatWriter writer = new MultiFormatWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
            BitMatrix bitMatrix = writer.encode(input, BarcodeFormat.QR_CODE, 500, 500, hints);
            mImage = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            for(int i=0; i<500; i++) {
                for(int j=0; j<500; j++) {
                    mImage.setPixel(i, j, bitMatrix.get(i, j)? Color.BLACK : Color.WHITE);
                }
            }
        }
        catch (WriterException e) {
            e.printStackTrace();
        }

        if(mImage != null) {
            mBinding.image.setImageBitmap(mImage);
        }
        else {
            mBinding.image.setImageResource(R.drawable.no_image);
        }
    }

    public void onSaveQRCode() {
        if(mImage == null) return;

        String filename = getString(R.string.app_name) + ".png";
        ImageUtil.SaveImageToGallery(mImage, filename);
    }
}
