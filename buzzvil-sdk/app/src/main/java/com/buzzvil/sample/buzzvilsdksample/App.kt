package com.buzzvil.sample.buzzvilsdksample

import android.app.Application
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.pop.PopConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.sdk.BuzzvilSdk

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val buzzAdBenefitConfig = BuzzAdBenefitConfig.Builder("YOUR_APP_ID")

        // Feed
        val feedConfig = FeedConfig.Builder("YOUR_FEED_UNIT_ID")
            .build()
        buzzAdBenefitConfig.setDefaultFeedConfig(feedConfig)

        // Pop
        val popFeedConfig = FeedConfig.Builder("YOUR_POP_UNIT_ID")
            .build()
        val popConfig = PopConfig.Builder(popFeedConfig).build()
        buzzAdBenefitConfig.setPopConfig(popConfig)

        BuzzvilSdk.initialize(
            application = this@App,
            buzzAdBenefitConfig = buzzAdBenefitConfig.build(),
        )
    }
}
