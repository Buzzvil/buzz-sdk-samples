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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.buzzvil.buzzad.benefit.core.models.Ad;
import com.buzzvil.buzzad.benefit.core.models.Creative;
import com.buzzvil.buzzad.benefit.presentation.media.CtaPresenter;
import com.buzzvil.buzzad.benefit.presentation.media.CtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdRewardResult;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView;
import com.buzzvil.buzzad.benefit.presentation.video.VideoErrorStatus;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

import java.util.ArrayList;
import java.util.List;

public class InterstitialAdView extends FrameLayout {

    private NativeAdView nativeAdView;
    private MediaView mediaView;
    private View titleLayout;
    private ImageView iconImageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
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
        this.titleLayout = findViewById(R.id.ad_title_layout);
        this.iconImageView = findViewById(R.id.ad_icon_image);
        this.titleTextView = findViewById(R.id.ad_title_text);
        this.descriptionTextView = findViewById(R.id.ad_description_text);
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
        mediaView.addOnMediaErrorListener(new MediaView.OnMediaErrorListener() {
            @Override
            public void onVideoError(@NonNull MediaView mediaView, @NonNull VideoErrorStatus videoErrorStatus, @Nullable String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(mediaView.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        titleTextView.setText(ad.getTitle());
        descriptionTextView.setText(ad.getDescription());
        Glide.with(this).load(ad.getIconUrl()).into(iconImageView);
        final CtaPresenter ctaPresenter = new CtaPresenter(ctaView);
        ctaPresenter.bind(nativeAd);

        final Creative.Type creativeType = ad.getCreative() == null ? null : ad.getCreative().getType();
        if (Creative.Type.IMAGE.equals(creativeType)) {
            titleLayout.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        } else {
            titleLayout.setVisibility(View.VISIBLE);
            descriptionTextView.setVisibility(View.VISIBLE);
        }

        final List<View> clickableViews = new ArrayList<>();
        clickableViews.add(ctaView);
        clickableViews.add(mediaView);
        clickableViews.add(titleLayout);
        clickableViews.add(descriptionTextView);

        nativeAdView.setMediaView(mediaView);
        nativeAdView.setClickableViews(clickableViews);
        nativeAdView.setNativeAd(nativeAd);

        nativeAdView.addOnNativeAdEventListener(new NativeAdView.OnNativeAdEventListener() {
            @Override
            public void onImpressed(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd) {

            }

            @Override
            public void onClicked(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd) {

            }

            @Override
            public void onRewardRequested(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd) {
                // Called when request has been sent to the server
            }

            @Override
            public void onRewarded(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd, @Nullable NativeAdRewardResult nativeAdRewardResult) {
                // Result of Reward Request can be found here
                // If the request result was successful, nativeAdRewardResult == NativeAdRewardResult.SUCCESS
                // If it was not successful, refer to the wiki page or NativeAdRewardResult class for Error cases.
            }

            @Override
            public void onParticipated(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd) {
                // Called when the Ad has been participated
                // Redraw UI with update Ad information here
                ctaPresenter.bind(nativeAd);
            }
        });
    }
}
