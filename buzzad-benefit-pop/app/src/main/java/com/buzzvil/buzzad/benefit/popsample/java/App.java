package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;

public class App extends MultiDexApplication {
    public static final String TAG = "App";
    public static final String BUZZ_AD_PREFERENCE = "BUZZ_AD_PREFERENCE";
    public static final String POP_ENABLED = "popInitialized";
    public static final String UNIT_ID_POP = "236027834764095";
    private BuzzAdPopController buzzAdPopController;

    @Override
    public void onCreate() {
        super.onCreate();
        initBuzzAdBenefitController();
    }

    private void initBuzzAdBenefitController() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(BUZZ_AD_PREFERENCE, Context.MODE_PRIVATE);
        boolean popEnabled = sharedPref.getBoolean(POP_ENABLED, false);
        buzzAdPopController = new BuzzAdPopController(getApplicationContext(), sharedPref);
        Log.d(TAG, "onCreate popEnabled = " + popEnabled);
        if (popEnabled) {
            Log.d(TAG, "onCreate popEnabled == true initBuzzAdBenefit");
            buzzAdPopController.initBuzzAdBenefit();
        }
    }

    public boolean isBuzzAdBenefitInitialized() {
        return buzzAdPopController.isBuzzAdBenefitInitialized;
    }

    public void clearBuzzAdBenefit() {
        buzzAdPopController.clearBuzzAdBenefit();
    }

    public void buildBuzzAdPop() {
        buzzAdPopController.buildBuzzAdPop();
    }

    public BuzzAdPop getBuzzAdPop() {
        return buzzAdPopController.getBuzzAdPop();
    }

    public void initBuzzAdBenefit() {
        buzzAdPopController.initBuzzAdBenefit();
    }
}
