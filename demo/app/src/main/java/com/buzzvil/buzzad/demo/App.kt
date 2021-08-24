package com.buzzvil.buzzad.demo

import android.app.Application
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.core.models.UserProfile
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.config.OptInFeature

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val feedConfig = FeedConfig.Builder(this, BuildConfig.FEED_UNIT_ID)
            .optInFeatureList(mutableListOf(OptInFeature.Pop))
            .build()

        val buzzAdBenefitConfig = BuzzAdBenefitConfig.Builder(this)
            .setFeedConfig(feedConfig)
            .build()

        BuzzAdBenefit.init(this, buzzAdBenefitConfig)

        val builder = UserProfile.Builder(BuzzAdBenefit.getUserProfile())
        val userProfile = builder.userId("USER_ID")
            .gender(UserProfile.Gender.MALE)
            .birthYear(1985)
            .build()
        BuzzAdBenefit.setUserProfile(userProfile)
    }
}
