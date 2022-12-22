package com.buzzvil.buzzad.benefit.sample.publisher.nativead2.carousel;

import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion;

class NativeAd2CarouselItem {
    // RecyclerView에서 해당 아이템이 네이티브 2.0인지 타입을 구분하기 위한 클래스입니다.
    static class NativeAd2Item extends NativeAd2CarouselItem {
    }

    // RecyclerView에서 해당 아이템이 피드 진입 슬라이드인지 타입을 구분하기 위한 클래스입니다.
    static class CarouselToFeedSlideItem extends NativeAd2CarouselItem {
        public final FeedPromotion feedPromotion;

        CarouselToFeedSlideItem(final FeedPromotion feedPromotion) {
            this.feedPromotion = feedPromotion;
        }
    }
}
