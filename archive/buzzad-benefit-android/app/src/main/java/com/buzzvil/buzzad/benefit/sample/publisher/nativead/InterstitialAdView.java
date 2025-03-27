package com.buzzvil.buzzad.benefit.sample.publisher.nativead;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.buzzvil.buzzad.benefit.presentation.media.CtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdEventListener;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdViewBinder;
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult;
import com.buzzvil.buzzad.benefit.presentation.video.VideoErrorStatus;
import com.buzzvil.buzzad.benefit.presentation.video.VideoEventListener;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

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
        final NativeAdViewBinder viewBinder = new NativeAdViewBinder.Builder(nativeAdView, mediaView)
                .titleTextView(titleTextView)
                .descriptionTextView(descriptionTextView)
                .iconImageView(iconImageView)
                .ctaView(ctaView)
                .build();
        viewBinder.bind(nativeAd);

        nativeAd.addNativeAdEventListener(new NativeAdEventListener() {
            @Override
            public void onImpressed(@NonNull NativeAd nativeAd) {
                Toast.makeText(getContext(), "onImpressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClicked(@NonNull NativeAd nativeAd) {
                Toast.makeText(getContext(), "onClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardRequested(@NonNull NativeAd nativeAd) {
                // Called when request has been sent to the server
                Toast.makeText(getContext(), "onRewardRequested", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewarded(@NonNull NativeAd nativeAd, @Nullable RewardResult rewardResult) {
                // Result of Reward Request can be found here
                // If the request result was successful, nativeAdRewardResult == NativeAdRewardResult.SUCCESS
                // If it was not successful, refer to the wiki page or NativeAdRewardResult class for Error cases.
                Toast.makeText(getContext(), "onRewarded: " + rewardResult, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onParticipated(@NonNull NativeAd nativeAd) {
                // Called when the Ad has been participated
                // Redraw UI with update Ad information here
                Toast.makeText(getContext(), "onParticipated", Toast.LENGTH_SHORT).show();
                // ctaPresenter.bind(nativeAd);
            }
        });
        mediaView.setVideoEventListener(new VideoEventListener() {
            @Override
            public void onVideoStarted() {

            }

            @Override
            public void onResume() {
            }

            @Override
            public void onPause() {
            }

            @Override
            public void onReplay() {
            }

            @Override
            public void onLanding() {
            }

            @Override
            public void onError(@NonNull VideoErrorStatus videoErrorStatus, @Nullable String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(mediaView.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onVideoEnded() {
            }
        });
    }
}
