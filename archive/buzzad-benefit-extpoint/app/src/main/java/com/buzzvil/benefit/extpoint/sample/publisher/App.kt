package com.buzzvil.benefit.extpoint.sample.publisher

import android.app.Application
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.extauth.ExtauthConfig
import com.buzzvil.buzzad.benefit.presentation.BuzzAdTheme
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig

class App : Application() {
    val feedConfig: FeedConfig = FeedConfig.Builder(BuildConfig.BUZZ_FEED_UNIT_ID)
        .build()

    override fun onCreate() {
        super.onCreate()

        //// Extauth 모듈 활성화를 위해 다음 항목들을 추가합니다. ////
        // ExtauthConfig - (1) AppIcon (2) loginTitle (3) loginDescription 를 변경할 수 있습니다..
        val extauthConfig: ExtauthConfig = ExtauthConfig.Builder(this)
            .setAppIcon(applicationInfo.icon)
            .build()

        val buzzAdBenefitConfig: BuzzAdBenefitConfig = BuzzAdBenefitConfig.Builder(this)
            .setExtauthConfig(extauthConfig)
            .setDefaultFeedConfig(feedConfig)
            .build()

        var buzzAdTheme = BuzzAdTheme()
            .colorPrimary(R.color.buzzAdThemeColorPrimary);

        BuzzAdTheme.setGlobalTheme(buzzAdTheme);

        // 초기화
        BuzzAdBenefit.init(this, buzzAdBenefitConfig)

    }
}
