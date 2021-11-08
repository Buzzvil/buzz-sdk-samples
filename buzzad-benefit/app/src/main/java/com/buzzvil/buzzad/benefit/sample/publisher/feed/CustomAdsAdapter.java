package com.buzzvil.buzzad.benefit.sample.publisher.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.buzzvil.buzzad.benefit.presentation.feed.ad.AdsAdapter;
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

public class CustomAdsAdapter extends AdsAdapter<AdsAdapter.NativeAdViewHolder> {
    private NativeAdView view;

    private final NativeAdEventListener nativeAdEventListener = new NativeAdEventListener() {
        @Override
        public void onImpressed(@NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onImpressed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClicked(@NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onClicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewardRequested(@NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onRewardRequested", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewarded(@NonNull NativeAd nativeAd, @Nullable RewardResult rewardResult) {
            Toast.makeText(view.getContext(), "onRewarded: " + rewardResult, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onParticipated(@NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onParticipated", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public AdsAdapter.NativeAdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final NativeAdView feedAdView = (NativeAdView) inflater.inflate(R.layout.bz_view_feed_ad, parent, false);
        return new NativeAdViewHolder(feedAdView);
    }

    @Override
    public void onBindViewHolder(NativeAdViewHolder holder, NativeAd nativeAd) {
        this.view = (NativeAdView) holder.itemView;
        nativeAd.addNativeAdEventListener(nativeAdEventListener);

        final MediaView mediaView = view.findViewById(R.id.mediaView);
        final LinearLayout titleLayout = view.findViewById(R.id.titleLayout);
        final TextView titleView = view.findViewById(R.id.textTitle);
        final ImageView iconView = view.findViewById(R.id.imageIcon);
        final TextView descriptionView = view.findViewById(R.id.textDescription);
        final CtaView ctaView = view.findViewById(R.id.ctaView);

        final NativeAdViewBinder viewBinder = new NativeAdViewBinder.Builder(view, mediaView)
                .titleTextView(titleView)
                .descriptionTextView(descriptionView)
                .iconImageView(iconView)
                .ctaView(ctaView)
                .build();
        viewBinder.bind(nativeAd);

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
