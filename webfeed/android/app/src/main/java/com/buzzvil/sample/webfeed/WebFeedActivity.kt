package com.buzzvil.sample.webfeed

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.*
import android.text.TextUtils
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.sample.webfeed.App.Companion.WEBFEED_SERVER_URL
import kotlinx.android.synthetic.main.activity_webfeed.*


class WebFeedActivity : AppCompatActivity() {
    companion object {
        private const val KEY_APP_ID = "KEY_APP_ID"
        private const val KEY_URL_PARAMS = "KEY_URL_PARAMS"

        fun getIntent(context: Context, appId: String, urlParams: String): Intent {
            return Intent(context, WebFeedActivity::class.java).also {
                it.putExtra(KEY_APP_ID, appId)
                it.putExtra(KEY_URL_PARAMS, urlParams)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webfeed)

        // Optional: Enable remote debug
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        // Mandatory

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportMultipleWindows(true)

        // Enable Local Storage
        webView.settings.domStorageEnabled = true

        // To preserve data < KITKAT
        webView.settings.databaseEnabled = true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.settings.databasePath =
                applicationContext.getDir("databases", Context.MODE_PRIVATE).path
        }

        // Open any link in current webview
        webView.webViewClient = WebViewClient()

        // Open any new tab link in the external browser
        webView.webChromeClient = object : WebChromeClient() {
            override fun onCreateWindow(
                view: WebView,
                dialog: Boolean,
                userGesture: Boolean,
                resultMsg: Message
            ): Boolean {
                val result = view.hitTestResult
                val url = if (result.type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    // In this case, result.extra is src of img tag
                    // To avoid opening images, obtain the link must be opened
                    val handler = Handler(Looper.getMainLooper())
                    val message = handler.obtainMessage()

                    webView.requestFocusNodeHref(message)
                    message.data.getString("url")
                } else {
                    result.extra
                } ?: return false

                Log.d("WEBVIEW", url)
                val context = view.context
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(browserIntent)
                return false
            }
        }


        // Open WebFeed
        val appId = intent.extras?.get(KEY_APP_ID) ?: ""
        val urlParams = (intent.extras?.get(KEY_URL_PARAMS) as String?).let {
            if (TextUtils.isEmpty(it)) {
                ""
            } else {
                "?$it"
            }
        }
        val webFeedUrl = "${WEBFEED_SERVER_URL}${appId}/feed${urlParams}"

        webView.loadUrl(webFeedUrl)
        Log.d("WEBVIEW", webFeedUrl)
    }
}