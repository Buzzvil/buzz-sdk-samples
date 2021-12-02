package com.buzzvil.buzzad.benefit.sample.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed;
import com.buzzvil.buzzad.benefit.presentation.interstitial.BuzzAdInterstitial;
import com.buzzvil.buzzad.benefit.presentation.interstitial.BuzzAdInterstitialTheme;
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdListener;
import com.buzzvil.buzzad.benefit.presentation.nativead.BuzzAdNative;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdRequest;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdsRequest;
import com.buzzvil.buzzad.benefit.sample.publisher.nativead.InterstitialAdView;
import com.buzzvil.buzzad.benefit.sample.publisher.nativead.PagerAdsView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button nativeAdButton;
    private Button nativeAdsButton;
    private Button feedButton;
    private Button feedWithTabButton;
    private Button webToFeedButton;
    private Button feedEntryPointButton;
    private ProgressBar progressBar;
    private Spinner interstitialTypeSpinner;
    private Spinner interstitialCustomizationSpinner;
    private Button interstitialAdButton;

    private View adView;

    private BroadcastReceiver sessionReadyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("SessionKey", "Session is Ready. Ads can be loaded now.");
            Toast.makeText(MainActivity.this, "Session is Ready. Ads can be loaded now.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerSessionReadyReceiver();

        this.nativeAdButton = findViewById(R.id.native_ad_button);
        nativeAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAd();
            }
        });

        this.nativeAdsButton = findViewById(R.id.native_ads_button);
        nativeAdsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAds();
            }
        });

        this.feedButton = findViewById(R.id.feed_button);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BuzzAdFeed.Builder().build().show(MainActivity.this);
            }
        });

        this.webToFeedButton = findViewById(R.id.web_to_feed);
        webToFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebToFeedActivity.startActivity(MainActivity.this);
            }
        });

        this.feedEntryPointButton = findViewById(R.id.feed_entry_point);
        feedEntryPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedEntryPointActivity.startActivity(MainActivity.this);
            }
        });

        this.progressBar = findViewById(R.id.progress_bar);

        this.interstitialTypeSpinner = findViewById(R.id.type_spinner);
        this.interstitialCustomizationSpinner = findViewById(R.id.customization_spinner);
        this.interstitialAdButton = findViewById(R.id.interstitial_ad_button);
        interstitialAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitialAd();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterSessionReadyReceiver();
    }

    private void loadNativeAd() {
        progressBar.setVisibility(View.VISIBLE);

        final NativeAdRequest nativeAdRequest = new NativeAdRequest.Builder().build();
        final BuzzAdNative buzzAdNative = new BuzzAdNative(App.UNIT_ID_NATIVE_AD);
        buzzAdNative.loadAd(nativeAdRequest, new BuzzAdNative.AdLoadListener() {
            @Override
            public void onAdLoaded(@NonNull NativeAd nativeAd) {
                progressBar.setVisibility(View.GONE);

                final InterstitialAdView interstitialAdView = new InterstitialAdView(MainActivity.this);
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

            @Override
            public void onLoadFailed(@NonNull AdError adError) {
                // 할당된 광고가 없으면 호출됩니다.
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load native ad: " + adError.getErrorType().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNativeAds() {
        progressBar.setVisibility(View.VISIBLE);

        final NativeAdsRequest nativeAdsRequest = new NativeAdsRequest.Builder()
                .adCount(5)
                .build();
        final BuzzAdNative buzzAdNative = new BuzzAdNative(App.UNIT_ID_NATIVE_AD);
        buzzAdNative.loadAds(nativeAdsRequest, new BuzzAdNative.AdsLoadListener() {
            @Override
            public void onAdsLoaded(@NonNull List<NativeAd> ads) {
                progressBar.setVisibility(View.GONE);

                final PagerAdsView pagerAdsView = new PagerAdsView(MainActivity.this);
                pagerAdsView.setOnCloseClickListener(new PagerAdsView.OnCloseClickListener() {
                    @Override
                    public void onCloseClick() {
                        closeAdView();
                    }
                });
                pagerAdsView.setNativeAds(ads);
                MainActivity.this.adView = pagerAdsView;
                ((ViewGroup) findViewById(android.R.id.content)).addView(adView);
            }

            @Override
            public void onLoadFailed(@NonNull AdError adError) {
                // 할당된 광고가 없으면 호출됩니다.
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load native ads: " + adError.getErrorType().toString(), Toast.LENGTH_SHORT).show();
            }
        });
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

    public void showInterstitialAd() {
        final int selectedType = interstitialTypeSpinner.getSelectedItemPosition();
        final BuzzAdInterstitial.Builder buzzAdInterstitialBuilder = new BuzzAdInterstitial.Builder(App.UNIT_ID_NATIVE_AD);

        final int customizationType = interstitialCustomizationSpinner.getSelectedItemPosition();
        switch (customizationType) {
            case 1: {
                final BuzzAdInterstitialTheme theme = BuzzAdInterstitialTheme.getDefault()
                        .backgroundColor(android.R.color.background_dark)
                        .textColor(android.R.color.white);
                buzzAdInterstitialBuilder.theme(theme);
                break;
            }
            case 2: {
                final BuzzAdInterstitialTheme theme = BuzzAdInterstitialTheme.getDefault()
                        .ctaBackgroundSelector(R.drawable.bg_interstitial_selector) // CTA 버튼 배경
                        .ctaTextColorSelector(android.R.color.white) // CTA 텍스트 색상
                        .ctaTextSize(R.dimen.interstitial_cta_text_size) // CTA 텍스트 크기
                        .rewardIcon(R.drawable.ic_reward_gray) // CTA 리워드 아이콘
                        .participatedIcon(R.drawable.ic_participated); // 참여 완료 후 CTA 리워드 아이콘

                buzzAdInterstitialBuilder.theme(theme);
                break;
            }
            case 0:
            default:
                break;
        }

        BuzzAdInterstitial buzzAdInterstitial = null;
        if (selectedType == 0 || selectedType == Spinner.INVALID_POSITION) {
            buzzAdInterstitial = buzzAdInterstitialBuilder.buildDialog();
        } else {
            buzzAdInterstitial = buzzAdInterstitialBuilder.buildBottomSheet();
        }

        BuzzAdInterstitial finalBuzzAdInterstitial = buzzAdInterstitial;
        buzzAdInterstitial.load(new InterstitialAdListener() {
            @Override
            public void onAdLoaded() {
                finalBuzzAdInterstitial.show(MainActivity.this);
            }

            @Override
            public void onAdLoadFailed(@Nullable AdError adError) {
                // 광고 호출 실패
                Toast.makeText(MainActivity.this, "Failed to load ads: " + adError.getErrorType().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                // Interstitial 지면이 종료됨
            }
        });
    }
    
    private void registerSessionReadyReceiver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(sessionReadyReceiver, BuzzAdBenefit.getSessionReadyIntentFilter());
    }

    private void unregisterSessionReadyReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(sessionReadyReceiver);
    }
}
