package com.buzzvil.booster.sample.publisher

import android.app.Application
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterConfig

class App: Application() {
    companion object {
        const val USER_ID = "BuzzBoosterAndroidTestUser"
        private const val APP_KEY = "0d944ac8a05b"
    }

    override fun onCreate() {
        super.onCreate()
        val buzzBoosterConfig = BuzzBoosterConfig(
            appKey = APP_KEY,
            notificationPendingActivityClass = MainActivity::class.java
        )
        BuzzBooster.init(this, buzzBoosterConfig)
    }
}
