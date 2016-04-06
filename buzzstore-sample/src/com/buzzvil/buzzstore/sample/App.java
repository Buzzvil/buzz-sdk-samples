package com.buzzvil.buzzstore.sample;

import android.app.Application;
import android.util.Log;

import com.buzzvil.buzzstore.sdk.BuzzStore;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Application", "create");

        // app_key : SDK 사용을 위한 앱키로, 어드민에서 확인 가능
        BuzzStore.init("app_key", this);
    }

}
