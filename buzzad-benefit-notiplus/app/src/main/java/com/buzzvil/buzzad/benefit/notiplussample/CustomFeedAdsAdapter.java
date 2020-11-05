package com.buzzvil.buzzad.benefit.notiplussample;

import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.buzzvil.buzzad.benefit.presentation.feed.ad.DefaultAdsAdapter;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView;
import com.buzzvil.buzzad.benefit.presentation.notification.BuzzAdPush;
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult;

public class CustomFeedAdsAdapter extends DefaultAdsAdapter {

    private final NativeAdView.OnNativeAdEventListener listener = new NativeAdView.OnNativeAdEventListener() {
        @Override
        public void onImpressed(final @NonNull NativeAdView view, final @NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onImpressed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClicked(@NonNull NativeAdView view, @NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onClicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewardRequested(@NonNull NativeAdView view, @NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onRewardRequested", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewarded(@NonNull NativeAdView nativeAdView, @NonNull NativeAd nativeAd, @Nullable RewardResult rewardResult) {
            Toast.makeText(nativeAdView.getContext(), "onRewarded: " + rewardResult, Toast.LENGTH_SHORT).show();
            if (rewardResult == RewardResult.SUCCESS) {
                BuzzAdPush.showRewardNotification(nativeAdView.getContext(), App.getRewardNotificationConfig(), nativeAd.getAd().getReward());
            }
        }

        @Override
        public void onParticipated(final @NonNull NativeAdView view, final @NonNull NativeAd nativeAd) {
            Toast.makeText(view.getContext(), "onParticipated", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onBindViewHolder(NativeAdViewHolder holder, NativeAd nativeAd) {
        super.onBindViewHolder(holder, nativeAd);
        final NativeAdView view = (NativeAdView) holder.itemView;
        view.addOnNativeAdEventListener(listener);
    }

    @Override
    public NativeAdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }
}
