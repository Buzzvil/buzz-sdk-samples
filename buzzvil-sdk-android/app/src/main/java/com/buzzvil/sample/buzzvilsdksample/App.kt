package com.buzzvil.sample.buzzvilsdksample

import android.app.Application
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.sample.buzzvilsdksample.custom.CustomFeedHeaderViewAdapter
import com.buzzvil.sample.buzzvilsdksample.custom.CustomLauncher
import com.buzzvil.sdk.BuzzvilSdk

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Feed(베네핏허브) 설정
        val feedConfig = FeedConfig.Builder(Constant.YOUR_FEED_ID)
            // 기본 내비게이션 바 제거하기
            // .navigationBarVisibility(false)
            .feedHeaderViewAdapterClass(CustomFeedHeaderViewAdapter::class.java)
            .build()

        // BuzzBenefit 설정
        val buzzAdBenefitConfig = BuzzAdBenefitConfig.Builder(Constant.YOUR_APP_ID)
            .setDefaultFeedConfig(feedConfig)
            .build()

        // Buzzvil SDK 초기화
        BuzzvilSdk.initialize(
            application = this@App,
            buzzAdBenefitConfig = buzzAdBenefitConfig
        )

        BuzzAdBenefit.setLauncher(CustomLauncher())
    }
}
