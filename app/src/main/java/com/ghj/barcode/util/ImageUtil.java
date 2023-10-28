package com.ghj.barcode.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.ghj.barcode.BarcodeApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImageUtil {

    // 비트맵 이미지를 갤러리에 저장
    public static void SaveImageToGallery(Bitmap bitmap, String filename) {
        if(BarcodeApp.getContext() == null) return;

        ContentResolver contentResolver = BarcodeApp.getContext().getContentResolver();
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
        cv.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
        cv.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        cv.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        cv.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        cv.put(MediaStore.Images.Media.IS_PENDING, true);

        Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
        if(uri != null) {
            try (OutputStream os = contentResolver.openOutputStream(uri)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);

                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(uri);
                    BarcodeApp.getContext().sendBroadcast(intent);
                }
                else {
                    MediaScannerConnection.scanFile(BarcodeApp.getContext(), new String[]{uri.getPath()}, new String[]{"image/png"}, null);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                cv.put(MediaStore.Images.Media.IS_PENDING, false);
                contentResolver.update(uri, cv, null, null);
            }
        }
    }

    // Bitmap을 ContentResolver로 쓰기
    private static void WriteImageToStream(ContentResolver contentResolver, Bitmap bitmap, Uri uri) {
        try (OutputStream os = contentResolver.openOutputStream(uri)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
