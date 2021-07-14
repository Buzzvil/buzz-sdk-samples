package com.buzzvil.buzzad.benefit.sample.publisher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler;

public class WebToFeedActivity extends AppCompatActivity implements BuzzAdBenefitJSBridge {
    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, WebToFeedActivity.class);
        context.startActivity(intent);
    }

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_to_feed);
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.addJavascriptInterface(this, "BuzzAdBenefitJSBridge");
        webView.loadUrl("file:///android_asset/WebToFeedSample.html");
    }

    @Override
    @JavascriptInterface
    public void showFeed() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                new FeedHandler(getApplicationContext(), App.UNIT_ID_FEED).startFeedActivity(WebToFeedActivity.this);
            }
        });
    }
}
