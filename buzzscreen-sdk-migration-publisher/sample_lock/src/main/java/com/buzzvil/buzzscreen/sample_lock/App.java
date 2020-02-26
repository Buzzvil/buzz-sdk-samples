package com.buzzvil.buzzscreen.sample_lock;

import android.app.NotificationManager;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.buzzvil.buzzscreen.migration.MigrationTo;
import com.buzzvil.buzzscreen.sdk.BuzzScreen;
import com.buzzvil.buzzscreen.sdk.UserProfile;

/**
 * Created by patrick on 2017. 12. 6..
 */

public class App extends MultiDexApplication {

    public static final String MAIN_APP_PACKAGE = "com.buzzvil.buzzscreen.sample_main";

    @Override
    public void onCreate() {
        super.onCreate();

        // BuzzScreen initialization code (same as the M app)
        // M앱과 동일한 기존 버즈스크린 초기화 코드.
        BuzzScreen.init("my_app_key", this, CustomLockerActivity.class, R.drawable.image_on_fail);

        // Code for migration
        // 마이그레이션을 위한 코드
        MigrationTo.init(this, App.MAIN_APP_PACKAGE);

        PreferenceHelper.init(this, "SamplePreference");

        BuzzScreen.getInstance().setOnPointListener(new BuzzScreen.OnPointListener() {
            @Override
            public void onSuccess(BuzzScreen.PointType type, int points) {
                if (isNotificationEnabled()) {
                    String message = String.format("%dP", points) + R.string.main_point_payment;
                    showNotification(message);
                }
            }

            @Override
            public void onFail(BuzzScreen.PointType type) {

            }
        });
    }

    public boolean isNotificationEnabled() {
        return PreferenceHelper.getBoolean(Constants.PREF_KEY_NOTIFICATION_ENABLE, true);
    }

    public void enableNotification(boolean enable) {
        PreferenceHelper.putBoolean(Constants.PREF_KEY_NOTIFICATION_ENABLE, enable);
    }

    public void showNotification(String string) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "SampleLockScreen")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("SampleLockScreen")
                        .setContentText(string);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(1000, builder.build());
    }

    public boolean isLoggedIn() {
        return !TextUtils.isEmpty(PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, ""));
    }

    public boolean login(String userId) {
        // Sign users into your service
        // 퍼블리셔 로그인
        if (TextUtils.isEmpty(userId)) {
            return false;
        } else {
            PreferenceHelper.putString(Constants.PREF_KEY_USER_ID, userId);
            return true;
        }
    }

    public void logout() {
        PreferenceHelper.remove(Constants.PREF_KEY_USER_ID);
        BuzzScreen.getInstance().logout();
    }

    public void checkAndSetBuzzScreenProfile() {
        UserProfile userProfile = BuzzScreen.getInstance().getUserProfile();
        if (TextUtils.isEmpty(userProfile.getUserId())) {
            String userId = PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, "");
            userProfile.setUserId(userId);
        }
        if (userProfile.getBirthYear() == 0
                || TextUtils.isEmpty(userProfile.getGender())
                || TextUtils.isEmpty(userProfile.getRegion())) {
            // Enter targeting data received from the user or the M app
            // 타게팅 정보를 유저로부터 입력받거나 M에서 전달한 정보 입력.
            int birthYear = 1985;
            String gender = UserProfile.USER_GENDER_MALE;
            userProfile.setBirthYear(birthYear);
            userProfile.setGender(gender);
        }
    }
}
