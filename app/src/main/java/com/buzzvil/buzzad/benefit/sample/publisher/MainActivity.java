package com.buzzvil.buzzad.benefit.sample.publisher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.core.models.Ad;
import com.buzzvil.buzzad.benefit.ui.feed.FeedHandler;
import com.buzzvil.buzzad.benefit.ui.media.CtaView;
import com.buzzvil.buzzad.benefit.ui.media.MediaView;
import com.buzzvil.buzzad.benefit.ui.nativead.NativeAd;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button nativeAdButton;
    private Button feedButton;
    private ProgressBar progressBar;
    private View interstitialView;
    private NativeAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nativeAdButton = findViewById(R.id.native_ad_button);
        nativeAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAd();
            }
        });

        feedButton = findViewById(R.id.feed_button);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FeedHandler feedHandler = new FeedHandler();
                feedHandler.startFeedActivity(MainActivity.this);
            }
        });

        progressBar = findViewById(R.id.progress_bar);
    }

    private void loadNativeAd() {
        progressBar.setVisibility(View.VISIBLE);
        nativeAd = new NativeAd(this, App.UNIT_ID_NATIVE_AD);
        nativeAd.setAdListener(new NativeAd.OnAdEventListener() {
            @Override
            public void onLoadError(@NonNull AdError adError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load native ads.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(@NonNull Ad ad) {
                progressBar.setVisibility(View.GONE);
                interstitialView = populateAd(ad);
                interstitialView.findViewById(R.id.ad_close_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closeInterstitialView();
                    }
                });
                ((ViewGroup) findViewById(android.R.id.content)).addView(interstitialView);
            }

            @Override
            public void onAdParticipated(@NonNull Ad ad) {
                final CtaView ctaView = interstitialView.findViewById(R.id.ad_cta_view);
                ctaView.setParticipated(true);
                ctaView.setRewardText(null);
            }
        });
        nativeAd.loadAd();
    }

    private View populateAd(final Ad ad) {
        nativeAd.unregisterView();

        final View interstitialView = LayoutInflater.from(this).inflate(R.layout.view_interstitial_ad, null, false);
        final View adView = interstitialView.findViewById(R.id.ad_view);
        final MediaView mediaView = interstitialView.findViewById(R.id.ad_media_view);
        final TextView titleTextView = interstitialView.findViewById(R.id.ad_title_text);
        final TextView descriptionTextView = interstitialView.findViewById(R.id.ad_description_text);
        final ImageView iconImageView = interstitialView.findViewById(R.id.ad_icon_image);
        final CtaView ctaView = interstitialView.findViewById(R.id.ad_cta_view);

        // 2) Assign ad properties.
        mediaView.setCreative(ad.getCreative());
        titleTextView.setText(ad.getTitle());
        descriptionTextView.setText(ad.getDescription());
        Glide.with(this).load(ad.getIconUrl()).into(iconImageView);
        ImageLoader.getInstance().displayImage(ad.getIconUrl(), iconImageView);
        ctaView.setRewardText(String.format(Locale.US, "+%d", ad.getReward()));
        ctaView.setCallToActionText(ad.getCallToAction());

        // 3) Create a list of clickable views and register it.
        final List<View> clickableViews = new ArrayList<>();
        clickableViews.add(ctaView);
        // optional
        clickableViews.add(mediaView);
        clickableViews.add(titleTextView);
        clickableViews.add(descriptionTextView);

        // 4) Call nativeAd.registerViewForInteraction().
        nativeAd.registerViewForInteraction(adView, mediaView, clickableViews);
        return interstitialView;
    }

    private boolean closeInterstitialView() {
        if (interstitialView != null) {
            nativeAd.unregisterView();
            nativeAd = null;
            ((ViewGroup) interstitialView.getParent()).removeView(interstitialView);
            interstitialView = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (!closeInterstitialView()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (nativeAd != null) {
            nativeAd.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (nativeAd != null) {
            nativeAd.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (nativeAd != null) {
            nativeAd.destroy();
        }
    }
}
