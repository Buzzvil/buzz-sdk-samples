package com.custom;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import android.graphics.Color;
import android.widget.ImageView;

import com.buzzvil.buzzscreen.sdk.BuzzScreen;
import com.buzzvil.buzzscreen.sdk.SecurityConfiguration;
import com.sample.custom.R;

public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // unitId : BuzzScreen 지면을 위한 ID로 버즈빌에서 발급 (기존 파트너는 AppKey와 UnitId가 동일함)
        // CustomLockerActivity.class : 잠금화면 액티비티 클래스
        // R.drawable.image_on_fail : 네트워크 에러 혹은 일시적으로 잠금화면에 보여줄 캠페인이 없을 경우 보여주게 되는 이미지.
        BuzzScreen.init("419318955785795", this, CustomLockerActivity.class, R.drawable.default_bg);

        BuzzScreen.getInstance().setSecurityConfiguration(
                new SecurityConfiguration.Builder()
                        .backgroundResourceId(R.drawable.image_stu_center)
                        .backgroundImageScaleType(ImageView.ScaleType.FIT_CENTER)
                        .backgroundColor(Color.WHITE)
                        .backgroundDimAlpha(0.7f)
                        .showClock(true)
                        .showDescription(true)
                        .build());
    }
}
