package com.ghj.barcode.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.ghj.barcode.BarcodeApp;

public class AlertUtil {

    // alert
    public static AlertDialog alert(String message, DialogInterface.OnClickListener listener) {
        return alert("", message, "", listener);
    }
    public static AlertDialog alert(String title, String message, String buttonText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BarcodeApp.getContext());
        if(!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setCancelable(false);

        String buttonText1 = "확인";
        if(!TextUtils.isEmpty(buttonText)) {
            buttonText1 = buttonText;
        }

        builder.setPositiveButton(buttonText1, (dialog, which) -> {
            dialog.dismiss();

            if(listener != null) {
                listener.onClick(dialog, which);
            }
        });
        return builder.show();
    }

    // confirm
    public static AlertDialog confirm(String message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        return confirm("", message, "확인", "취소", positiveListener, negativeListener);
    }
    public static AlertDialog confirm(String title, String message, String positiveText, String negativeText, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BarcodeApp.getContext());
        if(!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setCancelable(false);

        String buttonText1 = "확인";
        String buttonText2 = "취소";
        if(!TextUtils.isEmpty(positiveText)) {
            buttonText1 = positiveText;
        }
        if(!TextUtils.isEmpty(negativeText)) {
            buttonText2 = negativeText;
        }

        builder.setPositiveButton(buttonText1, (dialog, which) -> {
            dialog.dismiss();

            if(positiveListener != null) {
                positiveListener.onClick(dialog, which);
            }
        });
        builder.setNegativeButton(buttonText2, (dialog, which) -> {
            dialog.dismiss();

            if(negativeListener != null) {
                negativeListener.onClick(dialog, which);
            }
        });
        return builder.show();
    }
}
