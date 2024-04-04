package com.buzzvil.sample.buzzvilsdksample.custom

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buzzvil.buzzad.benefit.BuzzAdBenefitBase
import com.buzzvil.buzzad.benefit.core.models.Product
import com.buzzvil.buzzad.benefit.feed.benefithub.list.adapter.BenefitHubAdViewAdapter
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdEventListener
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdViewBinder
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult
import com.buzzvil.sample.buzzvilsdksample.R
import com.buzzvil.sample.buzzvilsdksample.databinding.ViewCustomBenefitHubAdBinding
import java.util.Locale
import kotlin.math.roundToInt

class CustomBenefitHubAdViewAdapter : BenefitHubAdViewAdapter {
    private lateinit var binding: ViewCustomBenefitHubAdBinding
    private var nativeAd: NativeAd? = null
    private var nativeAdViewBinder: NativeAdViewBinder? = null
    private val listener = object : NativeAdEventListener {
        override fun onClicked(nativeAd: NativeAd) {
        }

        override fun onImpressed(nativeAd: NativeAd) {
        }

        override fun onParticipated(nativeAd: NativeAd) {
        }

        override fun onRewardRequested(nativeAd: NativeAd) {
        }

        override fun onRewarded(nativeAd: NativeAd, nativeAdRewardResult: RewardResult?) {
        }
    }

    override fun onCreateView(context: Context, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewCustomBenefitHubAdBinding.inflate(inflater, parent, false)
        return binding.root
    }

    override fun getNativeAdView(): NativeAdView {
        return binding.nativeAdView
    }

    override fun onBindView(nativeAd: NativeAd) {
        nativeAd.addNativeAdEventListener(listener)

        val builder = NativeAdViewBinder.Builder(binding.root, binding.adMediaView)
            .addClickableView(binding.clickableArea)
            .ctaView(binding.adCtaView)
            .iconImageView(binding.adIconImage)
            .titleTextView(binding.adTitleText)
            .descriptionTextView(binding.adDescriptionText)

        if (nativeAd.shoppingProduct == null) {
            // 일반 광고 (CPS 외)
            binding.excpsLayout.visibility = View.VISIBLE
            binding.cpsLayout.visibility = View.GONE
        } else {
            // 쇼핑 광고 (CPS)
            binding.excpsLayout.visibility = View.GONE
            binding.cpsLayout.visibility = View.VISIBLE

            setCpsView(nativeAd.shoppingProduct!!)
        }

        val nativeAdViewBinder = builder.build()
        nativeAdViewBinder.bind(nativeAd)

        this.nativeAd = nativeAd
        this.nativeAdViewBinder = nativeAdViewBinder
    }

    override fun onDestroyView() {
        nativeAd?.removeNativeAdEventListener(listener)
        nativeAdViewBinder?.unbind()

        nativeAd = null
        nativeAdViewBinder = null
    }

    private fun setCpsView(product: Product) {
        val discountedPrice = product.discountedPrice
        if (discountedPrice != null) {
            // 할인이 있는 쇼핑 광고
            binding.originalPriceText.paintFlags = binding.originalPriceText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            var percentage = 0
            if (product.price > discountedPrice) {
                percentage = ((product.price - discountedPrice) / product.price * 100).roundToInt()
            }
            if (percentage > 0) {
                binding.priceText.text = getCommaSeparatedPrice(discountedPrice.toLong())
                binding.originalPriceText.text = getCommaSeparatedPrice(product.price.toLong())
                binding.discountPercentageText.text = String.format(Locale.ROOT, "%d%%", percentage)
                binding.discountPercentageText.visibility = View.VISIBLE
            } else {
                binding.priceText.text = getCommaSeparatedPrice(product.price.toLong())
                binding.originalPriceText.text = ""
                binding.discountPercentageText.visibility = View.GONE
            }
        } else {
            // 할인이 없는 쇼핑 광고
            binding.priceText.text = getCommaSeparatedPrice(product.price.toLong())
            binding.originalPriceText.text = ""
            binding.discountPercentageText.visibility = View.GONE
        }
    }

    private fun getCommaSeparatedPrice(price: Long): String {
        val contextForResource = BuzzAdBenefitBase.getInstance().core.applicationContext
        return String.format(
            Locale.getDefault(),
            contextForResource.getString(R.string.cps_price_unit),
            price
        )
    }
}
