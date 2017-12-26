package com.buzzvil.buzzscreen.sample_main_light;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.buzzvil.buzzscreen.migration.MigrationHost;
import com.buzzvil.buzzscreen.sdk.BuzzScreen;
import com.buzzvil.buzzscreen.sdk.UserProfile;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ToggleButton btLogin;
    private View layoutProfile;
    private Button btRequsetLockscreenAppActivation;
    private TextView tvVersion;
    private Button btDisableLockscreen;

    private App app;

    private int birthYear = 1985;
    private String gender = UserProfile.USER_GENDER_MALE;
    private String region = "서울특별시 관악구";

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (App) getApplicationContext();

        BuzzScreen.getInstance().launch();

        btLogin = (ToggleButton) findViewById(R.id.login_toggle);
        layoutProfile = findViewById(R.id.profile_layout);
        btRequsetLockscreenAppActivation = (Button) findViewById(R.id.lockscreen_app_activation_request);
        tvVersion = (TextView) findViewById(R.id.version);
        btDisableLockscreen = (Button) findViewById(R.id.lockscreen_disable);

        btLogin.setChecked(app.isLoggedIn());
        btLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    String userId = "testuserid" + new Random().nextInt(100000);
                    app.login(userId);
                    UserProfile userProfile = BuzzScreen.getInstance().getUserProfile();
                    // 포인트 적립을 위해서는 setUserId를 반드시 호출해야 함
                    userProfile.setUserId(userId);
                    // 캠페인 할당을 위한 타게팅 정보
                    userProfile.setBirthYear(birthYear);
                    userProfile.setGender(gender);
                    userProfile.setRegion(region);
                } else {
                    app.logout();
                }
                updateStatusUi();
            }
        });

        btRequsetLockscreenAppActivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app.isTermAgree()) {
                    // 기존에 BuzzScreen.getInstance().activate() 호출하던 위치
                    MigrationHost.requestActivationWithLaunch();
                } else {
                    dialog = new AlertDialog.Builder(MainActivity.this)
                            .setMessage("잠금화면 이용약관 동의")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    app.setTermAgree(true);
                                    // 기존에 BuzzScreen.getInstance().activate() 호출하던 위치
                                    MigrationHost.requestActivationWithLaunch();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .create();
                    dialog.show();
                }
            }
        });

        btDisableLockscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuzzScreen.getInstance().isActivated()) {
                    BuzzScreen.getInstance().deactivate();
                    btDisableLockscreen.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "잠금화면이 비활성화되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvVersion.setText("Version : " + BuildConfig.VERSION_NAME);
        updateStatusUi();
    }

    private void updateStatusUi() {
        if (app.isLoggedIn()) {
            layoutProfile.setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.profile_user_id)).setText("ID : " + PreferenceHelper.getString(PrefKeys.PREF_KEY_USER_ID, ""));
            ((TextView)findViewById(R.id.profile_birth_year)).setText("Birth year : " + birthYear);
            ((TextView)findViewById(R.id.profile_gender)).setText("Gender : " + gender);
            ((TextView)findViewById(R.id.profile_region)).setText("Region : " + region);
            btRequsetLockscreenAppActivation.setEnabled(true);
        } else {
            layoutProfile.setVisibility(View.GONE);
            btRequsetLockscreenAppActivation.setEnabled(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int visibility = BuzzScreen.getInstance().isActivated() ? View.VISIBLE : View.GONE;
        btDisableLockscreen.setVisibility(visibility);
    }
}
