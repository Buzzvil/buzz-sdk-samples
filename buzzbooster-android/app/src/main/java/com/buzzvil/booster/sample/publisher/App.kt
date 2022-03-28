package com.buzzvil.booster.sample.publisher

import android.app.Application
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterConfig
import java.util.*

class App: Application() {
    companion object {
        val USER_ID = UUID.randomUUID().toString()
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
