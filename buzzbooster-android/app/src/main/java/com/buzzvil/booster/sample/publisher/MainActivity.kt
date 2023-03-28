package com.buzzvil.booster.sample.publisher

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterUser
import com.buzzvil.booster.external.campaign.CampaignEntryView
import com.buzzvil.booster.sample.publisher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var buzzBooster: BuzzBooster
    private var login: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        buzzBooster = BuzzBooster.getInstance()

        registerViewEvent()
        addCustomEntryViewDynamically()
    }

    private fun registerViewEvent() {
        registerLoginAction()
    }

    private fun registerLoginAction() {
        activityMainBinding.loginButton.setOnClickListener {
            if (login) {
                BuzzBooster.setUser(null)
                activityMainBinding.loginButton.text = "login"
            } else {
                val user = BuzzBoosterUser.Builder()
                    .setUserId(App.USER_ID)
                    .setOptInMarketing(false)
                    .setProperty("LoginType", "Social(Google)")
                    .setProperty("Gender", "Male")
                    .setProperty("Age", "20")
                    .build()
                BuzzBooster.setUser(user)
                BuzzBooster.getInstance().showInAppMessage(this)
                activityMainBinding.loginButton.text = "logout"
            }
            login = !login
        }
    }

    private fun addCustomEntryViewDynamically() {
        val parent = findViewById<ViewGroup>(R.id.entryPointPlaceholder)
        val entryView: CampaignEntryView =
            layoutInflater.inflate(R.layout.campaign_entry_point, null) as CampaignEntryView
        entryView.setEntryName("your_custom_entry_point_1")
        parent.addView(entryView)
    }
}
