package com.buzzvil.buzzscreen.sample_lock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.buzzvil.buzzscreen.migration.MigrationTo;
import com.buzzvil.buzzscreen.sdk.BuzzScreen;

/**
 * Created by patrick on 2017. 12. 8..
 */

public class IntroActivity extends AppCompatActivity {
    private static final String TAG = IntroActivity.class.getSimpleName();

    private static final int SUPPORTED_MAIN_APP_VERSION = 2;

    private Button btLogin;
    private LinearLayout llLogin;
    private EditText etUserId;
    private AlertDialog dialog;

    private App app;
    private MigrationTo migration;

    // Whether the user was using the lockscreen in the main app
    // Main 앱에서 기존에 잠금화면을 쓰고 있었는지 여부
    private boolean usingLockScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        app = (App) getApplicationContext();

        BuzzScreen.getInstance().launch();

        btLogin = (Button) findViewById(R.id.login_button);
        llLogin = findViewById(R.id.login_layout);
        etUserId = findViewById(R.id.login_user_id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (app.isLoggedIn()) {
            // If the user was already logged in, the user will be taken to the app's main screen.
            // 이미 로그인되어 있는 경우는 앱의 메인 화면으로 진입합니다.
            startMainActivity();
        } else {
            // If the user is not logged in, user can be logged in automatically through the migration, or user can request a login manually here.
            // Implementing automatic sign-in is recommended to minimize user attrition during migration.
            // 로그인되어있지 않은 경우는 마이그레이션을 통해 자동으로 로그인을 할 수도 있고, 수동으로 로그인을 요청할 수도 있습니다.
            // 마이그레이션시 유저이탈을 최소화하기 위해서는 자동로그인을 구현하는 것이 좋습니다.
            migration = new MigrationTo();
            migration.migrate(new MigrationTo.OnMigrationListener() {

                /**
                 * Migration is only done for the first time, and then onAlreadyMigrated () is called if migrate is called again.
                 * 마이그레이션은 최초에만 수행되고 그 이후 migrate 호출시에는 onAlreadyMigrated()가 호출됩니다.
                 */
                @Override
                public void onAlreadyMigrated() {
                    useManualLogin();
                }

                /**
                 * This is called after the app receives data from the M app and the UserProfile information is updated.
                 * @param data Data transferred through `onMigrationStarted` in the M app migration
                 * @param usingLockScreen Whether BuzzScreen has been activated in M app
                 *
                 * M앱에서 데이터를 전달받고 UserProfile 정보가 업데이트된 이후 호출됩니다.
                 * @param data M앱의 마이그레이션 연동에서 `onMigrationStarted` 를 통해 전달된 데이터
                 * @param usingLockScreen M앱에서 버즈스크린 활성화 여부
                 */
                @Override
                public void onDataMigrated(Bundle data, boolean usingLockScreen) {
                    Log.d(TAG, "OnMigrationListener.onDataMigrated");
                    IntroActivity.this.usingLockScreen = usingLockScreen;
                    if (data != null) {
                        // Performs an automatic login using user information from M app.
                        // If automatic login is successful, select whether to activate the lockscreen according to usingLockScreen.
                        // In case of automatic login failure, proceed with manual login (user action required).
                        // M앱으로부터 받은 유저 정보를 사용하여 자동 로그인을 수행합니다.
                        // 자동로그인 성공시에는 usingLockScreen 에 따라 잠금화면 활성화 여부를 선택합니다.
                        // 자동로그인 실패시에는 유저의 액션이 필요한 수동로그인을 진행합니다.
                        String userId = data.getString("user_id");
                        requestLogin(userId);
                    } else {
                        // There is no information received from M app, so proceed to manual login.
                        // M 앱으로부터 받은 정보가 없으므로 수동 로그인을 진행합니다.
                        useManualLogin();
                    }
                }

                @Override
                public void onError(MigrationTo.MigrationError migrationError) {
                    Log.e(TAG, "OnMigrationListener.onError : " + migrationError);
                    switch (migrationError) {
                        case MAIN_APP_NOT_INSTALLED:
                            // If M app is not installed, implement your own login logic to enable lockscreen.
                            // M앱이 설치되지않은 경우, 자체 로그인을 구현하여 잠금화면을 사용할 수 있도록 구현합니다.
                            Toast.makeText(IntroActivity.this, "Main app is not installed.\nPlease install it or login.", Toast.LENGTH_LONG).show();
                            useManualLogin();
                            break;
                        case MAIN_APP_MIGRATION_NOT_SUPPORTED:
                            // If the version of the M app is a version that does not support migration, the lock screen may be duplicated.
                            // In this case, prevent the use of the L app lockscreen and require the M app to be updated first.
                            // M앱 버전이 마이그레이션을 지원하지 않는 버전인 경우 잠금화면이 중복으로 뜰 수 있으므로 L앱 사용을 막고 M앱의 업데이트를 요구합니다.
                            alertMustUpdate();
                            break;
                        case UNKNOWN_ERROR:
                            // In case of a temporary error during the migration process, you can request a retry or go to manual login.
                            // 마이그레이션 과정시 일시적인 에러인 경우 재시도를 요구하거나 수동 로그인을 통해 진입가능합니다.
                            useManualLogin();
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void onPause() {
        if (migration != null) {
            // Call to stop the migration progress.
            // If you do not stop here, onDataMigrated or onAlreadyMigrated can be called in the paused state.
            // 마이그레이션 진행 중단을 위해 호출합니다.
            // 여기서 중단하지 않으면 paused상태에서 onDataMigrated or onAlreadyMigrated 가 호출될 수 있습니다.
            migration.abort();
        }
        super.onPause();
    }

    private void alertMustUpdate() {
        dialog = new AlertDialog.Builder(IntroActivity.this)
                .setMessage("Not supported main app version. Please update it to v" + SUPPORTED_MAIN_APP_VERSION)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setCancelable(false)
                .create();
        dialog.show();
    }

    private void useManualLogin() {
        llLogin.setVisibility(View.VISIBLE);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = etUserId.getText().toString();
                requestLogin(userId);
            }
        });
    }

    private void requestLogin(String userId) {
        boolean isSucceeded = app.login(userId);
        if (isSucceeded) {
            Toast.makeText(IntroActivity.this, "User " + userId + " login succeeded", Toast.LENGTH_LONG).show();
            onLoginSuccess();
        } else {
            Toast.makeText(IntroActivity.this, "Login failed", Toast.LENGTH_LONG).show();
            onLoginFail();
        }
    }

    private void onLoginSuccess() {
        if (usingLockScreen) {
            // A user who was using lockscreen before - activated immediately
            // 이전에 잠금화면을 쓰고 있던 유저 - 바로 activate
            Toast.makeText(IntroActivity.this, "Activated lockscreen according to the previous setting", Toast.LENGTH_LONG).show();
            // Validate the migrated Profile data
            // 옮겨진 Profile 에 대한 유효성 검사
            app.checkAndSetBuzzScreenProfile();
            BuzzScreen.getInstance().activate();
            startMainActivity();
        } else {
            // A user who was not using lockscreen - Let the user choose to activate
            // 이전에 잠금화면을 쓰고 있지 않던 유저 - 유저에게 activate 여부 선택하게 하기
            dialog = new AlertDialog.Builder(IntroActivity.this)
                    .setMessage("Would you like to activate the lockscreen?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Validation of migrated Profile
                            // 옮겨진 Profile 에 대한 유효성 검사
                            app.checkAndSetBuzzScreenProfile();
                            BuzzScreen.getInstance().activate();
                            startMainActivity();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startMainActivity();
                        }
                    })
                    .setCancelable(false)
                    .create();
            dialog.show();
        }
    }

    private void onLoginFail() {
        useManualLogin();
    }

    private void startMainActivity() {
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
