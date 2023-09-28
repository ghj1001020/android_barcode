package com.ghj.barcode.common;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ghj.barcode.BarcodeApp;

public class Permission {
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

    // 권한요청
    public static void RequestPermission(String[] permission, int requestCode) {
        if(permission == null || permission.length == 0 || BarcodeApp.getActivity() == null || BarcodeApp.getActivity().isDestroyed()) {
            return;
        }

        ActivityCompat.requestPermissions(BarcodeApp.getActivity(), permission, requestCode);
    }
}
