package com.buzzvil.buzzad.benefit.sample.publisher.carousel;

import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;

/**
 * Carousel에 들어갈 아이템
 */
class CarouselItem {
    // NativeAd를 가지고 있는 아이템
    static class NativeAdItem extends CarouselItem {
        public final NativeAd nativeAd;

        NativeAdItem(final NativeAd nativeAd) {
            this.nativeAd = nativeAd;
        }
    }

    // Carousel To Feed Slide를 가지고 있는 아이템
    static class CarouselToFeedSlideItem extends CarouselItem {
        public final FeedPromotion feedPromotion;

        CarouselToFeedSlideItem(final FeedPromotion feedPromotion) {
            this.feedPromotion = feedPromotion;
        }
    }
}

