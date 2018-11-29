package com.hanki.hanki;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.hanki.hanki.Tutorial.TutorialActivity;

import java.security.Permission;
import java.util.List;

public class PermissionActivity extends AppCompatActivity {

    PermissionListener permissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        initPermissionListener();

        Button permissionBtn = (Button) findViewById(R.id.permissionBtn);
        permissionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //전화걸기와 위치 권한 받기
                TedPermission.with(PermissionActivity.this)
                        .setPermissionListener(permissionListener)
                        .setDeniedMessage("If you reject permission, you can not user this service\n\nPleas turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION)
                        .check();
            }
        });
    }

    public void initPermissionListener() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        final int checkShowTutorial = pref.getInt("checkTutorial", 0);

        permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() { //권한을 다 허용하였을 경우

                // **** 화면전환 (수정필요) **** //
                Intent intent;
                if(checkShowTutorial == 0) {
                    intent = new Intent(PermissionActivity.this, TutorialActivity.class);
                } else {
                    intent = new Intent(PermissionActivity.this, HomeActivity.class);
                }
                // ******************* //

                startActivity(intent);
                finish();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(PermissionActivity.this, "선택권한이 거부되었습니다. 앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
    }
}