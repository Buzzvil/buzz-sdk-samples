package com.buzzvil.pedometer.sample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.multidex.MultiDex;

import com.buzzvil.pedometer.standalone.BuzzPedometerSdk;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startService();
        initBuzzPedometer();
    }

    private void startService() {
        Intent serviceIntent = new Intent(this, PedometerForegroundService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    private void initBuzzPedometer() { BuzzPedometerSdk.init(getApplicationContext()); }
}