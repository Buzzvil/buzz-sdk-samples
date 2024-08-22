package com.buzzvil.buzzad.benefit.sample.publisher.nativead;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.buzzvil.buzzad.benefit.presentation.media.CtaView;
import com.buzzvil.buzzad.benefit.presentation.media.DefaultCtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdEventListener;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdViewBinder;
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult;
import com.buzzvil.buzzad.benefit.presentation.video.VideoErrorStatus;
import com.buzzvil.buzzad.benefit.presentation.video.VideoEventListener;
import com.buzzvil.buzzad.benefit.sample.publisher.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PagerAdsView extends FrameLayout {
    private static final int PAGE_MARGIN_DP = 16;
    private static final int PAGER_PADDING_DP = 24;

    private ViewPager pager;
    private TabLayout tabLayoutDots;
    private NativeAdsAdapter nativeAdsAdapter;

    public interface OnCloseClickListener {
        void onCloseClick();
    }

    private OnCloseClickListener onCloseClickListener;

    public PagerAdsView(@NonNull Context context) {
        this(context, null);
    }

    public PagerAdsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_pager_ads, this);
        this.pager = findViewById(R.id.pager);
        this.tabLayoutDots = findViewById(R.id.tab_layout_dots);
        findViewById(R.id.close_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCloseClickListener != null) {
                    onCloseClickListener.onCloseClick();
                }
            }
        });
        pager.setClipToPadding(false);
        final int pagerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PAGER_PADDING_DP, getResources().getDisplayMetrics());
        pager.setPadding(pagerPadding, 0, pagerPadding, 0);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PAGE_MARGIN_DP, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabLayoutDots.setupWithViewPager(pager);
    }

    public void setOnCloseClickListener(OnCloseClickListener onCloseClickListener) {
        this.onCloseClickListener = onCloseClickListener;
    }

    public void setNativeAds(Collection<NativeAd> nativeAds) {
        this.nativeAdsAdapter = new NativeAdsAdapter(nativeAds);
        pager.setAdapter(nativeAdsAdapter);
    }

    private static class NativeAdsAdapter extends PagerAdapter {
        private final List<NativeAd> nativeAds;

        NativeAdsAdapter(Collection<NativeAd> nativeAds) {
            this.nativeAds = new ArrayList<>(nativeAds);
        }

        @Override
        public int getCount() {
            return nativeAds.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = populateAd(container.getContext(), nativeAds.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        private View populateAd(final Context context, final NativeAd nativeAd) {
            final NativeAdView nativeAdView = (NativeAdView) LayoutInflater.from(context).inflate(R.layout.view_native_ad_item, null, false);
            final MediaView mediaView = nativeAdView.findViewById(R.id.ad_media_view);
            final TextView titleTextView = nativeAdView.findViewById(R.id.ad_title_text);
            final TextView descriptionTextView = nativeAdView.findViewById(R.id.ad_description_text);
            final ImageView iconImageView = nativeAdView.findViewById(R.id.ad_icon_image);
            final DefaultCtaView ctaView = nativeAdView.findViewById(R.id.ad_cta_view);

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
                    Toast.makeText(nativeAdView.getContext(), "onImpressed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onClicked(@NonNull NativeAd nativeAd) {
                    Toast.makeText(nativeAdView.getContext(), "onClicked", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardRequested(@NonNull NativeAd nativeAd) {
                    // Called when request has been sent to the server
                    Toast.makeText(nativeAdView.getContext(), "onRewardRequested", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewarded(@NonNull NativeAd nativeAd, @Nullable RewardResult rewardResult) {
                    // Result of Reward Request can be found here
                    // If the request result was successful, nativeAdRewardResult == NativeAdRewardResult.SUCCESS
                    // If it was not successful, refer to the wiki page or NativeAdRewardResult class for Error cases.
                    Toast.makeText(nativeAdView.getContext(), "onRewarded: " + rewardResult, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onParticipated(@NonNull NativeAd nativeAd) {
                    // Called when the Ad has been participated
                    // Redraw UI with update Ad information here
                    Toast.makeText(nativeAdView.getContext(), "onParticipated", Toast.LENGTH_SHORT).show();
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
            return nativeAdView;
        }
    }

}
