package com.buzzvil.buzzad.benefit.sample.publisher.carousel;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionFactory;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdEventListener;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdLoader;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdLoaderParams;
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult;
import com.buzzvil.buzzad.benefit.sample.publisher.App;
import com.buzzvil.buzzad.benefit.sample.publisher.databinding.ActivityNativeCarouselBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * BuzzAd SDK를 사용하여 Carousel UI를 구현하는 방법에 대한 예제 코드
 */
public class NativeCarouselActivity extends AppCompatActivity {
    private static final String TAG = "NativeCarouselActivity";

    public static void startActivity(final Context context) {
        final Intent intent = new Intent(context, NativeCarouselActivity.class);
        context.startActivity(intent);
    }

    private ActivityNativeCarouselBinding binding;
    private CarouselAdapter adapter;

    // Carousel 지면에서 사용할 Unit ID
    private String unitId = App.UNIT_ID_NATIVE_AD;

    // 무한 스크롤을 사용할지 설정
    private boolean infiniteLoop = true;

    // Carousel 아이템 간의 사이 간격
    private int itemPaddingDp = 16;

    // 화면 폭에 대한 Carousel 아이템의 폭 비율
    private float itemWidthPercentOfScreen = 0.8f;

    // LinearLayoutManager를 사용하고 HORIZONTAL로 설정하여 수평으로 일렬로 정렬된 레이아웃을 사용한다.
    private LinearLayoutManager layoutManager = new LinearLayoutManager(
            this,
            HORIZONTAL,
            false
    ) {
        // Carousel 아이템의 폭을 설정한 비율에 맞게 변경한다.
        @Override
        public boolean checkLayoutParams(final RecyclerView.LayoutParams lp) {
            lp.width = Math.round(getWidth() * itemWidthPercentOfScreen);
            return true;
        }
    };

    private NativeAdLoader.OnAdsLoadedListener adsLoadedListener = new NativeAdLoader.OnAdsLoadedListener() {

        @Override
        public void onLoadError(final AdError error) {
            onError();
        }

        @Override
        public void onAdsLoaded(final Collection<NativeAd> nativeAds) {
            if (nativeAds.isEmpty()) {
                onEmpty();
            } else {
                NativeCarouselActivity.this.onAdsLoaded(nativeAds);
            }
        }
    };

    private NativeAdEventListener nativeAdEventListener = new NativeAdEventListener() {
        @Override
        public void onImpressed(final NativeAd nativeAd) {
        }

        @Override
        public void onClicked(final NativeAd nativeAd) {
        }

        @Override
        public void onRewardRequested(final NativeAd nativeAd) {
        }

        @Override
        public void onRewarded(
                final NativeAd nativeAd,
                final RewardResult nativeAdRewardResult
        ) {
        }

        @Override
        public void onParticipated(final NativeAd nativeAd) {
        }
    };


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNativeCarouselBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new CarouselAdapter(infiniteLoop);
        initCarousel();
        loadAds();
    }

    private void initCarousel() {
        binding.carousel.setAdapter(adapter);

        // ViewPager와 비슷하게, 스크롤이 중간에서 멈추면 한쪽 아이템을 선택하여 다 보일 때까지 스크롤
        new PagerSnapHelper().attachToRecyclerView(binding.carousel);

        binding.carousel.setLayoutManager(layoutManager);
        // 아이템 간에 여백을 넣는 Decoration 추가
        binding.carousel.addItemDecoration(new PaddingDividerDecoration(itemPaddingDp));
    }

    private void loadAds() {
        new NativeAdLoader(unitId).loadAds(
                adsLoadedListener,
                new NativeAdLoaderParams.Builder()
                        .count(5)
                        .build()
        );
    }

    private void onError() {
        // 에러가 발생하면 지면을 표시하지 않는다.
        binding.placement.setVisibility(View.GONE);
        Log.d(TAG, "An error occurred while loading ads");
    }

    private void onEmpty() {
        // 보여줄 광고가 없으면 지면을 표시하지 않는다.
        binding.placement.setVisibility(View.GONE);
        Log.d(TAG, "The ad list is empty");
    }

    private void onAdsLoaded(final Collection<NativeAd> nativeAds) {
        binding.placement.setVisibility(View.VISIBLE);
        for (final NativeAd nativeAd : nativeAds) {
            nativeAd.addNativeAdEventListener(nativeAdEventListener);
        }

        final List<CarouselItem> items = buildCarouselItems(nativeAds);
        adapter.submitList(items);
        if (infiniteLoop) {
            // To show item before first item
            final RecyclerView.LayoutManager layoutManager = binding.carousel.getLayoutManager();
            if (layoutManager != null) {
                layoutManager.scrollToPosition(items.size() * 10000);
            }
        }
        Log.d(TAG, "" + nativeAds.size() + " Ads are loaded");
    }

    /**
     * NativeAd 배열을 Carousel에 채워넣을 CarouselItem으로 변환하는 함수
     *
     * @param nativeAds: Carousel에 표시할 광고 객체 배열
     *                   <p>
     *                   광고가 비어있지 않으면 마지막 페이지에 Carousel To Feed Slide를 추가한다.
     */
    private List<CarouselItem> buildCarouselItems(final Collection<NativeAd> nativeAds) {
        List<CarouselItem> items = new ArrayList<>();
        for (final NativeAd nativeAd : nativeAds) {
            items.add(new CarouselItem.NativeAdItem(nativeAd));
        }
        if (!items.isEmpty()) {
            items.add(new CarouselItem.CarouselToFeedSlideItem(
                    new FeedPromotionFactory(unitId).buildForCarousel()
            ));
        }
        return items;
    }
}
