package com.buzzvil.buzzad.benefit.popsample.java;

import android.app.Application;
import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.pop.SidePosition;
import com.buzzvil.buzzad.benefit.popsample.R;

public class App extends Application {

    public static final String APP_ID = "260318561407891";
    public static final String UNIT_ID_POP = "236027834764095";

    @Override
    public void onCreate() {
        super.onCreate();

        initBuzzAdBenefit();
        // initBuzzAdBenefitWithCustomControlService(); // Use this for custom pop notification
    }

    private void initBuzzAdBenefit() {
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(R.drawable.ic_notification_pop_gift)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimary)
                .notificationId(1021)
                .build();

        final PopConfig popConfig = new PopConfig.Builder(UNIT_ID_POP)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .initialPopIdleMode(PopConfig.PopIdleMode.INVISIBLE)
                .popNotificationConfig(popNotificationConfig)
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(APP_ID)
                .add(UNIT_ID_POP, popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

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
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(R.drawable.ic_notification_pop_gift)
                .notificationId(1021)
                .build();

        final PopConfig popConfig = new PopConfig.Builder(UNIT_ID_POP)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .initialPopIdleMode(PopConfig.PopIdleMode.INVISIBLE)
                .controlService(CustomControlService.class)
                .popNotificationConfig(popNotificationConfig)
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(APP_ID)
                .add(UNIT_ID_POP, popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.FEMALE)
                .birthYear(1993)
                .build();
        BuzzAdBenefit.setUserProfile(userProfile);
    }
}
