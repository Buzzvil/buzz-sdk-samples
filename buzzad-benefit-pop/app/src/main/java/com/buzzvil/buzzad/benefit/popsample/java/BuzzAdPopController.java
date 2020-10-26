package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;
import com.buzzvil.buzzad.benefit.pop.DefaultPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.pop.SidePosition;
import com.buzzvil.buzzad.benefit.popsample.R;
import com.buzzvil.buzzad.benefit.popsample.java.custom.CustomPopToolbarHolder;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;

public class BuzzAdPopController {
    public static final String TAG = "App";
    public static final String BUZZ_AD_PREFERENCE = "BUZZ_AD_PREFERENCE";
    public static final String POP_ENABLED = "popInitialized";
    public static final String UNIT_ID_POP = "236027834764095";
    public boolean isBuzzAdBenefitInitialized = false;
    private BuzzAdPop buzzAdPop = null;
    private SharedPreferences sharedPref;
    private Context context;

    BuzzAdPopController(Context context, SharedPreferences sharedPref) {
        this.context = context;
        this.sharedPref = sharedPref;
    }

    public void clearBuzzAdBenefit() {
        Log.d(TAG, "clearBuzzAdBenefit");
        isBuzzAdBenefitInitialized = false;
        buzzAdPop.removePop(context);
        buzzAdPop = null;
        sharedPref.edit().putBoolean(POP_ENABLED, false).apply();
    }

    public void buildBuzzAdPop() {
        if (isBuzzAdBenefitInitialized && buzzAdPop == null) {
            Log.d(TAG, "getBuzzAdPop success");
            buzzAdPop = new BuzzAdPop(context, App.UNIT_ID_POP);
        } else {
            Log.d(TAG, "getBuzzAdPop is already initialized");
        }
    }

    public BuzzAdPop getBuzzAdPop() {
        return buzzAdPop;
    }

    public void initBuzzAdBenefit() {
        Log.d(TAG, "initBuzzAdBenefit");
        final FeedConfig feedConfig = new FeedConfig.Builder(context, UNIT_ID_POP)
                // DefaultPopToolbarHolder: DefaultToolbar
                // TemplatePopToolbarHolder: Minimum customize, pop feed icon, name, button
                // CustomPopToolbarHolder: Use layout for toolbar
                .feedToolbarHolderClass(CustomPopToolbarHolder.class)
                .feedHeaderViewAdapterClass(DefaultPopHeaderViewAdapter.class)
                .articlesEnabled(true)
                .articleInAppLandingEnabled(true)
                .build();
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(context)
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimary)
                .notificationId(1021)
                .build();
        final PopConfig popConfig = new PopConfig.Builder(context, UNIT_ID_POP)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .initialPopIdleMode(PopConfig.PopIdleMode.INVISIBLE)
                .feedConfig(feedConfig)
                .idleTimeInMillis(10_000)
                .popNotificationConfig(popNotificationConfig)
                .previewIntervalInMillis(1000) // for test
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(context)
                .add(popConfig)
                .build();
        BuzzAdBenefit.init(context, buzzAdBenefitConfig);
        sharedPref.edit().putBoolean(POP_ENABLED, true).apply();

        isBuzzAdBenefitInitialized = true;
    }
}
