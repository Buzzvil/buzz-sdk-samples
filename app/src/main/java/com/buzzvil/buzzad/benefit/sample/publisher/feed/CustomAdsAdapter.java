package com.buzzvil.buzzad.benefit.sample.publisher.feed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buzzvil.buzzad.benefit.core.models.Ad;
import com.buzzvil.buzzad.benefit.presentation.feed.ad.AdsAdapter;
import com.buzzvil.buzzad.benefit.presentation.media.CtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView;
import com.buzzvil.buzzad.benefit.presentation.video.VideoErrorStatus;
import com.buzzvil.buzzad.benefit.sample.publisher.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class CustomAdsAdapter extends AdsAdapter<AdsAdapter.NativeAdViewHolder> {

    @Override
    public AdsAdapter.NativeAdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final NativeAdView feedAdView = (NativeAdView) inflater.inflate(R.layout.bz_view_feed_ad, parent, false);
        return new NativeAdViewHolder(feedAdView);
    }

    @Override
    public void onBindViewHolder(NativeAdViewHolder holder, NativeAd nativeAd) {
        final NativeAdView view = (NativeAdView) holder.itemView;

        final Ad ad = nativeAd.getAd();
        final MediaView mediaView = view.findViewById(R.id.mediaView);
        final TextView titleView = view.findViewById(R.id.textTitle);
        final ImageView iconView = view.findViewById(R.id.imageIcon);
        final TextView descriptionView = view.findViewById(R.id.textDescription);
        final CtaView ctaView = view.findViewById(R.id.ctaView);

        if (mediaView != null) {
            mediaView.setCreative(ad.getCreative());
            mediaView.setOnMediaEventListener(new MediaView.OnMediaEventListener() {
                @Override
                public void onVideoError(@NonNull Context context, @NonNull VideoErrorStatus errorStatus, @Nullable String errorMessage) {
                    if (errorMessage != null) {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if (titleView != null) {
            titleView.setText(ad.getTitle());
        }
        if (iconView != null) {
            ImageLoader.getInstance().displayImage(ad.getIconUrl(), iconView);
        }
        if (descriptionView != null) {
            descriptionView.setText(ad.getDescription());
        }

        if (ctaView != null) {
            if (nativeAd.isParticipated()) {
                ctaView.setParticipated(true);
                ctaView.setRewardText(null);
                ctaView.setCallToActionText(view.getResources().getString(R.string.bz_cta_done));
            } else {
                ctaView.setParticipated(false);
                if (ad.getReward() > 0) {
                    ctaView.setRewardText(String.format(Locale.US, "+%d", ad.getReward()));
                } else {
                    ctaView.setRewardText(null);
                }
                ctaView.setCallToActionText(ad.getCallToAction());
            }
        }

        final Collection<View> clickableViews = Arrays.asList((View) ctaView);

        view.setNativeAd(nativeAd);
        view.setMediaView(mediaView);
        view.setClickableViews(clickableViews);
    }
}
