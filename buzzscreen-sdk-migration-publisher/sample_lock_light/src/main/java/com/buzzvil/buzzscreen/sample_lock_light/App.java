package com.buzzvil.buzzscreen.sample_lock_light;

import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.buzzvil.buzzscreen.migration.MigrationClient;
import com.buzzvil.buzzscreen.sdk.BuzzScreen;
import com.buzzvil.buzzscreen.sdk.SimpleLockerActivity;

/**
 * Created by patrick on 2017. 12. 6..
 */

public class App extends MultiDexApplication {

    public static final String MAIN_APP_PACKAGE = "com.buzzvil.buzzscreen.sample_main_light";

    /**
     * Set a deeplink to use when moving the user to Main app because the conditions for turning on lockscreen are not met
     * If not, run Main app.
     * 잠금화면을 켜기 위한 조건이 충족되지 않아서 Main 앱으로 유저를 이동시켜야 할 때 사용할 딥링크 설정
     * 설정되지 않았을 경우 Main 앱을 실행한다.
     */
    public static final String DEEP_LINK_ONBOARDING = "";

    @Override
    public void onCreate() {
        super.onCreate();

        BuzzScreen.init("my_app_key", this, SimpleLockerActivity.class, R.drawable.image_on_fail);

        MigrationClient.init(this, App.MAIN_APP_PACKAGE);
        MigrationClient.setOnDeactivatedByMainAppListener(new MigrationClient.OnDeactivatedByMainAppListener() {
            @Override
            public void onDeactivated() {
                Toast.makeText(App.this, R.string.app_deactivated_by_m, Toast.LENGTH_LONG).show();
            }
        });
    }
}
