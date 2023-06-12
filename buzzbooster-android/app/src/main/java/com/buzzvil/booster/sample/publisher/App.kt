package com.buzzvil.booster.sample.publisher

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterConfig
import com.buzzvil.booster.external.BuzzBoosterUser
import com.buzzvil.booster.external.campaign.OptInMarketingCampaignMoveButtonClickListener
import java.util.UUID


class App : Application() {
    companion object {
        val USER_ID = UUID.randomUUID().toString()
        private const val APP_KEY = "404581383723425"
    }

    override fun onCreate() {
        super.onCreate()
        ResetManager.reset(this)

        val prefs = getSharedPreferences("prefs.db", Context.MODE_PRIVATE)
        val env = prefs.getString("environment", "prod")
        changeServer(env!!)


        val appKey = prefs.getString("appKey", APP_KEY)
        val buzzBoosterConfig = BuzzBoosterConfig(appKey = appKey!!)
        BuzzBooster.init(this, buzzBoosterConfig)

        val userId = prefs.getString("userID", null)
        if (userId != null) {
            val user = BuzzBoosterUser(userId)
            BuzzBooster.setUser(user)
        }

        BuzzBooster.getInstance().setOptInMarketingCampaignMoveButtonClickListener(object :
            OptInMarketingCampaignMoveButtonClickListener {
            override fun onClick() {
                val intent = Intent(this@App, OptInMarketingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        })
    }

    private fun changeServer(environment: String) {
        val prefs: SharedPreferences = getSharedPreferences("BuzzBoosterSprefsForMutator", MODE_PRIVATE)
        val editor = prefs.edit()
        val url = when (environment) {
            "prod" -> {
                "https://api.buzzvil.com/buzzbooster/"
            }
            "staging" -> {
                "https://api-staging.buzzvil.com/buzzbooster/"
            }
            "stagingqa" -> {
                "https://api-stagingqa.buzzvil.com/buzzbooster/"
            }
            else -> {
                "https://api-dev.buzzvil.com/buzzbooster/"
            }
        }
        editor.putString("server_mutator_server_env_prefix-BUZZ_API.name", url)
        editor.apply()
    }
}
