package com.buzzvil.benefit.extpoint.sample.publisher

import android.app.Application
import android.graphics.Color
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.extauth.BuzzAdBenefitExtauthConfig
import com.buzzvil.buzzad.benefit.extauth.BuzzAdBenefitExtauthInstaller
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.header.DefaultFeedHeaderViewAdapter

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val feedConfig = FeedConfig.Builder(this, BuildConfig.BUZZ_FEED_UNIT_ID)
            .feedHeaderViewAdapterClass(DefaultFeedHeaderViewAdapter::class.java)
            .build()

        //// Extauth 모듈 활성화를 위해 다음 항목들을 추가합니다. ////
        // ExtauthConfig - (1) AppIcon 과 (2) ThemeColor 를 변경할 수 있습니다.
        val extauthConfig = BuzzAdBenefitExtauthConfig.Builder(this)
            .setAppIcon(applicationInfo.icon)
            .build()
        // ExtauthInstaller
        val extauthInstaller = BuzzAdBenefitExtauthInstaller(extauthConfig)
        val buzzAdBenefitConfig = extauthInstaller.installTo(BuzzAdBenefitConfig.Builder(this))
            .setFeedConfig(feedConfig)
            .build()

        // (3) 초기화
        BuzzAdBenefit.init(this, buzzAdBenefitConfig)
    }
}
