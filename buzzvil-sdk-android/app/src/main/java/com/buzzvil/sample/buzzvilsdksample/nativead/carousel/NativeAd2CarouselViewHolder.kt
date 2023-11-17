package com.buzzvil.sample.buzzvilsdksample.nativead.carousel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2EventListener
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2StateChangedListener
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2ViewBinder
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionViewBinder
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult
import com.buzzvil.sample.buzzvilsdksample.Constant
import com.buzzvil.sample.buzzvilsdksample.databinding.CarouselItemBinding

class NativeAd2CarouselViewHolder(
    private val binding: CarouselItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val nativeAd2ViewBinder: NativeAd2ViewBinder
    private val carouselToFeedViewBinder: FeedPromotionViewBinder

    init {
        val nativeAd2View = binding.nativeAdView
        val mediaView = binding.adMediaView
        val titleTextView = binding.adTitleText
        val descriptionTextView = binding.adDescriptionText
        val iconImageView = binding.adIconImage
        val ctaView = binding.adCtaView

        // NativeAd2View와 하위 컴포넌트를 바인드합니다.
        nativeAd2ViewBinder = NativeAd2ViewBinder.Builder()
            .nativeAd2View(nativeAd2View)
            .mediaView(mediaView)
            .titleTextView(titleTextView)
            .descriptionTextView(descriptionTextView)
            .iconImageView(iconImageView)
            .ctaView(ctaView)
            .build(Constant.YOUR_NATIVE_ID)

        // 광고 로딩 상태에 따라 로딩 뷰를 보여주거나 숨깁니다.
        nativeAd2ViewBinder.addNativeAd2StateChangedListener(object : NativeAd2StateChangedListener {
            override fun onComplete() {
                setLoadingView(false)
            }

            override fun onError(adError: AdError) {
                setLoadingView(false)
            }

            override fun onNext(nativeAd2: NativeAd2) {
                setLoadingView(false)
            }

            override fun onRequested() {
                setLoadingView(true)
            }
        })

        // 필요에 따라 광고 이벤트 상태를 받아올 수 있는 NativeAd2EventListener를 설정하세요.
        // 로그 기록, 단순 알림 외에 다른 동작을 추가하는 것을 권장하지 않습니다. 자동 갱신 등 네이티브 2.0의 기능과 직접 구현한 동작이 충돌할 수 있습니다.
        nativeAd2ViewBinder.addNativeAd2EventListener(object : NativeAd2EventListener {
            override fun onClicked(nativeAd2: NativeAd2) {}
            override fun onImpressed(nativeAd2: NativeAd2) {}
            override fun onParticipated(nativeAd2: NativeAd2) {}
            override fun onRewardRequested(nativeAd2: NativeAd2) {}
            override fun onRewarded(nativeAd2: NativeAd2, rewardResult: RewardResult) {}
        })

        // CarouselToFeedSlideItem을 위한 바인더를 빌드합니다.
        carouselToFeedViewBinder = FeedPromotionViewBinder.Builder(nativeAd2View, mediaView)
            .titleTextView(titleTextView)
            .descriptionTextView(descriptionTextView)
            .iconImageView(iconImageView)
            .ctaView(ctaView)
            .build()
    }

    fun setPool(carouselPool: NativeAd2Pool, adKey: Int) {
        // 해당 position(adKey)에 해당하는 NativeAd2ViewBinder가 carouselPool을 사용하도록 합니다.
        nativeAd2ViewBinder.setPool(adKey, carouselPool)
    }

    fun bind(position: Int) {
        // NativeAd2ViewBinder의 bind()를 호출하면 광고 할당 및 갱신이 자동으로 수행됩니다.
        nativeAd2ViewBinder.bind(position)
    }

    fun bind(position: Int, feedPromotion: FeedPromotion) {
        // FeedPromotion 객체를 뷰에 바인드합니다.
        carouselToFeedViewBinder.bind(feedPromotion)

        // 베네핏허브 진입 슬라이드는 로딩 뷰가 필요 없기 때문에 false로 설정합니다.
        setLoadingView(false)
    }

    fun unbind(position: Int) {
        // unbind를 반드시 호출하여 뷰를 재사용할 때 문제가 발생하지 않게 합니다.
        nativeAd2ViewBinder.unbind(position)
        carouselToFeedViewBinder.unbind()
    }

    private fun setLoadingView(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingView.visibility = View.VISIBLE
        } else {
            binding.loadingView.visibility = View.GONE
        }
    }
}
