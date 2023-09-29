package com.ghj.barcode.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.ComponentActivity;
import androidx.databinding.ViewDataBinding;

import com.ghj.barcode.BarcodeApp;
import com.ghj.barcode.R;
import com.ghj.barcode.common.Alert;
import com.ghj.barcode.common.Permission;
import com.ghj.barcode.util.AppUtil;
import com.ghj.barcode.util.IntentUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity<VB extends ViewDataBinding> extends AppCompatActivity {

    public VB mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarcodeApp.setContext(this);
        BarcodeApp.setActivity(this);

        mBinding = newBinding();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BarcodeApp.setContext(this);
        BarcodeApp.setActivity(this);

        // 인트로가 아니면 카메라 권한체크
        if(!(this instanceof IntroActivity)) {
            if(!Permission.HasAppNeedPermission()) {
                Alert.alert(getString(R.string.not_permission), (dialog, which) -> {
                    AppUtil.AppClose();
                });
            }
        }
    }

    // 뷰바인딩
    public abstract VB newBinding();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        List<String> deniedPermission = new ArrayList<>();
        boolean isRationale = false;
        for (int i=0; i<grantResults.length; i++) {
            if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermission.add(permissions[i]);
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                    isRationale = true;
                }
            }
        }

        onRequestPermissionsResult(requestCode, deniedPermission, isRationale);
    }

    // 권한결과 콜백
    public void onRequestPermissionsResult(int requestCode, List<String> deniedPermission, boolean isRationale) {}
}
