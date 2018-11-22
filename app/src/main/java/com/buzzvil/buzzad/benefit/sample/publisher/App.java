package com.buzzvil.buzzad.benefit.sample.publisher;

import android.app.Application;

import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.ui.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.ui.FeedConfig;

public class App extends Application {

    public static final String APP_ID = "YOUR_APP_ID";
    public static final String UNIT_ID_NATIVE_AD = "YOUR_NATIVE_AD_UNIT_ID";
    public static final String UNIT_ID_FEED = "YOUR_FEED_UNIT_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        BuzzAdBenefit.init(this, APP_ID);

        final FeedConfig feedConfig = new FeedConfig.Builder()
                .unitId(UNIT_ID_FEED)
                .title("BuzzAdBenefit Feed")
                .primaryColor("#1290FF")
                .build();
        BuzzAdBenefit.setFeedConfig(feedConfig);

        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build();

        BuzzAdBenefit.setUserProfile(userProfile);
    }
}
