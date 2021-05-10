package com.buzzvil.buzzad.benefit.sample.publisher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler;

public class WebViewActivity extends AppCompatActivity implements BuzzAdBenefitJSBridge {
    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.addJavascriptInterface(this, "BuzzAdBenefit");
        webView.loadUrl("file:///android_asset/webToNativeSample.html");   // now it will not fail here
    }

    @Override
    @JavascriptInterface
    public void showFeed() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                new FeedHandler(getApplicationContext(), "YOUR_FEED_UNIT_ID").startFeedActivity(WebViewActivity.this);
            }
        });
    }
}
