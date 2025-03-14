package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.carousel

import com.buzzvil.buzzbenefit.buzznative.BuzzNative

sealed class BuzzNativeCarouselItem {
    // 리사이클러 뷰에서 해당 아이템이 네이티브인지 타입을 구분하기 위한 클래스입니다.
    data class BuzzNativeItem(val buzzNative: BuzzNative) : BuzzNativeCarouselItem()

    // RecyclerView에서 해당 아이템이 BuzzBenefitHubPromotion 타입인지 구분하기 위한 클래스입니다.
    data object BuzzBenefitHubPromotionItem : BuzzNativeCarouselItem()
}
