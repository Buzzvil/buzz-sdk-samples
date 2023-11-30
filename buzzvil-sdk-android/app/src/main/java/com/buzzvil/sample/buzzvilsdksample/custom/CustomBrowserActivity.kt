package com.buzzvil.sample.buzzvilsdksample.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buzzvil.buzzad.browser.BuzzAdBrowser
import com.buzzvil.buzzad.browser.BuzzAdBrowser.OnBrowserEventListener
import com.buzzvil.buzzad.browser.BuzzAdBrowserFragment
import com.buzzvil.sample.buzzvilsdksample.R

class CustomBrowserActivity : AppCompatActivity() {
    companion object {
        const val KEY_URL = "com.sample.KEY_URL"
        private lateinit var fragment: BuzzAdBrowserFragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_browser)

        // URL을 KY로 하여 WebView를 가지고있는 Fragment를 받아와 사용합니다.
        fragment = BuzzAdBrowser.getInstance(this).getFragment(intent.getStringExtra(KEY_URL))
        supportFragmentManager.beginTransaction().replace(R.id.browserContainer, fragment).commit()
        val webView = fragment.webView

        // Browser의 이벤트를 받을 수 있습니다. DeepLink가 열렸을 경우, Browser를 닫아주어야 빈 페이지가 보여지는 현상을 방지할 수 있습니다.
        BuzzAdBrowser.getInstance(this).addBrowserEventListener(object : OnBrowserEventListener() {
            override fun onDeepLinkOpened() {
                super.onDeepLinkOpened()
                finish()
            }
        })
    }

    override fun onBackPressed() {
        val webView = fragment.webView
        if (webView != null && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
