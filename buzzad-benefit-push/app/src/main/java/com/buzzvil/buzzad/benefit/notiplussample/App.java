package com.buzzvil.buzzad.benefit.notiplussample;

import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.presentation.notification.PushDialogConfig;
import com.buzzvil.buzzad.benefit.presentation.notification.RewardNotificationConfig;

public class App extends MultiDexApplication {
    // Caution: Replace `236027834764095` with Your Unit ID
    public static final String UNIT_ID_FEED = "236027834764095";
    public static RewardNotificationConfig rewardNotificationConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        initBuzzAdBenefit();
    }

    private void initBuzzAdBenefit() {
        BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        // Caution: Replace `SAMPLE_USER_ID` with User's ID
        // Caution: Replace `UserProfile.Gender.FEMALE` with User's gender
        // Caution: Replace `1993` with User's BirthYear
        UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.FEMALE)
                .birthYear(1993)
                .build();
        BuzzAdBenefit.setUserProfile(userProfile);
    }

    public static PushDialogConfig getPushDialogConfig() {
        return new PushDialogConfig.Builder()
                .colorConfirm(R.color.colorAccent)
                .colorCancel(R.color.colorPrimary)
//                .imageRegisterLogo(R.drawable.benefit_notiplus_dialog_image_logo)
//                .imageUnregisterLogo(R.drawable.benefit_notiplus_dialog_image_logo)
                .build();
    }

    public static RewardNotificationConfig getRewardNotificationConfig() {
        if (rewardNotificationConfig == null) {
            rewardNotificationConfig = new RewardNotificationConfig.Builder().build();
        }
        return rewardNotificationConfig;
    }
}
