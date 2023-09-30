package com.ghj.barcode.activity.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ghj.barcode.databinding.DialogResultBinding;
import com.ghj.barcode.util.ClipboardUtil;

public class ResultDialog extends BaseDialog<DialogResultBinding> {

    private String mMessage;
    private IDialogListener mListener;

    public ResultDialog(String message, IDialogListener listener) {
        mMessage = message;
        mListener = listener;
    }

    @Override
    public DialogResultBinding newBinding(ViewGroup container) {
        return DialogResultBinding.inflate(LayoutInflater.from(getContext()), container, false);
    }

    @Override
    public void initDialog(Bundle bundle) {
        mBinding.txtTitle.setVisibility(View.GONE);
        mBinding.txtMsg.setText(mMessage);
        mBinding.btnCopy.setOnClickListener(view -> {
            String text = mBinding.txtMsg.getText().toString();
            ClipboardUtil.CopyText(text);
        });
        mBinding.btnOk.setOnClickListener(view -> {
            dismiss();
            if(mListener != null) {
                mListener.onDialogClicked(IDialogListener.DIALOG_BTN_OK);
            }
        });
    }
}
