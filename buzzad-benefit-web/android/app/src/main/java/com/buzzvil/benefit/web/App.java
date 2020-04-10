package com.buzzvil.benefit.web;

import android.app.Application;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;

public class App extends Application {
    public static final String MY_WEB_PAGE = "https://buzzvil.github.io/buzzad-benefit-sdk-publisher-web/";

    // This is for test. use your APP_ID
    final String YOUR_APP_ID = "YOUR_APP_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        BuzzAdBenefit.init(this, new BuzzAdBenefitConfig.Builder(YOUR_APP_ID).build());
    }
}
