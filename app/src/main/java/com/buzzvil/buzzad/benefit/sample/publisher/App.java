package com.buzzvil.buzzad.benefit.sample.publisher;

import android.app.Application;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;

public class App extends Application {

    public static final String APP_ID = "YOUR_APP_ID";
    public static final String UNIT_ID_NATIVE_AD = "YOUR_NATIVE_AD_UNIT_ID";
    public static final String UNIT_ID_FEED = "YOUR_FEED_UNIT_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        BuzzAdBenefit.init(this, new BuzzAdBenefitConfig.Builder(APP_ID).build());

        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build();

        BuzzAdBenefit.setUserProfile(userProfile);
    }
}
