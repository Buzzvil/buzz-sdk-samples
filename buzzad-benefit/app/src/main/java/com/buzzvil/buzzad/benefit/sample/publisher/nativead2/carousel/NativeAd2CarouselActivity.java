package com.buzzvil.buzzad.benefit.sample.publisher.nativead2.carousel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2PoolInitListener;
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedEntryView;
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionFactory;
import com.buzzvil.buzzad.benefit.sample.publisher.App;
import com.buzzvil.buzzad.benefit.sample.publisher.R;
import com.buzzvil.buzzad.benefit.sample.publisher.carousel.PaddingDividerDecoration;

import java.util.Arrays;
import java.util.List;

public class NativeAd2CarouselActivity extends AppCompatActivity {
    private final String unitId = App.UNIT_ID_NATIVE_AD;

    // 최대 10개의 광고를 한 번에 요청할 수 있습니다.
    private final int REQUEST_AD_COUNT = 5;

    // 무한 스크롤을 적용합니다.
    boolean isInfiniteLoopEnabled = true;

    private TextView carouselStateTextView;
    private RecyclerView carouselRecyclerView;
    private FeedEntryView toFeedLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad2_carousel);

        carouselStateTextView = findViewById(R.id.carouselStateTextView);
        carouselRecyclerView = findViewById(R.id.carouselRecyclerView);
        toFeedLink = findViewById(R.id.toFeedLink);

        initCarousel();
    }

    private void initCarousel() {
        // 광고 중복 할당을 막기 위해 하나의 캐러셀에 하나의 NativeAd2Pool 인스턴스를 생성하여 사용합니다.
        NativeAd2Pool carouselPool = new NativeAd2Pool(unitId);

        carouselPool.init(REQUEST_AD_COUNT, new NativeAd2PoolInitListener() {
            @Override
            public void onLoaded(int adCount) {
                carouselStateTextView.setText("carouselPool state: onLoaded (adCount: " + adCount + ")");
                toFeedLink.setVisibility(View.VISIBLE);

                // 할당 받은 광고 갯수(adCount)만큼 아이템 리스트를 만들어 RecyclerView 어댑터를 초기화 합니다.
                initAdapter(adCount, carouselPool);
            }

            @Override
            public void onError(@NonNull AdError adError) {
                // 광고 레이아웃을 숨김 처리 하는 등 적절한 에러 처리를 할 수 있습니다.
                carouselStateTextView.setText("carouselPool state: onError (" + adError.getAdErrorType().name() + ")");
                toFeedLink.setVisibility(View.GONE);
            }
        });
    }

    private void initAdapter(int adCount, NativeAd2Pool carouselPool) {
        // 아이템 간에 여백을 넣는 Decoration을 추가합니다.
        int itemPaddingDp = 16;
        carouselRecyclerView.addItemDecoration(new PaddingDividerDecoration(itemPaddingDp));

        // 뷰페이저 처럼 아이템 위치를 중앙에 고정할 수 있도록 PagerSnapHelper를 사용합니다.
        new PagerSnapHelper().attachToRecyclerView(carouselRecyclerView);

        // 캐러셀에 보여줄 아이템 리스트를 생성합니다.
        List<NativeAd2CarouselItem> list = buildCarouselItems(adCount);

        // 어댑터를 설정합니다.
        NativeAd2CarouselAdapter adapter = new NativeAd2CarouselAdapter(unitId, list, carouselPool, isInfiniteLoopEnabled);
        carouselRecyclerView.setAdapter(adapter);

        if (isInfiniteLoopEnabled) {
            // 적당히 큰 수의 position으로 이동하여 무한 스크롤 효과를 구현합니다.
            carouselRecyclerView.getLayoutManager().scrollToPosition(list.size() * 10000);
        }
    }

    private List<NativeAd2CarouselItem> buildCarouselItems(int adCount) {
        // 마지막 페이지에 CarouselToFeedSlideItem을 추가하기 위해 1개 더 큰 사이즈로 배열을 생성합니다.
        NativeAd2CarouselItem[] carouselItems = new NativeAd2CarouselItem[adCount + 1];

        // 배열을 NativeAd2Item으로 채웁니다.
        Arrays.fill(carouselItems, new NativeAd2CarouselItem.NativeAd2Item());

        // 마지막 아이템은 CarouselToFeedSlideItem으로 설정합니다.
        carouselItems[carouselItems.length - 1] = new NativeAd2CarouselItem.CarouselToFeedSlideItem(new FeedPromotionFactory(unitId).buildForCarousel());

        return Arrays.asList(carouselItems);
    }
}
