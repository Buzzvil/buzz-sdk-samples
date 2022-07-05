package com.buzzvil.booster.sample.publisher

import android.app.Application
import android.util.Log
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterConfig
import java.util.*

class App: Application() {
    companion object {
        val USER_ID = UUID.randomUUID().toString()
        private const val APP_KEY = "307117684877774"
    }

    override fun onCreate() {
        super.onCreate()
        val buzzBoosterConfig = BuzzBoosterConfig(appKey = APP_KEY)
        BuzzBooster.init(this, buzzBoosterConfig)
    }
}
