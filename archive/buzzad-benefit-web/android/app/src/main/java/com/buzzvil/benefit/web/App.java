package com.buzzvil.benefit.web;

import android.app.Application;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;

public class App extends Application {
    public static final String MY_WEB_PAGE = "https://buzzvil.github.io/buzzad-benefit-sdk-publisher-web/";

    @Override
    public void onCreate() {
        super.onCreate();
        BuzzAdBenefit.init(this, new BuzzAdBenefitConfig.Builder(this).build());
    }
}
