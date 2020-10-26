package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;

public class App extends MultiDexApplication {
    public static final String TAG = "App";
    public static final String BUZZ_AD_PREFERENCE = "BUZZ_AD_PREFERENCE";
    public static final String UNIT_ID_POP = "236027834764095";
    private BuzzAdPopController buzzAdBenefitController;

    @Override
    public void onCreate() {
        super.onCreate();
        initBuzzAdBenefitController();
    }

    private void initBuzzAdBenefitController() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(BUZZ_AD_PREFERENCE, Context.MODE_PRIVATE);
        buzzAdBenefitController = new BuzzAdPopController(getApplicationContext(), sharedPref);

        boolean buzzAdBenefitEnabled = buzzAdBenefitController.isBuzzAdBenefitEnabled();
        Log.d(TAG, "onCreate buzzAdBenefitEnabled == " + buzzAdBenefitEnabled);
        if (buzzAdBenefitEnabled) {
            Log.d(TAG, "onCreate buzzAdBenefitEnabled == true initBuzzAdBenefit");
            buzzAdBenefitController.initBuzzAdBenefit();
        } else {
            Log.d(TAG, "onCreate buzzAdBenefitEnabled == false skip initBuzzAdBenefit");
        }
    }

    public boolean isBuzzAdBenefitInitialized() {
        return buzzAdBenefitController.isBuzzAdBenefitInitialized;
    }

    public void clearBuzzAdBenefit() {
        buzzAdBenefitController.clearBuzzAdBenefit();
    }

    public void buildBuzzAdPop() {
        try {
            buzzAdBenefitController.buildBuzzAdPop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public BuzzAdPop getBuzzAdPop() {
        return buzzAdBenefitController.getBuzzAdPop();
    }

    public void initBuzzAdBenefit() {
        if (!isBuzzAdBenefitInitialized()) {
            buzzAdBenefitController.initBuzzAdBenefit();
        } else {
            Log.d(TAG, "initBuzzAdBenefit fail - already initialized");
        }
    }
}
