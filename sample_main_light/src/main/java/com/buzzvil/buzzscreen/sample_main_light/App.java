package com.buzzvil.buzzscreen.sample_main_light;

import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.buzzvil.buzzscreen.migration.MigrationHost;
import com.buzzvil.buzzscreen.sdk.BuzzScreen;
import com.buzzvil.buzzscreen.sdk.SimpleLockerActivity;

/**
 * Created by patrick on 2017. 12. 6..
 */

public class App extends MultiDexApplication {

    public static final String LOCKSCREEN_APP_PACKAGE = "com.buzzvil.buzzscreen.sample_lock_light";

    @Override
    public void onCreate() {
        super.onCreate();

        BuzzScreen.init("my_app_key", this, SimpleLockerActivity.class, R.drawable.image_on_fail);

        MigrationHost.init(this, LOCKSCREEN_APP_PACKAGE);
        MigrationHost.setOnDeactivatedByLockScreenAppListener(new MigrationHost.OnDeactivatedByLockScreenAppListener() {
            @Override
            public void onDeactivated() {
                Log.i("MainApp", "LockScreen is deactivated by Main App");
            }
        });

        PreferenceHelper.init(this, "SamplePreference");
    }

    public boolean isLoggedIn() {
        return !TextUtils.isEmpty(PreferenceHelper.getString(PrefKeys.PREF_KEY_USER_ID, ""));
    }

    public void login(String userId) {
        PreferenceHelper.putString(PrefKeys.PREF_KEY_USER_ID, userId);
    }

    public void logout() {
        PreferenceHelper.remove(PrefKeys.PREF_KEY_USER_ID);
        PreferenceHelper.remove(PrefKeys.PREF_KEY_TERM_AGREE);
        BuzzScreen.getInstance().logout();
        MigrationHost.requestDeactivation();
    }

    public boolean isTermAgree() {
        return PreferenceHelper.getBoolean(PrefKeys.PREF_KEY_TERM_AGREE, false);
    }

    public void setTermAgree(boolean agree) {
        PreferenceHelper.putBoolean(PrefKeys.PREF_KEY_TERM_AGREE, agree);
    }
}
