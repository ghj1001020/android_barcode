package com.ghj.barcode.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.ghj.barcode.R;
import com.ghj.barcode.databinding.ActivityIntroBinding;
import com.ghj.barcode.define.Code;
import com.ghj.barcode.util.AlertUtil;
import com.ghj.barcode.util.AppUtil;
import com.ghj.barcode.util.IntentUtil;
import com.ghj.barcode.util.PermissionUtil;

import java.util.List;

public class IntroActivity extends BaseActivity<ActivityIntroBinding> {

    // 권한설정 화면 콜백
    ActivityResultLauncher<Intent> mPermissionCallBack =  registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == Activity.RESULT_OK) {
                if(PermissionUtil.HasAppNeedPermission()) {
                    moveToMain();
                }
                else {
                    AlertUtil.alert(getString(R.string.not_permission), (dialog, which) -> {
                        AppUtil.AppClose();
                    });
                }
            }
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PermissionUtil.HasAppNeedPermission()) {
            moveToMain();
        }
        else {
            PermissionUtil.RequestPermission(PermissionUtil.APP_NEED_PERMISSION, Code.PERMISSION.REQ_APP_NEED);
        }
    }

    @Override
    public ActivityIntroBinding newBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_intro);
    }

    public void moveToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, List<String> deniedPermission, boolean isRationale) {
        if(requestCode == Code.PERMISSION.REQ_APP_NEED) {
            if(deniedPermission.size() == 0) {
                moveToMain();
            }
            else {
                if(isRationale) {
                    Intent it = IntentUtil.OpenPermissionSetting();
                    mPermissionCallBack.launch(it);
                }
                else {
                    AlertUtil.alert(getString(R.string.not_permission), (dialog, which) -> {
                        AppUtil.AppClose();
                    });
                }
            }
        }
    }
}
