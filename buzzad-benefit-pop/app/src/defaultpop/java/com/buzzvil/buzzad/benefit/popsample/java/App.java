package com.buzzvil.buzzad.benefit.popsample.java;

import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.pop.toolbar.DefaultPopToolbarHolder;
import com.buzzvil.buzzad.benefit.popsample.R;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomControlService;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomPopToolbarHolder;

public class App extends MultiDexApplication {
    // Caution: Replace `236027834764095` with Your Unit ID
    public static final String UNIT_ID_POP = "236027834764095";

    @Override
    public void onCreate() {
        super.onCreate();

        initBuzzAdBenefit();
//        initBuzzAdBenefitWithCustomPopHeaderViewAdapter(); // User this for custom pop header view adapter
//        initBuzzAdBenefitWithCustomControlService(); // Use this for custom pop notification
    }

    private void initBuzzAdBenefit() {
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(getApplicationContext())
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimary)
                .notificationId(1021)
                .build();
        final PopConfig popConfig = new PopConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                // DefaultPopToolbarHolder: DefaultToolbar
                // TemplatePopToolbarHolder: Minimum customize, pop feed icon, name, button
                // CustomPopToolbarHolder: Use layout for toolbar
                .feedToolbarHolderClass(CustomPopToolbarHolder.class)
                .popNotificationConfig(popNotificationConfig)
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .setPopConfig(popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        // Caution: Replace `SAMPLE_USER_ID` with User's ID
        // Caution: Replace `UserProfile.Gender.FEMALE` with User's gender
        // Caution: Replace `1993` with User's BirthYear
        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.FEMALE)
                .birthYear(1993)
                .build();
        BuzzAdBenefit.setUserProfile(userProfile);
    }
    /**
     * [CUSTOM_POP_HEADER_VIEW_ADAPTER] Set custom CustomPopHeaderViewAdapter
     */
    private void initBuzzAdBenefitWithCustomPopHeaderViewAdapter() {
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(getApplicationContext())
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimary)
                .notificationId(1021)
                .build();
        final PopConfig popConfig = new PopConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                .feedToolbarHolderClass(DefaultPopToolbarHolder.class)
                .popNotificationConfig(popNotificationConfig)
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .setPopConfig(popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        // Caution: Replace `SAMPLE_USER_ID` with User's ID
        // Caution: Replace `UserProfile.Gender.FEMALE` with User's gender
        // Caution: Replace `1993` with User's BirthYear
        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.FEMALE)
                .birthYear(1993)
                .build();
        BuzzAdBenefit.setUserProfile(userProfile);
    }

    /**
     * [CUSTOM_POP_CONTROL_SERVICE] Set custom PopControlService
     */
    private void initBuzzAdBenefitWithCustomControlService() {
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(getApplicationContext())
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .notificationId(1021)
                .build();

        final PopConfig popConfig = new PopConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                .controlService(CustomControlService.class)
                .popNotificationConfig(popNotificationConfig)
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .setPopConfig(popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        // Caution: Replace `SAMPLE_USER_ID` with User's ID
        // Caution: Replace `UserProfile.Gender.FEMALE` with User's gender
        // Caution: Replace `1993` with User's BirthYear
        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.FEMALE)
                .birthYear(1993)
                .build();
        BuzzAdBenefit.setUserProfile(userProfile);
    }
}
