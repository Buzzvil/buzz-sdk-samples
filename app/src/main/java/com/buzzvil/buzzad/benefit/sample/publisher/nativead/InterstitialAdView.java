package com.buzzvil.buzzad.benefit.sample.publisher.nativead;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.buzzvil.buzzad.benefit.core.models.Ad;
import com.buzzvil.buzzad.benefit.presentation.media.CtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InterstitialAdView extends FrameLayout {

    private NativeAdView nativeAdView;
    private MediaView mediaView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView iconImageView;
    private CtaView ctaView;

    public interface OnCloseClickListener {
        void onCloseClick();
    }

    private OnCloseClickListener onCloseClickListener;

    public InterstitialAdView(@NonNull Context context) {
        this(context, null);
    }

    public InterstitialAdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_interstitial_ad, this);
        this.nativeAdView = findViewById(R.id.native_ad_view);
        this.mediaView = findViewById(R.id.ad_media_view);
        this.titleTextView = findViewById(R.id.ad_title_text);
        this.descriptionTextView = findViewById(R.id.ad_description_text);
        this.iconImageView = findViewById(R.id.ad_icon_image);
        this.ctaView = findViewById(R.id.ad_cta_view);
        findViewById(R.id.ad_close_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCloseClickListener != null) {
                    onCloseClickListener.onCloseClick();
                }
            }
        });
    }

    public void setOnCloseClickListener(OnCloseClickListener onCloseClickListener) {
        this.onCloseClickListener = onCloseClickListener;
    }

    public void populateAd(final NativeAd nativeAd) {
        final Ad ad = nativeAd.getAd();

        mediaView.setCreative(ad.getCreative());
        titleTextView.setText(ad.getTitle());
        descriptionTextView.setText(ad.getDescription());
        Glide.with(this).load(ad.getIconUrl()).into(iconImageView);
        ctaView.setRewardText(String.format(Locale.US, "+%d", ad.getReward()));
        ctaView.setCallToActionText(ad.getCallToAction());

        final List<View> clickableViews = new ArrayList<>();
        clickableViews.add(ctaView);
        clickableViews.add(mediaView);
        clickableViews.add(titleTextView);
        clickableViews.add(descriptionTextView);

        nativeAdView.setMediaView(mediaView);
        nativeAdView.setClickableViews(clickableViews);
        nativeAdView.setNativeAd(nativeAd);

        nativeAdView.setOnNativeAdEventListener(new NativeAdView.OnNativeAdEventListener() {
            @Override
            public void onImpressed(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd) {

            }

            @Override
            public void onClicked(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd) {

            }

            @Override
            public void onParticipated(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd) {
                ctaView.setParticipated(true);
                ctaView.setRewardText(null);
            }
        });
    }
}
