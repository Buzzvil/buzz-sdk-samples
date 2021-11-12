package com.buzzvil.buzzad.benefit.sample.publisher;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomAdsAdapter;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomFeedHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomFeedToolbarHolder;

public class App extends Application {
    public static final String UNIT_ID_NATIVE_AD = "YOUR_NATIVE_AD_UNIT_ID";
    public static final String UNIT_ID_FEED = "YOUR_FEED_UNIT_ID";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final FeedConfig feedConfig = new FeedConfig.Builder(getApplicationContext(), App.UNIT_ID_FEED)
                .adsAdapterClass(CustomAdsAdapter.class)
                .feedToolbarHolderClass(CustomFeedToolbarHolder.class)
                .feedHeaderViewAdapterClass(CustomFeedHeaderViewAdapter.class)
                .imageTypeEnabled(true)
                .build();

        BuzzAdBenefit.init(this, new BuzzAdBenefitConfig.Builder(this).add(feedConfig).build());

        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build();

        BuzzAdBenefit.setUserProfile(userProfile);
    }
}
