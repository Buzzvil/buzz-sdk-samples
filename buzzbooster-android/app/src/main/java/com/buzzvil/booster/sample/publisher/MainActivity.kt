package com.buzzvil.booster.sample.publisher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.sample.publisher.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var buzzBooster: BuzzBooster
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        buzzBooster = BuzzBooster.getInstance()

        registerViewEvent()
    }

    private fun registerViewEvent() {
        registerSendEventAction()
        registerLoginAction()
        registerLogoutAction()
    }

    private fun registerSendEventAction() {
        activityMainBinding.sendEventButton.setOnClickListener {
            buzzBooster.sendEvent("integration test")
        }
    }

    private fun registerLoginAction() {
        activityMainBinding.loginButton.setOnClickListener {
            BuzzBooster.setUserId(App.USER_ID)
        }
    }

    private fun registerLogoutAction() {
        activityMainBinding.logoutButton.setOnClickListener {
            BuzzBooster.setUserId(null)
        }
    }
}
