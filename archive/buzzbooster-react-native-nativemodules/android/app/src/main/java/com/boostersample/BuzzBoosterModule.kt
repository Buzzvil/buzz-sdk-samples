package com.boostersample

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.buzzvil.booster.external.BuzzBooster
import com.buzzvil.booster.external.BuzzBoosterActivityTag
import com.buzzvil.booster.external.BuzzBoosterConfig
import com.buzzvil.booster.external.BuzzBoosterJavaScriptInterface
import com.buzzvil.booster.external.BuzzBoosterUser
import com.buzzvil.booster.external.UserEvent
import com.buzzvil.booster.external.UserEventListener
import com.buzzvil.booster.external.campaign.CampaignType
import com.buzzvil.booster.external.campaign.OptInMarketingCampaignMoveButtonClickListener
import com.facebook.react.bridge.ActivityEventListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule

class BuzzBoosterModule(
    private val reactContext: ReactApplicationContext
) : ReactContextBaseJavaModule(reactContext), ActivityEventListener, OptInMarketingCampaignMoveButtonClickListener, UserEventListener {
    init {
        reactContext.addActivityEventListener(this);
    }

    override fun getName(): String {
        return "BuzzBooster"
    }

    override fun onClick() {
        BuzzBooster.getInstance().finishActivity(reactContext, BuzzBoosterActivityTag.CAMPAIGN)
        BuzzBooster.getInstance().finishActivity(reactContext, BuzzBoosterActivityTag.HOME)
        val params = Arguments.createMap()
        sendEvent(reactContext, "OptInMarketingCampaignMoveButtonClicked", params)
    }

    override fun onUserEvent(userEvent: UserEvent) {
        val params = Arguments.createMap().apply {
            putString("userEventName", userEvent.name)
            if (userEvent.values != null) { // null 처리 없어도 되나 iOS랑 맞추기 위해 처리
                putMap("userEventValues", Arguments.makeNativeMap(userEvent.values))
            }
        }
        sendEvent(reactContext, "onUserEventDidOccur", params)
    }

    override fun onNewIntent(intent: Intent) {
        currentActivity?.apply {
            if (BuzzBooster.hasUnhandledNotificationClick(intent)) {
                BuzzBooster.handleNotification(this, intent)
            }
        }
    }

    override fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) { }

    private fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap?) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun initAndroidApp(appKey: String) {
        val config = BuzzBoosterConfig(appKey)
        BuzzBooster.init(reactContext, config)
        BuzzBooster.getInstance().setOptInMarketingCampaignMoveButtonClickListener(this)
        BuzzBooster.getInstance().addUserEventListener(this)
    }

    @ReactMethod
    fun setUser(userMap: ReadableMap?) {
        if (userMap != null) {
            val map: HashMap<String, Any> = userMap.toHashMap()
            val userId = map["userId"] as String
            val optInMarketing = map["optInMarketing"] as Boolean?
            val properties = map["properties"] as HashMap<String, Any>?

            val userBuilder = BuzzBoosterUser.Builder()
                .setUserId(userId)

            if (optInMarketing != null){
                userBuilder.setOptInMarketing(optInMarketing)
            }
            properties?.forEach { entry ->
                userBuilder.addProperty(entry.key, entry.value)
            }
            BuzzBooster.setUser(userBuilder.build())
        } else {
            BuzzBooster.setUser(null)
        }
    }

    @ReactMethod
    fun setPushToken(token: String) {
        BuzzBooster.setFCMToken(token)
    }

    @ReactMethod
    fun sendEvent(eventName: String, eventValues: ReadableMap?) {
        if (eventValues == null) {
            BuzzBooster.getInstance().sendEvent(eventName)
        } else {
            try {
                val readableMap: HashMap<String, Any> = eventValues.toHashMap()
                val map = readableMap as Map<String, Any>
                BuzzBooster.getInstance().sendEvent(eventName, map)
            } catch (e : RuntimeException) {
                throw RuntimeException("${eventValues.toString()} is not validate format")
            }
        }
    }

    @ReactMethod
    fun showInAppMessage() {
        val activity = currentActivity
        if (activity != null) {
            BuzzBooster.getInstance().showInAppMessage(currentActivity!!)
        }
    }

    @ReactMethod
    fun showHome() {
        val activity = currentActivity
        if (activity != null) {
            BuzzBooster.getInstance().showHome(currentActivity!!)
        }
    }

    @ReactMethod
    fun showNaverPayExchange() {
        val activity = currentActivity
        if (activity != null) {
            BuzzBooster.getInstance().showNaverPayExchange(currentActivity!!)
        }
    }

    @ReactMethod
    fun showCampaignWithId(campaignId: String) {
        if (currentActivity != null) {
            BuzzBooster.getInstance().showCampaign(currentActivity!!, campaignId)
        }
    }

    @ReactMethod
    fun showCampaignWithType(campaignType: String) {
        try {
            val campaignTypeOrigin = CampaignType.valueOf(campaignType)
            if (currentActivity != null) {
                BuzzBooster.getInstance().showCampaign(currentActivity!!, campaignTypeOrigin)
            }
        } catch (e : RuntimeException) {
            throw RuntimeException("$campaignType is not a member of CampaignType")
        }
    }

    @ReactMethod
    fun setTheme(theme: String) {
        when(theme) {
            "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "System" -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
            else -> throw RuntimeException("$theme is not a member of Theme")
        }
    }

    @ReactMethod
    fun showPage(pageId: String) {
        if (currentActivity != null) {
            BuzzBooster.getInstance().showPage(currentActivity!!, pageId)
        }
    }

    @ReactMethod
    public fun addListener(eventName: String) { }

    @ReactMethod
    public fun removeListeners(count: Int) { }

    @ReactMethod
    public fun isBuzzBoosterNotification(data: ReadableMap, promise: Promise) {
        val readableMap: HashMap<String, Any> = data.toHashMap()
        val map = readableMap as Map<String, String>
        promise.resolve(BuzzBooster.isBuzzBoosterNotification(map))
    }

    @ReactMethod
    public fun handleNotification(data: ReadableMap) {
        val readableMap: HashMap<String, Any> = data.toHashMap()
        val map = readableMap as Map<String, String>
        BuzzBooster.handleNotification(map)
    }

    @ReactMethod
    public fun hasUnhandledNotificationClick(promise: Promise) {
        if (currentActivity != null) {
            promise.resolve(BuzzBooster.hasUnhandledNotificationClick(currentActivity!!))
        } else {
            promise.resolve(false)
        }
    }

    @ReactMethod
    public fun handleNotificationClick() {
        currentActivity?.let {
            BuzzBooster.handleNotification(it)
        }
    }

    @ReactMethod
    public fun postJavaScriptMessage(message: String) {
        currentActivity?.let {
            BuzzBoosterJavaScriptInterface(it).postMessage(message)
        }
    }
}
