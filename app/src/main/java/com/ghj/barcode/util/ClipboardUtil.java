package com.ghj.barcode.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import com.ghj.barcode.BarcodeApp;

public class ClipboardUtil {
    // 클립보드 복사
    public static void CopyText(String text) {
        if(BarcodeApp.getContext() == null) return;

        try {
            ClipboardManager cm = (ClipboardManager) BarcodeApp.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(BarcodeApp.getContext().getPackageName(), text);
            cm.setPrimaryClip(clipData);
            if(Build.VERSION_CODES.TIRAMISU > Build.VERSION.SDK_INT) {
                Toast.makeText(BarcodeApp.getContext(), "클립보드에 복사되었습니다", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
