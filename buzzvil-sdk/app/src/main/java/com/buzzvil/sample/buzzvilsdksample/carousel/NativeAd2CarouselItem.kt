package com.buzzvil.sample.buzzvilsdksample.carousel

import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion

sealed class NativeAd2CarouselItem {
    // RecyclerView에서 해당 아이템이 네이티브 2.0인지 타입을 구분하기 위한 클래스입니다.
    data object NativeAd2Item : NativeAd2CarouselItem()

    // RecyclerView에서 해당 아이템이 베네핏허브 진입 슬라이드인지 타입을 구분하기 위한 클래스입니다.
    data class CarouselToFeedSlideItem(val feedPromotion: FeedPromotion) : NativeAd2CarouselItem()
}
