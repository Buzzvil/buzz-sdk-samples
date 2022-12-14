package com.buzzvil.buzzad.benefit.sample.publisher.nativead2.carousel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2PoolInitListener;
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

    private TextView carouselStateTextView;
    private RecyclerView carouselRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad2_carousel);

        carouselStateTextView = findViewById(R.id.carouselStateTextView);
        carouselRecyclerView = findViewById(R.id.carouselRecyclerView);

        initCarousel();
    }

    private void initCarousel() {
        NativeAd2Pool carouselPool = new NativeAd2Pool(unitId);

        carouselPool.init(REQUEST_AD_COUNT, new NativeAd2PoolInitListener() {
            @Override
            public void onLoaded(int adCount) {
                carouselStateTextView.setText("carouselPool state: onLoaded (adCount: " + adCount + ")");
                initAdapter(adCount, carouselPool);
            }

            @Override
            public void onError(@NonNull AdError adError) {
                carouselStateTextView.setText("carouselPool state: onError (" + adError.getAdErrorType().name() + ")");
            }
        });
    }

    private void initAdapter(int adCount, NativeAd2Pool carouselPool) {
        // 아이템 간에 여백을 넣는 Decoration 추가
        int itemPaddingDp = 16;
        carouselRecyclerView.addItemDecoration(new PaddingDividerDecoration(itemPaddingDp));

        // ViewPager와 비슷하게, 스크롤이 중간에서 멈추면 한쪽 아이템을 선택하여 다 보일 때까지 스크롤
        new PagerSnapHelper().attachToRecyclerView(carouselRecyclerView);

        // Adapter 설정
        List<NativeAd2CarouselItem> list = buildCarouselItems(adCount);
        boolean infiniteLoop = true;
        NativeAd2CarouselAdapter adapter = new NativeAd2CarouselAdapter(unitId, list, carouselPool, infiniteLoop);
        carouselRecyclerView.setAdapter(adapter);

        // 적당히 큰 수의 position으로 이동하여 무한 스크롤 효과
        carouselRecyclerView.getLayoutManager().scrollToPosition(list.size() * 10000);
    }

    private List<NativeAd2CarouselItem> buildCarouselItems(int adCount) {
        // adCount 갯수만큼 광고 + 마지막 페이지에 Carousel To Feed Slide를 추가
        NativeAd2CarouselItem[] carouselItems = new NativeAd2CarouselItem[adCount + 1];
        Arrays.fill(carouselItems, new NativeAd2CarouselItem.NativeAd2Item());
        carouselItems[carouselItems.length - 1] = new NativeAd2CarouselItem.CarouselToFeedSlideItem(new FeedPromotionFactory(unitId).buildForCarousel());

        return Arrays.asList(carouselItems);
    }
}
