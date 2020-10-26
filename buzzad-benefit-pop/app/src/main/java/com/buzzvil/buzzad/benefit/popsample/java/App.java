package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;
import com.buzzvil.buzzad.benefit.pop.DefaultPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.pop.SidePosition;
import com.buzzvil.buzzad.benefit.pop.toolbar.DefaultPopToolbarHolder;
import com.buzzvil.buzzad.benefit.popsample.R;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomControlService;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomPopToolbarHolder;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;

public class App extends MultiDexApplication {
    public static final String TAG = "App";
    public static final String BUZZ_AD_PREFERENCE = "BUZZ_AD_PREFERENCE";
    public static final String POP_ENABLED = "popInitialized";
    public static final String UNIT_ID_POP = "236027834764095";
    public boolean isBuzzAdBenefitInitialized = false;
    private BuzzAdPop buzzAdPop = null;
    private SharedPreferences sharedPref;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPref = getApplicationContext().getSharedPreferences(BUZZ_AD_PREFERENCE, Context.MODE_PRIVATE);
        boolean popEnabled = sharedPref.getBoolean(POP_ENABLED, false);
        Log.d(TAG, "onCreate popEnabled = " + popEnabled);
        if (popEnabled) {
            Log.d(TAG, "onCreate popEnabled == true initBuzzAdBenefit");
            initBuzzAdBenefit();
        }
//        initBuzzAdBenefit();
//        initBuzzAdBenefitWithCustomPopHeaderViewAdapter(); // User this for custom pop header view adapter
//        initBuzzAdBenefitWithCustomControlService(); // Use this for custom pop notification
    }

    public void clearBuzzAdBenefit() {
        Log.d(TAG, "clearBuzzAdBenefit");
        isBuzzAdBenefitInitialized = false;
        buzzAdPop.removePop(getApplicationContext());
        buzzAdPop = null;
        sharedPref.edit().putBoolean(POP_ENABLED, false).apply();
    }

    public void buildBuzzAdPop() {
        if (isBuzzAdBenefitInitialized && buzzAdPop == null) {
            Log.d(TAG, "getBuzzAdPop success");
            buzzAdPop = new BuzzAdPop(getApplicationContext(), App.UNIT_ID_POP);
        } else {
            Log.d(TAG, "getBuzzAdPop is already initialized");
        }
    }

    public BuzzAdPop getBuzzAdPop() {
        return buzzAdPop;
    }

    public void initBuzzAdBenefit() {
        Log.d(TAG, "initBuzzAdBenefit");
        final FeedConfig feedConfig = new FeedConfig.Builder(getApplicationContext(), UNIT_ID_POP)
                // DefaultPopToolbarHolder: DefaultToolbar
                // TemplatePopToolbarHolder: Minimum customize, pop feed icon, name, button
                // CustomPopToolbarHolder: Use layout for toolbar
                .feedToolbarHolderClass(CustomPopToolbarHolder.class)
                .feedHeaderViewAdapterClass(DefaultPopHeaderViewAdapter.class)
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
                .idleTimeInMillis(10_000)
                .popNotificationConfig(popNotificationConfig)
                .previewIntervalInMillis(1000) // for test
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .add(popConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);
        sharedPref.edit().putBoolean(POP_ENABLED, true).apply();

        isBuzzAdBenefitInitialized = true;
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
    }
}
