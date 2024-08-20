package com.buzzvil.buzzad.benefit.sample.publisher.carousel

import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd

/**
 * Carousel에 들어갈 아이템
 */
sealed class CarouselItem {
    companion object {
        /**
         * 파라미터에 맞게 아이템 클래스를 생성
         */
        fun new(nativeAd: NativeAd) = NativeAdItem(nativeAd)
        fun new(feedPromotion: FeedPromotion) = CarouselToFeedSlideItem(feedPromotion)
    }

    // NativeAd를 가지고 있는 아이템
    data class NativeAdItem(val nativeAd: NativeAd) : CarouselItem()
    // Carousel To Feed Slide를 가지고 있는 아이템
    data class CarouselToFeedSlideItem(val feedPromotion: FeedPromotion) : CarouselItem()
}

