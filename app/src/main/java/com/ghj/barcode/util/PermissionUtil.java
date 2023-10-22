package com.ghj.barcode.util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ghj.barcode.BarcodeApp;

import java.util.Map;

public class PermissionUtil {

    // 필수권한 : 카메라
    public static String[] APP_NEED_PERMISSION = new String[] { Manifest.permission.CAMERA };

    // 권한체크
    public static boolean HasAppNeedPermission() {
        return HasPermission(APP_NEED_PERMISSION);
    }
    public static boolean HasPermission(String[] permission) {
        if(BarcodeApp.getContext() == null || permission == null) return false;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        boolean result = true;
        for (String perm: permission) {
            if(ContextCompat.checkSelfPermission(BarcodeApp.getContext(), perm) != PackageManager.PERMISSION_GRANTED) {
                result = false;
            }
        }
        return result;
    }
}
