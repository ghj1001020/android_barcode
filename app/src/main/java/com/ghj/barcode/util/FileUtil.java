package com.ghj.barcode.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import androidx.customview.widget.Openable;

import com.ghj.barcode.BarcodeApp;

import java.io.File;

public class FileUtil {

    // uri로 filename 구하기
    @SuppressLint("Range")
    public static String GetFilenameFromUri(Uri uri) {
        if(uri == null) return "";

        if("file".equalsIgnoreCase(uri.getScheme())) {
            File file = new File(uri.getPath());
            return file.getName();
        }
        else {
            Cursor cursor = null;
            try {
                ContentResolver cr = BarcodeApp.getContext().getContentResolver();
                cursor = cr.query(uri, null, null, null, null);
                String name = "";
                if(cursor != null && cursor.moveToFirst()) {
                    name = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));
                }
                return name;
            }
            catch (Exception e) {
                LogUtil.e(e.getMessage());
                return "";
            }
            finally {
                if(cursor != null) {
                    cursor.close();
                }
            }
        }
    }
}
