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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PermissionUtil.HasAppNeedPermission()) {
            moveToMain();
        }
        else {
            onRequestPermissions(PermissionUtil.APP_NEED_PERMISSION, Code.PERMISSION.REQ_APP_NEED);
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

    // 액티비티 콜백
    @Override
    public void onRequestActivityResult(int requestCode, int resultCode, Intent data) {
        if(PermissionUtil.HasAppNeedPermission()) {
            moveToMain();
        }
        else {
            AlertUtil.alert(getString(R.string.not_permission), (dialog, which) -> {
                AppUtil.AppClose();
            });
        }
    }

    // 권한 콜백
    @Override
    public void onRequestPermissionsResult(int requestCode, List<String> deniedPermission) {
        if(requestCode == Code.PERMISSION.REQ_APP_NEED) {
            if(deniedPermission.size() == 0) {
                moveToMain();
            }
            else {
                AlertUtil.confirm(getString(R.string.not_permission_setting), (dialog, which) -> {
                    Intent it = IntentUtil.OpenPermissionSetting();
                    onRequestActivity(it);
                }, ((dialog, which) -> {
                    AppUtil.AppClose();
                }));
            }
        }
    }
}
