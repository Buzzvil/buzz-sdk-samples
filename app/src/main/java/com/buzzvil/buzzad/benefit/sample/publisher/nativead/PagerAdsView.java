package com.buzzvil.buzzad.benefit.sample.publisher.nativead;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.buzzvil.buzzad.benefit.core.models.Ad;
import com.buzzvil.buzzad.benefit.presentation.media.CtaPresenter;
import com.buzzvil.buzzad.benefit.presentation.media.CtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdRewardResult;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView;
import com.buzzvil.buzzad.benefit.presentation.video.VideoErrorStatus;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

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

    private static class NativeAdsAdapter extends android.support.v4.view.PagerAdapter {
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
            final Ad ad = nativeAd.getAd();

            final NativeAdView nativeAdView = (NativeAdView) LayoutInflater.from(context).inflate(R.layout.view_native_ad_item, null, false);
            final MediaView mediaView = nativeAdView.findViewById(R.id.ad_media_view);
            final TextView titleTextView = nativeAdView.findViewById(R.id.ad_title_text);
            final TextView descriptionTextView = nativeAdView.findViewById(R.id.ad_description_text);
            final ImageView iconImageView = nativeAdView.findViewById(R.id.ad_icon_image);
            final CtaView ctaView = nativeAdView.findViewById(R.id.ad_cta_view);

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
            Glide.with(context).load(ad.getIconUrl()).into(iconImageView);
            final CtaPresenter ctaPresenter = new CtaPresenter(ctaView);
            ctaPresenter.bind(nativeAd);

            final List<View> clickableViews = new ArrayList<>();
            clickableViews.add(ctaView);
            clickableViews.add(mediaView);
            clickableViews.add(titleTextView);
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

            return nativeAdView;
        }
    }

}
