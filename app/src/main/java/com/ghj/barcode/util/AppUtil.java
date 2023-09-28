package com.ghj.barcode.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.ghj.barcode.BarcodeApp;

public class AppUtil {

    // 앱종료
    public static void AppClose() {
        try {
            if(BarcodeApp.getActivity() != null) {
                BarcodeApp.getActivity().finishAffinity();
            }
            android.os.Process.killProcess( android.os.Process.myPid() );
        }
        catch( Exception e ) {}
    }
}
