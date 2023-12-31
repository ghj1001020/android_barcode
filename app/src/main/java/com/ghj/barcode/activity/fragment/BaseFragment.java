package com.ghj.barcode.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseFragment<VB extends ViewDataBinding> extends Fragment implements View.OnClickListener {

    // 액티비티
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

    // 뷰바인딩
    public abstract VB newBinding(ViewGroup container);
    public abstract void initFragment(Bundle bundle);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = newBinding(container);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment(savedInstanceState);
    }

    // 액티비티 요청
    public void onRequestActivity(Intent intent) {
        onRequestActivity(intent, 0);
    }
    public void onRequestActivity(Intent intent, int requestCode) {
        mReqCodeActivity = requestCode;
        mActivityLauncher.launch(intent);
    }
    public void onRequestActivityResult(int requestCode, int resultCode, Intent data) {}

    // 권한
    public void onRequestPermissions(String[] permission, int requestCode) {
        if(permission == null || permission.length == 0) return;

        mReqCodePermission = requestCode;
        mPermissionLauncher.launch(permission);
    }
    public void onRequestPermissionsResult(int requestCode, List<String> deniedPermission) {}

    @Override
    public void onClick(View v) { }
}
