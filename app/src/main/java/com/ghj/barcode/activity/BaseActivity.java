package com.ghj.barcode.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.ViewDataBinding;

import com.ghj.barcode.BarcodeApp;
import com.ghj.barcode.R;
import com.ghj.barcode.util.AlertUtil;
import com.ghj.barcode.util.AppUtil;
import com.ghj.barcode.util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseActivity<VB extends ViewDataBinding> extends AppCompatActivity {

    // 액티비티 요청
    private int mReqCodeActivity = 0;
    private ActivityResultLauncher<Intent> mActivityLauncher =  registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            onRequestActivityResult(mReqCodeActivity, o.getResultCode(), o.getData());
        }
    });

    // 권한
    private int mReqCodePermission = 0;
    private ActivityResultLauncher<String[]> mPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            if(result.size() == 0) return;

            List<String> deniedPermission = new ArrayList<>();
            for (String key : result.keySet()) {
                if(!result.get(key)) {
                    deniedPermission.add(key);
                }
            }
            onRequestPermissionsResult(mReqCodePermission, deniedPermission);
        }
    });

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
    }

    // 뷰바인딩
    public abstract VB newBinding();

    // 액티비티 요청 콜백
    public void onRequestActivity(Intent intent) {
        onRequestActivity(0, intent);
    }
    public void onRequestActivity(int requestCode, Intent intent) {
        mReqCodeActivity = requestCode;
        mActivityLauncher.launch(intent);
    }
    public void onRequestActivityResult(int requestCode, int resultCode, Intent data) {}

    // 권한 요청 콜백
    public void onRequestPermissions(String[] permission, int requestCode) {
        if(permission == null || permission.length == 0) return;

        mReqCodePermission = requestCode;
        mPermissionLauncher.launch(permission);
    }
    public void onRequestPermissionsResult(int requestCode, List<String> deniedPermission) {}
}
