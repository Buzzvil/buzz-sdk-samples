package com.buzzvil.sample.buzzvil_sdk_v6_sample

import android.app.Application
import android.widget.Toast
import com.buzzvil.buzzbenefit.BuzzBenefitConfig
import com.buzzvil.buzzbenefit.pop.BuzzPopConfig
import com.buzzvil.buzzbenefit.pop.BuzzPopNotificationConfig
import com.buzzvil.buzzbenefit.pop.BuzzPopSidePosition
import com.buzzvil.buzzbenefit.pop.BuzzPopTheme
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant.YOUR_APP_ID
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant.YOUR_POP_UNIT_ID
import com.buzzvil.sample.buzzvil_sdk_v6_sample.pop.CustomPopToolbarHolder
import com.buzzvil.sample.buzzvil_sdk_v6_sample.pop.YourBuzzPopControlService
import com.buzzvil.sample.buzzvil_sdk_v6_sample.pop.YourBuzzPopMessageView
import com.buzzvil.sample.buzzvil_sdk_v6_sample.pop.YourBuzzPopToolbarHolder
import com.buzzvil.sample.buzzvil_sdk_v6_sample.pop.YourBuzzPopUtilityLayoutHandler
import com.buzzvil.sdk.BuzzvilSdk

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // BuzzBenefit 설정
        val buzzBenefitConfig = BuzzBenefitConfig.Builder(YOUR_APP_ID)

        val buzzPopNotificationConfig = BuzzPopNotificationConfig.Builder(this@App)
            .notificationId(YourBuzzPopControlService.POP_NOTIFICATION_ID)
            .smallIconResId(R.drawable.your_small_icon)
            .titleResId(R.string.your_pop_notification_title)
            .textResId(R.string.your_pop_notification_text)
            .colorResId(R.color.your_pop_notification_color)
            .notificationId(5000) // 기본값
            .notificationId(1021)
            .build()

        val buzzPopConfigBuilder = BuzzPopConfig
            .Builder(YOUR_POP_UNIT_ID)
//            .idleTimeInMillis(5000L) // Pop 표시 시간
//            .initialSidePosition(BuzzPopSidePosition.RIGHT, 0.6f) // Pop 표시 위치
//            .marginBetweenIconAndPreviewInDp(8.0f) // 팝 버튼과 메시지 사이 간격
//            .marginBetweenIconAndScreenEdgeInDp(24.0f) // 팝 버튼과 화면 끝 사이 간격
//            .buzzPopNotificationConfig(buzzPopNotificationConfig)
//            .buzzPopToolbarHolderClass(YourBuzzPopToolbarHolder::class.java)
//            .buzzPopMessageViewClass(YourBuzzPopMessageView::class.java)
//            .buzzPopToolbarHolderClass(CustomPopToolbarHolder::class.java)
//            .buzzPopUtilityLayoutHandlerClass(YourBuzzPopUtilityLayoutHandler::class.java)

        // BuzzPopTheme.iconResId = R.drawable.your_pop_icon
        // BuzzPopTheme.rewardReadyIconResId = R.drawable.your_pop_icon_reward_ready
        // BuzzPopTheme.popFeedCloseIconResId = R.drawable.your_pop_icon_feed_close

        // BuzzTheme.setCustomBuzzCtaView(YourCustomBuzzCtaView::class.java)

        buzzBenefitConfig.setBuzzPopConfig(buzzPopConfigBuilder.build())

        // Buzzvil SDK 초기화
        BuzzvilSdk.initialize(
            application = this@App,
            buzzBenefitConfig = buzzBenefitConfig.build()
        ) {
            Toast.makeText(this, "BuzzvilSdk is initialized", Toast.LENGTH_SHORT).show()
        }
    }
}
