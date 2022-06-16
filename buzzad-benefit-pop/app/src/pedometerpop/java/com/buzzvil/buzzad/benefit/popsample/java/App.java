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
import com.buzzvil.buzzad.benefit.pop.PopConfig;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.pop.pedometer.BuzzAdPopPedometer;
import com.buzzvil.buzzad.benefit.pop.pedometer.PedometerConfig;
import com.buzzvil.buzzad.benefit.pop.popicon.SidePosition;
import com.buzzvil.buzzad.benefit.pop.presentation.DefaultPedometerPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.pop.toolbar.DefaultPopToolbarHolder;
import com.buzzvil.buzzad.benefit.popsample.R;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;

import org.jetbrains.annotations.NotNull;

public class App extends MultiDexApplication {
    public static final String TAG = "PopSampleApp";

    // Caution: Please replace IDs with your unit IDs.
    public static final String UNIT_ID_POP = "236027834764095";
    public static final String UNIT_ID_PEDOMETER = "450190159219814";
    public static final String UNIT_ID_PEDOMETER_REWARD = "153253018216976";
    public static final String UNIT_ID_PEDOMETER_INTRO = "304632571836222";
    public static final String UNIT_ID_POP_EXIT = "96451896568578";

    private PopConfig popConfig;

    @Override
    public void onCreate() {
        Log.d(TAG, "BuzzAdPopPedometer onCreate");
        super.onCreate();

        initBuzzAdBenefit();
    }

    private void initBuzzAdBenefit() {
        final FeedConfig feedConfig = new FeedConfig.Builder(UNIT_ID_POP)
                // DefaultPopToolbarHolder: DefaultToolbar
                // TemplatePopToolbarHolder: Minimum customize, pop feed icon, name, button
                // CustomPopToolbarHolder: Use layout for toolbar
                .feedToolbarHolderClass(DefaultPopToolbarHolder.class)
                .feedHeaderViewAdapterClass(DefaultPedometerPopHeaderViewAdapter.class)
                .build();
        final PopNotificationConfig popNotificationConfig = new PopNotificationConfig.Builder(getApplicationContext())
                .smallIconResId(R.drawable.ic_notification_pop_gift)
                .titleResId(R.string.pop_notification_title)
                .textResId(R.string.pop_notification_text)
                .colorResId(R.color.colorPrimary)
                .notificationId(1021)
                .build();
        final com.buzzvil.buzzad.benefit.pop.pedometer.PedometerConfig pedometerConfig = buildPedometerConfig();
        popConfig = new PopConfig.Builder(feedConfig)
                .popMode(PopConfig.PopMode.PEDOMETER_POP)
                .initialSidePosition(new SidePosition(SidePosition.Side.RIGHT, 0.6f))
                .pedometerConfig(pedometerConfig)
//                .popExitUnitId(UNIT_ID_POP_EXIT) // For Interstitial Ad: Pop Exit
                .popNotificationConfig(popNotificationConfig)
                .previewIntervalInMillis(1000) // for test
                .build();

        final BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .setPopConfig(popConfig)
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
                BuzzAdPopPedometer.init(context, UNIT_ID_POP);
            }
        }
    };

    @NotNull
    private PedometerConfig buildPedometerConfig() {
        return new PedometerConfig
                .Builder(this, UNIT_ID_PEDOMETER, UNIT_ID_PEDOMETER_REWARD)
                // use following code for customizing
//                .pedometerIntroUnitId(UNIT_ID_PEDOMETER_INTRO) // For Interstitial Ad: Pedometer Fragment Intro
//                .tutorialUrl("https://www.buzzvil.com/ko/main")
//                .introImageDrawableResId(R.drawable.bz_img_buzzvil_logo)
//                .bottomSheetImageResId(R.drawable.bz_img_buzzvil_logo)
                .build();
    }
}
