package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.carousel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHubPromotionViewBinder
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.BuzzNativeAdViewBinding

class BuzzBenefitHubPromotionViewHolder(
    private val binding: BuzzNativeAdViewBinding,
    nativeUnitId: String
) : RecyclerView.ViewHolder(binding.root) {
    private val buzzBenefitHubPromotionViewBinder: BuzzBenefitHubPromotionViewBinder

    init {
        val buzzNativeView = binding.buzzNativeAdView
        val mediaView = binding.buzzMediaView
        val titleTextView = binding.textTitle
        val descriptionTextView = binding.textDescription
        val iconImageView = binding.imageIcon
        val ctaView = binding.buzzCtaView

        // BuzzBenefitHubPromotionItem을 위한 바인더를 빌드합니다.
        buzzBenefitHubPromotionViewBinder = BuzzBenefitHubPromotionViewBinder.Builder(nativeUnitId)
            .buzzNativeView(buzzNativeView)
            .buzzMediaView(mediaView)
            .titleTextView(titleTextView)
            .descriptionTextView(descriptionTextView)
            .iconImageView(iconImageView)
            .buzzCtaView(ctaView)
            .build()
    }

    fun bind() {
        // 베네핏허브 프로모션 뷰를 바인드합니다.
        buzzBenefitHubPromotionViewBinder.bind()
        binding.loadingView.visibility = View.GONE
    }

    fun unbind() {
        // unbind를 반드시 호출하여 뷰를 재사용할 때 문제가 발생하지 않게 합니다.
        buzzBenefitHubPromotionViewBinder.unbind()
    }
}
