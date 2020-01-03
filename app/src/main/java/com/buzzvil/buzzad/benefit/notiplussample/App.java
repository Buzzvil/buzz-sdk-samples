package com.buzzvil.buzzad.benefit.notiplussample;

import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.presentation.notification.NotiPlusDialogConfig;

public class App extends MultiDexApplication {
    public static final String APP_ID = "APP_ID";
    public static final String UNIT_ID_FEED = "UNIT_ID_FEED";

    @Override
    public void onCreate() {
        super.onCreate();
        initBuzzAdBenefit();
    }

    private void initBuzzAdBenefit() {
        BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(APP_ID)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.FEMALE)
                .birthYear(1993)
                .build();
        BuzzAdBenefit.setUserProfile(userProfile);
    }

    public static NotiPlusDialogConfig getNotiPlusDialogConfig() {
        return new NotiPlusDialogConfig.Builder()
                .colorConfirm(R.color.colorAccent)
                .colorCancel(R.color.colorPrimary)
                .imageRegisterLogo(R.drawable.benefit_notiplus_dialog_image_logo)
                .imageUnregisterLogo(R.drawable.benefit_notiplus_dialog_image_logo)
                .build();
    }
}
