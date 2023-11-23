package com.buzzvil.sample.buzzvilsdksample

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.core.models.AutoplayType
import com.buzzvil.buzzad.benefit.core.models.UserPreferences
import com.buzzvil.buzzad.benefit.pop.PopConfig
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig
import com.buzzvil.buzzad.benefit.pop.popicon.SidePosition
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.sample.buzzvilsdksample.custom.CustomFeedHeaderViewAdapter
import com.buzzvil.sample.buzzvilsdksample.custom.CustomLauncher
import com.buzzvil.sample.buzzvilsdksample.pop.YourPopControlService
import com.buzzvil.sdk.BuzzvilSdk

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Feed(베네핏허브) 설정
        val feedConfig = FeedConfig.Builder(Constant.YOUR_FEED_ID)
            // 기본 내비게이션 바 제거하기
            // .navigationBarVisibility(false)
            .feedHeaderViewAdapterClass(CustomFeedHeaderViewAdapter::class.java)
            // Pop UnitID 없이 Pop 기능만 켜기
            // .optInFeatureList(Collections.singletonList(OptInFeature.Pop))
            .build()

        // PopNotificationConfig 설정
        val popNotificationConfig = PopNotificationConfig.Builder(this@App)
            .notificationId(YourPopControlService.POP_NOTIFICATION_ID)
            .build()

        // Pop을 Feed와 분리하여 따로 설정
        val popFeedConfig = FeedConfig.Builder(Constant.YOUR_POP_ID)
            .build()
        val popConfig = PopConfig.Builder(popFeedConfig)
            .popNotificationConfig(popNotificationConfig)
            .controlService(YourPopControlService::class.java)
            // .initialSidePosition(SidePosition(SidePosition.Side.RIGHT, 0.6f))
            // .idleTimeInMillis(5000) // Pop 표시 시간
            // .marginBetweenIconAndPreviewInDp(8.0f) // 팝 버튼과 메시지 사이 간격
            // .marginBetweenIconAndScreenEdgeInDp(24.0f) // 팝 버튼과 화면 끝 사이 간격
            .build()

        // BuzzBenefit 설정
        val buzzAdBenefitConfig = BuzzAdBenefitConfig.Builder(Constant.YOUR_APP_ID)
            .setDefaultFeedConfig(feedConfig)
            .setPopConfig(popConfig)
            .build()

        // Buzzvil SDK 초기화
        BuzzvilSdk.initialize(
            application = this@App,
            buzzAdBenefitConfig = buzzAdBenefitConfig
        )

        // 커스텀 런쳐 설정하기
        // setCustomLauncher()

        // 동영상 광고 자동재생 설정하기
        // setVideoAutoPlayMode()

        // 세션 준비가 완료되면 브로드캐스트를 수신하기
        // setSessionBroadcastReceiver()
    }

    private fun setCustomLauncher() {
        BuzzAdBenefit.setLauncher(CustomLauncher())
    }

    private fun setVideoAutoPlayMode() {
        val userPreferences = UserPreferences.Builder(BuzzAdBenefit.getUserPreferences())
            .autoplayType(AutoplayType.ON_WIFI)
            // .autoplayType(AutoplayType.DISABLED) // 동영상 광고를 자동으로 재생하지 않습니다.
            // .autoplayType(AutoplayType.ENABLED) //  동영상 광고를 항상 자동재생합니다.
            .build()
        BuzzAdBenefit.setUserPreferences(userPreferences)
    }

    private fun setSessionBroadcastReceiver() {
        val sessionReadyReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // 세션이 준비 되었습니다.
            }
        }

        // 세션 준비가 완료되면 브로드캐스트를 수신합니다.
        BuzzAdBenefit.registerSessionReadyBroadcastReceiver(this, sessionReadyReceiver)

        // 브로드캐스트를 수신하지 않으려면 다음과 같이 해제합니다.
        BuzzAdBenefit.unregisterSessionReadyBroadcastReceiver(sessionReadyReceiver)
    }
}
