package com.buzzvil.buzzscreen.sample_main;

import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.buzzvil.buzzscreen.migration.MigrationFrom;
import com.buzzvil.buzzscreen.sdk.BuzzScreen;
import com.buzzvil.buzzscreen.sdk.SimpleLockerActivity;

/**
 * Created by patrick on 2017. 12. 6..
 */

public class App extends MultiDexApplication {

    public static final String LOCKSCREEN_APP_PACKAGE = "com.buzzvil.buzzscreen.sample_lock";

    @Override
    public void onCreate() {
        super.onCreate();

        // BuzzScreen initialization code
        // 기존 버즈스크린 초기화 코드.
        BuzzScreen.init("my_app_key", this, SimpleLockerActivity.class, R.drawable.image_on_fail);

        // Code for migration
        // 마이그레이션을 위한 코드
        MigrationFrom.init(this, LOCKSCREEN_APP_PACKAGE);
        MigrationFrom.bind(new MigrationFrom.OnMigrationListener() {
            @Override
            public void onAlreadyMigrated() {

            }

            @Override
            public Bundle onMigrationStarted() {
                // BuzzScreen UserProfile and lockscreen on/off information already set in M app are automatically sent to L app.
                // If you return any other necessary data, it will be passed to M app during the migration process.
                // Be sure to pass user authentication information to implement automatic login in L app without user's additional action.
                // In this sample, automated log in is implemented by assuming user_id as user authentication information.
                // M앱에서 이미 설정한 버즈스크린 UserProfile, 잠금화면 on/off 정보는 자동으로 L앱으로 전달됩니다.
                // 그 외의 필요한 정보를 리턴해주면 마이그레이션 과정에서 M앱으로 전달됩니다.
                // 반드시 유저 인증정보를 전달하여 L앱에서 유저의 추가 액션없이 자동 로그인이 되도록 구현바랍니다.
                // 여기서는 user_id 를 유저인증정보로 가정하고 전달하여 L앱에서 자동로그인을 구현하였습니다.
                if (isLoggedIn()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id", PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, ""));
                    return bundle;
                } else {
                    return null;
                }
            }

            @Override
            public void onMigrationCompleted() {
                // If you want to save the migration completion status
                // 마이그레이션 완료상태를 저장하고 싶은 경우 사용 예시
                PreferenceHelper.putBoolean(Constants.PREF_KEY_MIGRATION_COMPLETED, true);
            }
        });

        PreferenceHelper.init(this, "SamplePreference");
    }

    public boolean isLoggedIn() {
        return !TextUtils.isEmpty(PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, ""));
    }

    public void login(String userId) {
        PreferenceHelper.putString(Constants.PREF_KEY_USER_ID, userId);
    }

    public void logout() {
        PreferenceHelper.remove(Constants.PREF_KEY_USER_ID);
        BuzzScreen.getInstance().logout();
    }
}
