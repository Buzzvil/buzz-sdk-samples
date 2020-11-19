package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.pop.DefaultPedometerPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.pop.PopHeaderConfig;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.pop.pedometer.BuzzAdPopPedometer;
import com.buzzvil.buzzad.benefit.pop.pedometer.PedometerConfig;
import com.buzzvil.buzzad.benefit.pop.SidePosition;
import com.buzzvil.buzzad.benefit.pop.toolbar.DefaultPopToolbarHolder;
import com.buzzvil.buzzad.benefit.popsample.R;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomControlService;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;

import org.jetbrains.annotations.NotNull;

public class App extends MultiDexApplication {
    public static final String TAG = "PopSampleApp";
    public static final String UNIT_ID_POP = "236027834764095";
    public static final String APP_ID = "260318561407891";
    public static final String UNIT_ID_PEDOMETER = "239470121994416";
    public static final String UNIT_ID_PEDOMETER_REWARD = "406229237968648";

    private PopConfig popConfig;

    @Override
    public void onCreate() {
        Log.d(TAG, "BuzzAdPopPedometer onCreate");
        super.onCreate();

        initBuzzAdBenefit();
//        initBuzzAdBenefitWithCustomPopHeaderViewAdapter(); // User this for custom pop header view adapter
//        initBuzzAdBenefitWithCustomControlService(); // Use this for custom pop notification
    }

    private void initBuzzAdBenefit() {
        final FeedConfig feedConfig = new FeedConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                // DefaultPopToolbarHolder: DefaultToolbar
                // TemplatePopToolbarHolder: Minimum customize, pop feed icon, name, button
                // CustomPopToolbarHolder: Use layout for toolbar
                .feedToolbarHolderClass(DefaultPopToolbarHolder.class)
                .feedHeaderViewAdapterClass(DefaultPedometerPopHeaderViewAdapter.class)
                .articlesEnabled(true)
                .articleInAppLandingEnabled(true)
                .build();
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(getApplicationContext())
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimary)
                .notificationId(1021)
                .build();
        final com.buzzvil.buzzad.benefit.pop.pedometer.PedometerConfig pedometerConfig = buildPedometerConfig();
        final PopHeaderConfig popHeaderConfig = buildPedometerPopHeaderConfig();
        popConfig = new PopConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                .popMode(PopConfig.PopMode.PEDOMETER_POP)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .initialPopIdleMode(PopConfig.PopIdleMode.INVISIBLE)
                .feedConfig(feedConfig)
                .pedometerConfig(pedometerConfig)
                .popHeaderConfig(popHeaderConfig)
                .popNotificationConfig(popNotificationConfig)
                .previewIntervalInMillis(1000) // for test
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .add(popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        BuzzAdBenefit.registerSessionReadyBroadcastReceiver(this, sessionReadyReceiver);
    }

    private BroadcastReceiver sessionReadyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                initPedometer(context);
            } else {
                Log.d(TAG, "BuzzAdPopPedometer failure, needs Build.VERSION_CODE >= 19");
            }
            Log.d(TAG, "Session is Ready. Ads can be loaded now.");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void initPedometer(Context context) {
            Log.d(TAG, "Session is Ready. initPedometer");

            if (popConfig.getPedometerConfig() != null) {
                BuzzAdPopPedometer.init(context, APP_ID, UNIT_ID_POP);
            }
        }
    };

    @NotNull
    private PopHeaderConfig buildPedometerPopHeaderConfig() {
        return new PopHeaderConfig.Builder()
                // use following code for customizing
                .pedometerStepCountDescription(getString(R.string.pop_pedometer_dashboard_today_steps))
                .pedometerDashboardProgressChartColor(android.R.color.holo_red_dark)
                .pedometerDashboardProgressChartGuideColor(android.R.color.holo_red_light)
                .pedometerInProgressDescription(getString(R.string.pop_pedometer_dashboard_inprogress_description))
                .pedometerRewardableDescription(getString(R.string.pop_pedometer_dashboard_rewardable_description))
                .drawableProgressIcon(R.drawable.bz_pop_ic_pedometer_stat)
                .drawablePopPedometerDescriptionIcon(R.drawable.bz_pop_ic_pedometer_run)
                .pedometerDashboardBoundaryImage(R.drawable.dashboard_boundary)
                .build();
    }

    @NotNull
    private PedometerConfig buildPedometerConfig() {
        return new PedometerConfig
                .Builder(UNIT_ID_PEDOMETER, UNIT_ID_PEDOMETER_REWARD)
                // use following code for customizing
//                .pedometerIntroUnitId(App.unitIdPedometerPopIntro)
//                .tutorialUrl("https://www.buzzvil.com/ko/main")
//                .toolbarTitleResId(R.string.benefit_pop_pedometer_toolbar_title)
//                .introDescriptionResId(R.string.benefit_pop_pedometer_intro_description)
//                .introTitleResId(R.string.benefit_pop_pedometer_intro_title)
//                .introImageDrawableResId(R.drawable.bz_img_buzzvil_logo)
//                .switchThumbActiveColorResId(android.R.color.holo_red_dark)
//                .switchThumbInactiveColorResId(android.R.color.holo_green_dark)
//                .switchTrackInactiveColorResId(android.R.color.holo_blue_light)
//                .milestoneProgressColorResId(android.R.color.holo_red_dark)
//                .milestoneProgressGuideColorResId(android.R.color.holo_green_dark)
//                .milestoneRewardIconResId(R.drawable.ic_exit)
//                .bottomSheetImageResId(R.drawable.bz_img_buzzvil_logo)
//                .bottomSheetInProgressStringResId(R.string.benefit_pop_pedometer_bottomsheet_in_progress)
//                .bottomSheetSuccessStringResId(R.string.benefit_pop_pedometer_bottomsheet_success)
//                .popIconProgressColorResId(android.R.color.holo_red_dark)
//                .popIconProgressGuideColorResId(android.R.color.holo_green_dark)
//                .popIconRewardIconResId(R.drawable.ic_exit)
//                .popIconBackgroundColorResId(android.R.color.white)
//                .popIconTextColorResId(android.R.color.black)
//                .rewardAvailableTextColorId(R.color.bzv_status_best)
//                .rewardAvailableBackgroundId(R.drawable.benefit_pop_bg_reward_available)
                .build();
    }

    /**
     * [CUSTOM_POP_HEADER_VIEW_ADAPTER] Set custom CustomPopHeaderViewAdapter
     */
    private void initBuzzAdBenefitWithCustomPopHeaderViewAdapter() {
        final FeedConfig feedConfig = new FeedConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                .feedToolbarHolderClass(DefaultPopToolbarHolder.class)
                .feedHeaderViewAdapterClass(CustomPopHeaderViewAdapter.class)
                .articlesEnabled(true)
                .articleInAppLandingEnabled(true)
                .build();
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(getApplicationContext())
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimary)
                .notificationId(1021)
                .build();
        final PopConfig popConfig = new PopConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .initialPopIdleMode(PopConfig.PopIdleMode.INVISIBLE)
                .feedConfig(feedConfig)
                .popNotificationConfig(popNotificationConfig)
                .previewIntervalInMillis(1000) // for test
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .add(popConfig)
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
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(getApplicationContext())
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .notificationId(1021)
                .build();

        final PopConfig popConfig = new PopConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .initialPopIdleMode(PopConfig.PopIdleMode.INVISIBLE)
                .controlService(CustomControlService.class)
                .popNotificationConfig(popNotificationConfig)
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .add(popConfig)
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
