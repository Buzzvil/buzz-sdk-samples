package com.buzzvil.buzzscreen.sample_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.buzzvil.buzzscreen.sdk.BuzzScreen;
import com.buzzvil.buzzscreen.sdk.UserProfile;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ToggleButton buttonLogin;
    private SwitchCompat switchEnableLock;
    private TextView tvUserId;
    private TextView tvMigrationStatus;
    private TextView tvVersion;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (App) getApplicationContext();

        BuzzScreen.getInstance().launch();

        buttonLogin = (ToggleButton) findViewById(R.id.login_toggle);
        switchEnableLock = (SwitchCompat) findViewById(R.id.main_lockscreen_enable);
        tvUserId = (TextView) findViewById(R.id.user_id);
        tvMigrationStatus = (TextView) findViewById(R.id.migration_status);
        tvVersion = (TextView) findViewById(R.id.version);

        checkLoginAndUpdateUi();
        buttonLogin.setChecked(app.isLoggedIn());
        buttonLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    String userId = "testuserid" + new Random().nextInt(100000);
                    app.login(userId);
                } else {
                    app.logout();
                    switchEnableLock.setChecked(false);
                }
                checkLoginAndUpdateUi();
            }
        });

        switchEnableLock.setChecked(BuzzScreen.getInstance().isActivated());
        switchEnableLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    String userId = PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, "");
                    // Information delivered by a user at the moment of activation
                    // 활성화 순간 유저가 전달한 정보
                    int birthYear = 1985;
                    String gender = UserProfile.USER_GENDER_MALE;
                    UserProfile userProfile = BuzzScreen.getInstance().getUserProfile();
                    // SetUserId must be called to give users rewards through s2s postback or batch process.
                    // 포인트 적립을 위해서는 setUserId를 반드시 호출해야 함
                    userProfile.setUserId(userId);
                    // Targeting information for campaign allocation
                    // 캠페인 할당을 위한 타게팅 정보
                    userProfile.setBirthYear(birthYear);
                    userProfile.setGender(gender);
                    // Activate BuzzScreen
                    // 버즈스크린 활성화
                    BuzzScreen.getInstance().activate();
                } else {
                    BuzzScreen.getInstance().deactivate();
                }
            }
        });
        tvVersion.setText("Version : " + BuildConfig.VERSION_NAME);
    }

    private void checkLoginAndUpdateUi() {
        if (app.isLoggedIn()) {
            tvUserId.setVisibility(View.VISIBLE);
            tvUserId.setText(PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, ""));
            switchEnableLock.setEnabled(true);
        } else {
            tvUserId.setVisibility(View.GONE);
            switchEnableLock.setEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PreferenceHelper.getBoolean(Constants.PREF_KEY_MIGRATION_COMPLETED, false)) {
            tvMigrationStatus.setVisibility(View.VISIBLE);
            tvMigrationStatus.setText("Migration completed.\nPlease use Lockscreen App instead.");
            switchEnableLock.setEnabled(false);
            switchEnableLock.setChecked(false);
        } else {
            tvMigrationStatus.setVisibility(View.GONE);
        }
    }
}
