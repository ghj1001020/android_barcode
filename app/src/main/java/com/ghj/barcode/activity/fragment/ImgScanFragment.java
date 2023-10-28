package com.ghj.barcode.activity.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.FragmentImgscanBinding;
import com.ghj.barcode.sqlite.YJSQLiteService;
import com.ghj.barcode.util.ClipboardUtil;
import com.ghj.barcode.util.FileUtil;
import com.ghj.barcode.util.IntentUtil;
import com.ghj.barcode.util.LogUtil;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.android.Intents;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ImgScanFragment extends BaseFragment<FragmentImgscanBinding> {
    String mBarcode = "";

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
        mBarcode = "";
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
            if(TextUtils.isEmpty(mBarcode)) return;
            ClipboardUtil.CopyText(mBarcode);
        }
    }

    @Override
    public void onRequestActivityResult(int requestCode, int resultCode, Intent data) {
        // 이미지선택
        if(data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                mBarcode = getQRCodeText(uri);
                mBinding.image.setImageURI(uri);
                mBinding.txtMsg.setText(mBarcode);

                YJSQLiteService.InsertHistory(mBarcode);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            mBinding.txtFilename.setText(FileUtil.GetFilenameFromUri(uri));
            mBinding.txtFilename.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
            mBinding.txtMsg.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
        }
    }

    String getQRCodeText(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            int[] intArray = new int[bitmap.getWidth() * bitmap.getHeight()];
            bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            LuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), intArray);

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new QRCodeReader();
            Result result = reader.decode(binaryBitmap);
            return result.getText();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
