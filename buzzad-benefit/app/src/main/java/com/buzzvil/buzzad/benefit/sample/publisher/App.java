package com.buzzvil.buzzad.benefit.sample.publisher;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;

public class App extends Application {
    public static final String UNIT_ID_NATIVE_AD = "100000043";
    public static final String UNIT_ID_FEED = "100000043";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FeedConfig feedConfig = new FeedConfig.Builder(this, UNIT_ID_FEED).build();
        PopConfig popConfig = new PopConfig.Builder(this, UNIT_ID_FEED).build();

        BuzzAdBenefit.init(this, new BuzzAdBenefitConfig.Builder(this).setFeedConfig(feedConfig).setPopConfig(popConfig).build());

        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build();

        BuzzAdBenefit.setUserProfile(userProfile);
    }
}
