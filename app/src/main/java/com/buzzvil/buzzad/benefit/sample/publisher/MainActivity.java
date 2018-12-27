package com.buzzvil.buzzad.benefit.sample.publisher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdLoader;
import com.buzzvil.buzzad.benefit.sample.publisher.nativead.InterstitialAdView;
import com.buzzvil.buzzad.benefit.sample.publisher.nativead.PagerAdsView;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private Button interstitialAdButton;
    private Button nativeAdsButton;
    private Button feedButton;
    private ProgressBar progressBar;

    private View adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interstitialAdButton = findViewById(R.id.interstitial_ad_button);
        interstitialAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAd();
            }
        });

        nativeAdsButton = findViewById(R.id.native_ads_button);
        nativeAdsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAds();
            }
        });

        feedButton = findViewById(R.id.feed_button);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FeedConfig feedConfig = new FeedConfig.Builder(App.UNIT_ID_FEED)
                        .title("BuzzAdBenefit Feed")
                        .primaryColor("#1290FF")
                        .build();
                final FeedHandler feedHandler = new FeedHandler(feedConfig);
                feedHandler.startFeedActivity(MainActivity.this);
            }
        });

        progressBar = findViewById(R.id.progress_bar);
    }

    private void loadNativeAd() {
        progressBar.setVisibility(View.VISIBLE);
        final NativeAdLoader loader = new NativeAdLoader(App.UNIT_ID_NATIVE_AD);
        loader.loadAd(new NativeAdLoader.OnAdLoadedListener() {
            @Override
            public void onLoadError(@NonNull AdError adError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load a native ad.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(@NonNull NativeAd nativeAd) {
                progressBar.setVisibility(View.GONE);

                InterstitialAdView interstitialAdView = new InterstitialAdView(MainActivity.this);
                interstitialAdView.setOnCloseClickListener(new InterstitialAdView.OnCloseClickListener() {
                    @Override
                    public void onCloseClick() {
                        closeAdView();
                    }
                });
                interstitialAdView.populateAd(nativeAd);
                MainActivity.this.adView = interstitialAdView;
                ((ViewGroup) findViewById(android.R.id.content)).addView(adView);
            }
        });
    }

    private void loadNativeAds() {
        progressBar.setVisibility(View.VISIBLE);
        final NativeAdLoader loader = new NativeAdLoader(App.UNIT_ID_NATIVE_AD);
        loader.loadAds(new NativeAdLoader.OnAdsLoadedListener() {
            @Override
            public void onLoadError(@NonNull AdError adError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load native ads.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdsLoaded(@NonNull Collection<NativeAd> collection) {
                progressBar.setVisibility(View.GONE);

                PagerAdsView pagerAdsView = new PagerAdsView(MainActivity.this);
                pagerAdsView.setOnCloseClickListener(new PagerAdsView.OnCloseClickListener() {
                    @Override
                    public void onCloseClick() {
                        closeAdView();
                    }
                });
                pagerAdsView.setNativeAds(collection);
                MainActivity.this.adView = pagerAdsView;
                ((ViewGroup) findViewById(android.R.id.content)).addView(adView);
            }
        }, 5);
    }

    private boolean closeAdView() {
        if (adView != null) {
            ((ViewGroup) adView.getParent()).removeView(adView);
            adView = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (!closeAdView()) {
            super.onBackPressed();
        }
    }
}
