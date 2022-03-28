package com.buzzvil.booster.sample.publisher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.sample.publisher.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var buzzBooster: BuzzBooster
    private var login: Boolean = false
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
    }

    private fun registerSendEventAction() {
        activityMainBinding.sendEventButton.setOnClickListener {
            // 이벤트 발생 3회 이상부터 리워드 지급
            buzzBooster.sendEvent("integration")
        }
    }

    private fun registerLoginAction() {
        activityMainBinding.loginButton.setOnClickListener {
            if (login) {
                BuzzBooster.setUserId(null)
                activityMainBinding.loginButton.text = "login"
            } else {
                BuzzBooster.setUserId(App.USER_ID)
                activityMainBinding.loginButton.text = "logout"
            }
            login = !login
        }
    }
}
