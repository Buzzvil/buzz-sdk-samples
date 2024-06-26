package com.buzzvil.booster.sample.publisher

import android.app.Application
import android.content.Intent
import android.util.Log
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterConfig
import com.buzzvil.booster.external.UserEvent
import com.buzzvil.booster.external.UserEventListener
import com.buzzvil.booster.external.campaign.OptInMarketingCampaignMoveButtonClickListener
import com.google.firebase.messaging.FirebaseMessaging
import java.util.UUID

class App : Application() {
    companion object {
        val USER_ID = UUID.randomUUID().toString() // TODO: Replace with your user id
        private const val APP_KEY = "307117684877774" // TODO: Replace with your app key
    }

    private val userEventListener = object : UserEventListener {
        override fun onUserEvent(userEvent: UserEvent) {
            Log.d("App", "onUserEvent: $userEvent")
        }
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

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            BuzzBooster.setFCMToken(it)
        }

        BuzzBooster.getInstance().addUserEventListener(userEventListener)
    }

    override fun onTerminate() {
        super.onTerminate()
        BuzzBooster.getInstance().removeUserEventListener(userEventListener)
    }
}
