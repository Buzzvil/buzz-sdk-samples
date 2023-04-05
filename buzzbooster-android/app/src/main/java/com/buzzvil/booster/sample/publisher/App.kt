package com.buzzvil.booster.sample.publisher

import android.app.Application
import android.content.Intent
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterConfig
import com.buzzvil.booster.external.campaign.OptInMarketingCampaignMoveButtonClickListener
import java.util.UUID

class App : Application() {
    companion object {
        val USER_ID = UUID.randomUUID().toString()
        private const val APP_KEY = "307117684877774"
    }

    override fun onCreate() {
        super.onCreate()
        val buzzBoosterConfig = BuzzBoosterConfig(appKey = APP_KEY)
        BuzzBooster.init(this, buzzBoosterConfig)

        BuzzBooster.getInstance().setOptInMarketingCampaignMoveButtonClickListener(object :
            OptInMarketingCampaignMoveButtonClickListener {
            override fun onClick() {
                val intent = Intent(this@App, OptInMarketingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        })
    }
}
