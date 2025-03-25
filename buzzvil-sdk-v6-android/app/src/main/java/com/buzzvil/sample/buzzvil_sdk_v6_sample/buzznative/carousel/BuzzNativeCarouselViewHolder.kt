package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.carousel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.buzzvil.buzzbenefit.BuzzAdError
import com.buzzvil.buzzbenefit.buzznative.BuzzNative
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeAd
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeAdEventsListener
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeAdViewBinder
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeRefreshEventsListener
import com.buzzvil.buzzbenefit.buzznative.BuzzRewardResult
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.BuzzNativeAdViewBinding

class BuzzNativeCarouselViewHolder(
    private val binding: BuzzNativeAdViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val buzzNativeAdViewBinder: BuzzNativeAdViewBinder

    init {
        val buzzNativeAdView = binding.buzzNativeAdView
        val buzzMediaView = binding.buzzMediaView
        val titleTextView = binding.textTitle
        val descriptionTextView = binding.textDescription
        val iconImageView = binding.imageIcon
        val buzzCtaView = binding.buzzCtaView
        val nativeOverlayView = binding.nativeOverlayView

        // BuzzNativeView와 하위 컴포넌트를 바인드합니다.
        buzzNativeAdViewBinder = BuzzNativeAdViewBinder.Builder()
            .buzzNativeAdView(buzzNativeAdView)
            .buzzMediaView(buzzMediaView)
            .titleTextView(titleTextView)
            .descriptionTextView(descriptionTextView)
            .iconImageView(iconImageView)
            .buzzCtaView(buzzCtaView)
            .nativeOverlayViewLayout(nativeOverlayView)
            .build()
    }

    fun bind(native: BuzzNative) {
        // BuzzNativeViewBinder의 bind()를 호출하면 광고 갱신이 자동으로 수행됩니다.
        // (Optional) BuzzNativeRefreshEventsListener, BuzzNativeAdEventsListener를 bind 하기 전 연결해야 합니다.

        native.setRefreshEventsListener(
            object : BuzzNativeRefreshEventsListener {
                override fun onSuccess(buzzNativeAd: BuzzNativeAd) {
                    setLoadingView(false)
                }

                override fun onFailure(adError: BuzzAdError) {
                    setLoadingView(false)
                }

                override fun onRequest() {
                    setLoadingView(true)
                }
            }
        )

        native.setAdEventsListener(object : BuzzNativeAdEventsListener {
            override fun onClicked(buzzNativeAd: BuzzNativeAd) {}

            override fun onImpressed(buzzNativeAd: BuzzNativeAd) {}

            override fun onParticipated(buzzNativeAd: BuzzNativeAd) {}

            override fun onRewardRequested(buzzNativeAd: BuzzNativeAd) {}

            override fun onRewarded(buzzNativeAd: BuzzNativeAd, buzzRewardResult: BuzzRewardResult) {}
        })

        buzzNativeAdViewBinder.bind(native)
    }

    fun unbind() {
        // unbind를 반드시 호출하여 뷰를 재사용할 때 문제가 발생하지 않게 합니다.
        buzzNativeAdViewBinder.unbind()
    }

    private fun setLoadingView(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingView.visibility = View.VISIBLE
        } else {
            binding.loadingView.visibility = View.GONE
        }
    }
}
