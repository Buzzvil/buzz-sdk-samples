package com.buzzvil.buzzad.benefit.sample.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler;
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdConfig;
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdHandler;
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdHandlerFactory;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdLoader;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomAdsAdapter;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomFeedHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomFeedToolbarHolder;
import com.buzzvil.buzzad.benefit.sample.publisher.nativead.InterstitialAdView;
import com.buzzvil.buzzad.benefit.sample.publisher.nativead.PagerAdsView;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private Button nativeAdButton;
    private Button nativeAdsButton;
    private Button feedButton;
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
                final FeedConfig feedConfig = new FeedConfig.Builder(App.UNIT_ID_FEED)
                        .adsAdapterClass(CustomAdsAdapter.class)
                        .feedToolbarHolderClass(CustomFeedToolbarHolder.class)
                        .feedHeaderViewAdapterClass(CustomFeedHeaderViewAdapter.class)
                        .imageTypeEnabled(true)
                        .build();
                final FeedHandler feedHandler = new FeedHandler(feedConfig);
                feedHandler.startFeedActivity(MainActivity.this);
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
        final NativeAdLoader loader = new NativeAdLoader(App.UNIT_ID_NATIVE_AD);
        loader.loadAd(new NativeAdLoader.OnAdLoadedListener() {
            @Override
            public void onLoadError(@NonNull AdError adError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load native ad: " + adError.getErrorType().toString(), Toast.LENGTH_SHORT).show();
            }

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
        }, true);
    }

    private void loadNativeAds() {
        progressBar.setVisibility(View.VISIBLE);
        final NativeAdLoader loader = new NativeAdLoader(App.UNIT_ID_NATIVE_AD);
        loader.loadAds(new NativeAdLoader.OnAdsLoadedListener() {
            @Override
            public void onLoadError(@NonNull AdError adError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load native ads: " + adError.getErrorType().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdsLoaded(@NonNull Collection<NativeAd> collection) {
                progressBar.setVisibility(View.GONE);

                final PagerAdsView pagerAdsView = new PagerAdsView(MainActivity.this);
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

    public void showInterstitialAd() {
        final int selectedType = interstitialTypeSpinner.getSelectedItemPosition();
        final InterstitialAdHandler.OnInterstitialAdEventListener eventListener = new InterstitialAdHandler.OnInterstitialAdEventListener() {
            @Override
            public void onAdLoadFailed(@NonNull AdError adError) {
                Toast.makeText(MainActivity.this, "Failed to load ads: " + adError.getErrorType().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded() {

            }
        };

        final InterstitialAdHandler.Type interstitialType = selectedType == 0 || selectedType == Spinner.INVALID_POSITION
                ? InterstitialAdHandler.Type.Dialog
                : InterstitialAdHandler.Type.BottomSheet;

        final InterstitialAdHandler interstitialAdHandler = new InterstitialAdHandlerFactory().create(App.UNIT_ID_NATIVE_AD, interstitialType);

        final int customizationType = interstitialCustomizationSpinner.getSelectedItemPosition();
        switch (customizationType) {
            case 1:
                interstitialAdHandler.show(this,
                        new InterstitialAdConfig.Builder()
                                .topIcon(R.drawable.ic_gift_green)
//                                .topIcon(ContextCompat.getDrawable(this, R.drawable.ic_gift_green))
                                .build(),
                        eventListener);
                return;
            case 2:
                interstitialAdHandler.show(this,
                        new InterstitialAdConfig.Builder()
                                .layoutBackgroundColor(android.R.color.background_dark)
                                .textColor(android.R.color.white)
                                .titleText(getString(R.string.bz_interstitial_title))
                                .closeText(getString(R.string.bz_interstitial_close)) // For Dialog only
                                .build(),
                        eventListener);
                return;
            case 3:
                interstitialAdHandler.show(this,
                        new InterstitialAdConfig.Builder()
                                .ctaViewBackgroundColorList(getCtaViewBackgroundColorList())
                                .ctaViewTextColor(getCtaViewTextColorList())
                                .ctaRewardDrawable(R.drawable.ic_reward_gray)
                                .ctaParticipatedDrawable(R.drawable.ic_participated)
                                .build(),
                        eventListener);
                return;
            case 0:
            default:
                // Default Dialog or BottomSheet does not have title and close button text.
                // It is recommended to provide the strings with translations.
                interstitialAdHandler.show(this, null, eventListener);
                break;
        }
    }

    private ColorStateList getCtaViewBackgroundColorList() {
        final int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{android.R.attr.state_pressed}
        };

        final int[] colors = new int[]{
                ContextCompat.getColor(this, android.R.color.holo_blue_light),  // enabled
                ContextCompat.getColor(this, android.R.color.holo_blue_dark)   // pressed
        };

        return new ColorStateList(states, colors);

    }

    private ColorStateList getCtaViewTextColorList() {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{android.R.attr.state_pressed}
        };

        int[] colors = new int[]{
                Color.WHITE,  // enabled
                Color.WHITE   // pressed
        };

        return new ColorStateList(states, colors);

    }

    private void registerSessionReadyReceiver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(sessionReadyReceiver, BuzzAdBenefit.getSessionReadyIntentFilter());
    }

    private void unregisterSessionReadyReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(sessionReadyReceiver);
    }
}
