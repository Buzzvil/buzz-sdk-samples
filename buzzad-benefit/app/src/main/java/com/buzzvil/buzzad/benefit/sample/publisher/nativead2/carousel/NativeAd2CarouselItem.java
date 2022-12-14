package com.buzzvil.buzzad.benefit.sample.publisher.nativead2.carousel;

import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion;

class NativeAd2CarouselItem {
    static class NativeAd2Item extends NativeAd2CarouselItem {
    }

    static class CarouselToFeedSlideItem extends NativeAd2CarouselItem {
        public final FeedPromotion feedPromotion;

        CarouselToFeedSlideItem(final FeedPromotion feedPromotion) {
            this.feedPromotion = feedPromotion;
        }
    }
}
