package com.buzzvil.booster.sample.publisher

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterUser
import com.buzzvil.booster.external.campaign.CampaignType
import com.buzzvil.booster.sample.publisher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private var login: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuzzBooster.hasUnhandledNotificationClick(this)) {
            BuzzBooster.handleNotification(this)
        }
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        registerViewEvent()
    }

    private fun registerViewEvent() {
        registerAppThemeAction()
        registerLoginAction()
        registerShowInAppMessageAction()
        registerShowHomeAction()
        registerShowAttendanceCampaignAction()
        registerLikeButtonAction()
        registerCommentButtonAction()
        registerPostButtonAction()
        registerUploadReviewButtonAction()
        registerPageVisitButtonAction()
    }

    private fun registerAppThemeAction() {
        activityMainBinding.themeModeLightRadioButton.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        activityMainBinding.themeModeDarkRadioButton.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        activityMainBinding.themeModeSystemRadioButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            }
        }
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
                    .addProperty("LoginType", "Social(Google)")
                    .addProperty("Gender", "Male")
                    .addProperty("Age", "20")
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

    private fun registerShowHomeAction() {
        activityMainBinding.showHomeButton.setOnClickListener {
            BuzzBooster.getInstance().showHome(this)
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

    private fun registerUploadReviewButtonAction() {
        activityMainBinding.reviewButton.setOnClickListener {
            BuzzBooster.getInstance()
                .sendEvent("bb_review_upload", mapOf("uploaded_review_id" to "review_1"))
        }
    }

    private fun registerPageVisitButtonAction() {
        activityMainBinding.visitPageButton.setOnClickListener {
            BuzzBooster.getInstance()
                .sendEvent("bb_page_visit", mapOf("visited_page_id" to "page_1"))
        }
    }
}
