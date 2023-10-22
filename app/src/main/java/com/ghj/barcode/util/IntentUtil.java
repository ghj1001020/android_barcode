package com.ghj.barcode.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.ghj.barcode.BarcodeApp;
import com.ghj.barcode.activity.BaseActivity;

public class IntentUtil {

    // 설정 화면으로 이동
    public static Intent OpenPermissionSetting() {
        try {
            return new Intent( Settings.ACTION_APPLICATION_DETAILS_SETTINGS ).setData( Uri.parse("package:" + BarcodeApp.getContext().getPackageName()) );
        }
        catch( Exception e ) {
            return new Intent( Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS );
        }
    }

    // 갤러리선택 화면으로 이동
    public static Intent OpenImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return Intent.createChooser(intent, "이미지 선택");
    }
}
