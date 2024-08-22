package com.buzzvil.buzzad.demo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop
import com.buzzvil.buzzad.benefit.pop.PopOverlayPermissionConfig
import com.buzzvil.buzzad.benefit.pop.inapppop.BuzzAdInAppPop
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler
import com.buzzvil.buzzad.benefit.presentation.feed.navigation.NavigateToFeedCommand
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdConfig
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdHandler
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdHandlerFactory
import com.buzzvil.buzzad.benefit.presentation.notification.BuzzAdPush
import com.buzzvil.buzzad.benefit.presentation.notification.PushDialogConfig
import com.buzzvil.buzzad.demo.customized.CustomNotificationWorker
import com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor
import io.mattcarroll.hover.overlay.OverlayPermission

private const val REQUEST_CODE_SHOW_POP = 1024

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var feedHandler: FeedHandler
    private lateinit var buzzAdPop: BuzzAdPop
    private lateinit var buzzAdInAppPop: BuzzAdInAppPop
    private lateinit var buzzAdPush: BuzzAdPush
    private lateinit var interstitialAdHandler: InterstitialAdHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
        setFeatures()
    }

    private fun setViewModel() {
        viewModel.feature.observe(this, {
            when (it) {
                Feature.FEED -> showFeed()
                Feature.POP -> showPop()
                Feature.IN_APP_POP -> showInAppPop()
                Feature.PUSH -> showPush()
                Feature.INTERSTITIAL -> showInterstitial()
                Feature.NATIVE -> showNative()
                Feature.RESET_ALL -> resetAll()
            }
        })
    }

    private fun setFeatures() {
        this.feedHandler = FeedHandler(this, BuildConfig.FEED_UNIT_ID)
        this.buzzAdPop = BuzzAdPop(this, BuildConfig.FEED_UNIT_ID)
        this.buzzAdInAppPop = BuzzAdInAppPop.Builder(this).build()
        this.buzzAdPush = BuzzAdPush(
            CustomNotificationWorker::class.java,
            PushDialogConfig.Builder().build()
        )
        this.interstitialAdHandler = InterstitialAdHandlerFactory().create(
            BuildConfig.INTERSTITIAL_UNIT_ID,
            InterstitialAdHandler.Type.Dialog
        )
        checkPopPermission()
    }

    private fun checkPopPermission() {
        if (intent.getBooleanExtra(SettingsMonitor.KEY_SETTINGS_RESULT, false)
            && intent.getIntExtra(SettingsMonitor.KEY_SETTINGS_REQUEST_CODE, 0) == REQUEST_CODE_SHOW_POP
        ) {
            // 권한 획득 확인 후 pop 실행
            buzzAdPop.preloadAndShowPop();
        }
    }

    private fun showFeed() {
        feedHandler.startFeedActivity(this)
    }

    private fun showPop() {
        if (BuzzAdPop.hasPermission(this) || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            buzzAdPop.preloadAndShowPop()
        } else {
            BuzzAdPop.requestPermissionWithDialog(
                this,
                PopOverlayPermissionConfig.Builder(R.string.bz_pop_default_name)
                    .settingsIntent(OverlayPermission.createIntentToRequestOverlayPermission(this))
                    .requestCode(REQUEST_CODE_SHOW_POP)
                    .build()
            )
        }
    }

    private fun showInAppPop() {
        buzzAdInAppPop.show(this)
    }

    private fun showPush() {
        if (buzzAdPush.isRegistered(this)) {
            return
        }

        buzzAdPush.registerWithDialog(
            this,
            object : BuzzAdPush.OnRegisterListener {
                override fun onSuccess() {
                    Toast.makeText(applicationContext, R.string.push_success_message, Toast.LENGTH_SHORT).show()
                }

                override fun onCanceled() {
                    Toast.makeText(applicationContext, R.string.push_failure_message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun showInterstitial() {
        val config = InterstitialAdConfig.Builder()
            .navigateCommand(NavigateToFeedCommand(BuildConfig.FEED_UNIT_ID))
            .build()

        interstitialAdHandler.show(
            this,
            config
        )
    }

    private fun showNative() {
        val direction = MainFragmentDirections.navigateToNativeAd()
        findNavController(R.id.mainContent).navigate(direction)
    }

    private fun resetAll() {
        unregisterPop()
        unregisterPush()
        removeInAppPop()
    }

    private fun unregisterPop() {
        buzzAdPop.removePop(this)
    }

    private fun unregisterPush() {
        buzzAdPush.unregister(this)
    }

    private fun removeInAppPop() {
        buzzAdInAppPop.hide()
    }
}
