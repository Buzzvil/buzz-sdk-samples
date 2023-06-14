package com.buzzvil.booster.sample.publisher

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterUser
import com.buzzvil.booster.external.campaign.BuzzBoosterActivityTag
import com.buzzvil.booster.external.campaign.CampaignEntryView
import com.buzzvil.booster.external.campaign.CampaignType
import com.buzzvil.booster.sample.publisher.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var buzzBooster: BuzzBooster
    private var login: Boolean = false
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        buzzBooster = BuzzBooster.getInstance()

        registerViewEvent()
        addCustomEntryViewDynamically()

        if (App.campaignId != null) {
            handler = Handler(Looper.getMainLooper())
            runnable = object : Runnable {
                override fun run() {
                    BuzzBooster.getInstance()
                        .finishActivity(this@MainActivity, BuzzBoosterActivityTag.CAMPAIGN_DETAIL)
                    BuzzBooster.getInstance().showCampaign(this@MainActivity, App.campaignId!!)
                    handler!!.postDelayed(this, 3000)
                }
            }
            handler!!.post(runnable!!) // 핸들러에 첫 번째 실행을 예약합니다.
        }
    }

    override fun onDestroy() {
        runnable?.let {
            handler?.removeCallbacks(it)
        }
        super.onDestroy()
    }

    private fun registerViewEvent() {
        registerLoginAction()
        registerShowInAppMessageAction()
        registerShowCampaignListAction()
        registerShowAttendanceCampaignAction()
        registerLikeButtonAction()
        registerCommentButtonAction()
        registerPostButtonAction()
        registerTicketButtonAction()
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
                    .setProperty("IsTest", "true")
                    .build()
                BuzzBooster.setUser(user)
                activityMainBinding.loginButton.text = "logout"
            }
            login = !login
        }
    }

    private fun registerShowInAppMessageAction() {
        activityMainBinding.showInAppMessageButton.setOnClickListener {
            BuzzBooster.getInstance().showInAppMessage(this)
        }
    }

    private fun registerShowCampaignListAction() {
        activityMainBinding.showCampaignListButton.setOnClickListener {
            BuzzBooster.getInstance().showCampaign(this)
        }
    }

    private fun registerShowAttendanceCampaignAction() {
        activityMainBinding.showAttendanceCampaignButton.setOnClickListener {
            BuzzBooster.getInstance().showCampaign(this, CampaignType.Attendance)
        }
    }

    private fun registerLikeButtonAction() {
        activityMainBinding.likeButton.setOnClickListener {
            BuzzBooster.getInstance().sendEvent("bb_like", mapOf("liked_content_id" to "post_1"))
        }
    }

    private fun registerCommentButtonAction() {
        activityMainBinding.commentButton.setOnClickListener {
            BuzzBooster.getInstance().sendEvent(
                "bb_comment",
                mapOf("commented_content_id" to "post_2", "comment" to "Great post!")
            )
        }
    }

    private fun registerPostButtonAction() {
        activityMainBinding.postButton.setOnClickListener {
            BuzzBooster.getInstance()
                .sendEvent("bb_posting_content", mapOf("posted_content_id" to "post_3"))
        }
    }

    private fun registerTicketButtonAction() {
        activityMainBinding.ticketButton.setOnClickListener {
            BuzzBooster.getInstance()
                .sendEvent("ticket")
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
