package com.buzzvil.buzzad.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val feature = MutableLiveData<Feature>()

    fun showFeed() {
        feature.value = Feature.FEED
    }

    fun showPop() {
        feature.value = Feature.POP
    }

    fun showInAppPop() {
        feature.value = Feature.IN_APP_POP
    }

    fun showPush() {
        feature.value = Feature.PUSH
    }

    fun showInterstitial() {
        feature.value = Feature.INTERSTITIAL
    }

    fun showNative() {
        feature.value = Feature.NATIVE
    }

    fun resetAll() {
        feature.value = Feature.RESET_ALL
    }
}
