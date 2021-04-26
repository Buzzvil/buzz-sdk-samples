package com.buzzvil.sample.webfeed

import android.app.Application

class App : Application() {
    companion object {
        private const val appId = "123";
        const val WEBFEED_URL = "https://webfeed.buzzvil.com/app/${appId}"
    }
}