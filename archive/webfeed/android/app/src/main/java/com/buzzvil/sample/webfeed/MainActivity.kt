package com.buzzvil.sample.webfeed

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.buzzvil.sample.webfeed.App.Companion.WEBFEED_SERVER_URL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webFeedServerUrl.text = WEBFEED_SERVER_URL

        urlParamsView.doAfterTextChanged { text ->
            feedPath.text = "/feed" + if (TextUtils.isEmpty(text)) {
                ""
            } else {
                "?"
            }
        }

        webFeedButton.setOnClickListener {
            startActivity(
                WebFeedActivity.getIntent(
                    this,
                    appIdView.text.toString(),
                    urlParamsView.text.toString()
                )
            )
        }
    }
}