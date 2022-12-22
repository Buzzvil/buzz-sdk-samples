package com.buzzvil.buzzad.benefit.sample.publisher.nativead2.carousel;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2EventListener;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2StateChangedListener;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2View;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2ViewBinder;
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion;
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionViewBinder;
import com.buzzvil.buzzad.benefit.presentation.media.DefaultCtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

public class NativeAd2CarouselViewHolder extends RecyclerView.ViewHolder {

    private TextView positionTextView;
    private LinearLayout loadingView;

    private NativeAd2ViewBinder nativeAd2ViewBinder;
    private FeedPromotionViewBinder carouselToFeedViewBinder;

    NativeAd2CarouselViewHolder(String unitId, NativeAd2View nativeAd2View) {
        super(nativeAd2View);

        positionTextView = nativeAd2View.findViewById(R.id.positionTextView);
        TextView stateTextView = nativeAd2View.findViewById(R.id.stateTextView);
        TextView eventTextView = nativeAd2View.findViewById(R.id.eventTextView);
        this.loadingView = nativeAd2View.findViewById(R.id.loadingView);

        MediaView mediaView = nativeAd2View.findViewById(R.id.mediaView);
        ImageView imageIcon = nativeAd2View.findViewById(R.id.imageIcon);
        TextView textTitle = nativeAd2View.findViewById(R.id.textTitle);
        TextView textDescription = nativeAd2View.findViewById(R.id.textDescription);
        DefaultCtaView ctaView = nativeAd2View.findViewById(R.id.ctaView);

        // NativeAd2View와 하위 컴포넌트를 바인드합니다.
        nativeAd2ViewBinder = new NativeAd2ViewBinder.Builder()
                .nativeAd2View(nativeAd2View)
                .mediaView(mediaView)
                .iconImageView(imageIcon)
                .titleTextView(textTitle)
                .descriptionTextView(textDescription)
                .ctaView(ctaView)
                .build(unitId);

        // 필요에 따라 광고 갱신 상태를 받아올 수 있는 NativeAd2StateChangedListener를 설정하세요.
        nativeAd2ViewBinder.addNativeAd2StateChangedListener(new NativeAd2StateChangedListener() {
            @Override
            public void onRequested() {
                stateTextView.setText("state: onRequested");
            }

            @Override
            public void onNext(@NonNull NativeAd2 nativeAd2) {
                stateTextView.setText("state: onNext");
            }

            @Override
            public void onComplete() {
                stateTextView.setText("state: onComplete");
            }

            @Override
            public void onError(@NonNull AdError adError) {
                stateTextView.setText("state: onError(" + adError.getAdErrorType().name() + ")");
            }
        });

        // 필요에 따라 광고 이벤트 상태를 받아올 수 있는 NativeAd2EventListener를 설정하세요.
        nativeAd2ViewBinder.addNativeAd2EventListener(new NativeAd2EventListener() {
            @Override
            public void onImpressed(@NonNull NativeAd2 nativeAd2) {
                eventTextView.setText("event: onImpressed");
            }

            @Override
            public void onClicked(@NonNull NativeAd2 nativeAd2) {
                eventTextView.setText("event: onClicked");
            }

            @Override
            public void onRewardRequested(@NonNull NativeAd2 nativeAd2) {
                eventTextView.setText("event: onRewardRequested");
            }

            @Override
            public void onRewarded(@NonNull NativeAd2 nativeAd2, @NonNull RewardResult rewardResult) {
                eventTextView.setText("event: onRewarded, result: " + rewardResult.name());
            }

            @Override
            public void onParticipated(@NonNull NativeAd2 nativeAd2) {
                eventTextView.setText("event: onParticipated");
            }
        });

        // CarouselToFeedSlideItem을 위한 바인더를 빌드합니다.
        carouselToFeedViewBinder = new FeedPromotionViewBinder.Builder(nativeAd2View, mediaView)
                .titleTextView(textTitle)
                .descriptionTextView(textDescription)
                .iconImageView(imageIcon)
                .ctaView(ctaView)
                .build();
    }

    public void setPool(NativeAd2Pool carouselPool, int adKey) {
        // 해당 position(adKey)에 해당하는 NativeAd2ViewBinder가 carouselPool을 사용하도록 합니다.
        nativeAd2ViewBinder.setPool(adKey, carouselPool);
    }

    public void bind(int position) {
        positionTextView.setText("position: " + position);
        setLoadingView(false);

        // NativeAd2ViewBinder의 bind()를 호출하면 광고 할당 및 갱신이 자동으로 수행됩니다.
        nativeAd2ViewBinder.bind(position);
    }

    public void bind(int position, FeedPromotion feedPromotion) {
        positionTextView.setText("position: " + position);
        setLoadingView(false);

        // FeedPromotion 객체를 뷰에 바인드합니다.
        carouselToFeedViewBinder.bind(feedPromotion);
    }

    public void unbind(int position) {
        // unbind를 반드시 호출하여 뷰를 재사용할 때 문제가 발생하지 않게 합니다.
        nativeAd2ViewBinder.unbind(position);
        carouselToFeedViewBinder.unbind();
    }

    private void setLoadingView(boolean isLoading) {
        if (isLoading) {
            loadingView.setVisibility(View.VISIBLE);
        } else {
            loadingView.setVisibility(View.GONE);
        }
    }
}
