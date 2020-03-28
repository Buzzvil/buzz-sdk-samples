package com.buzzvil.buzzad.unity.sample;

import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.pop.SidePosition;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;

public class App extends MultiDexApplication {
    static final String TAG = "App";
    private static final String UNIT_ID = "444437953344574";

    @Override
    public void onCreate() {
        super.onCreate();
        initBuzzAdBenefit();
        Log.d(TAG, "App:onCreate()");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        Log.d(TAG, "App:attachBaseContext()");
    }

    private void initBuzzAdBenefit() {
        final FeedConfig popFeedConfig = new FeedConfig.Builder(this, UNIT_ID)
                .articlesEnabled(true)
                .articleInAppLandingEnabled(true)
                .build();
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(this)
                .smallIconResId(getApplicationInfo().icon)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimaryBenefit)
                .notificationId(1000)
                .build();
        final PopConfig popConfig = new PopConfig.Builder(this, UNIT_ID)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .feedConfig(popFeedConfig)
                .popNotificationConfig(popNotificationConfig)
                .iconResId(R.drawable.sample_default_pop_icon)
                .rewardReadyIconResId(R.drawable.sample_pop_reward_ready_icon_selector)
                .build();
        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .add(UNIT_ID, popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.FEMALE)
                .birthYear(1993)
                .build();
        BuzzAdBenefit.setUserProfile(userProfile);
        Log.d(TAG, "App:initBuzzAdBenefit()");
    }
}
