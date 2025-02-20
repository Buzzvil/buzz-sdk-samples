package com.buzzvil.sample.buzzvilsdksample.nativead.carousel

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.buzzvil.buzzad.benefit.presentation.media.CtaView
import com.buzzvil.buzzad.benefit.presentation.media.MediaView
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView
import com.buzzvil.sample.buzzvilsdksample.R

@SuppressWarnings("RestrictedApi")
class CustomFeedPromotionViewBinder private constructor(
    private val nativeAdView: NativeAdView,
    private val mediaView: MediaView,
    private val ctaView: CtaView?,
    private val titleTextView: TextView?,
    private val descriptionTextView: TextView?,
    private val iconImageView: ImageView?,
    private val sponsoredLayout: ViewGroup?,
    private val customClickableViews: List<View>
) {
    fun bind() {
        nativeAdView.isFeedPromotionView = true
        mediaView.setCreative(null)
        titleTextView?.text = "타이틀 텍스트를 입력하세요."
        descriptionTextView?.text = "description 을 입력하세요"
        ctaView?.visibility = View.GONE
        mediaView.setDrawable(R.drawable.ic_hello)
        iconImageView?.setImageResource(android.R.drawable.ic_dialog_info)
        nativeAdView.setOnClickListener {
            Log.d("CustomFeedPromotionViewBinder", "nativeAdView clicked")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.buzzvil.com"))
            nativeAdView.context.startActivity(intent)
        }
    }

    fun unbind() {
        nativeAdView.isFeedPromotionView = false
        nativeAdView.setOnClickListener(null)
    }

    data class Builder constructor(
        private val nativeAdView: NativeAdView,
        private val mediaView: MediaView
    ) {
        private var ctaView: CtaView? = null
        private var titleTextView: TextView? = null
        private var descriptionTextView: TextView? = null
        private var iconImageView: ImageView? = null
        private var sponsoredLayout: ViewGroup? = null
        private val clickableViews: ArrayList<View> = ArrayList()

        fun ctaView(ctaView: CtaView?) = apply { this.ctaView = ctaView }
        fun titleTextView(titleTextView: TextView?) = apply { this.titleTextView = titleTextView }
        fun descriptionTextView(descriptionTextView: TextView?) =
            apply { this.descriptionTextView = descriptionTextView }

        fun iconImageView(iconImageView: ImageView?) = apply { this.iconImageView = iconImageView }
        fun sponsoredLayout(adChoiceLayout: ViewGroup?) =
            apply { this.sponsoredLayout = adChoiceLayout }

        fun addClickableView(view: View) = apply { this.clickableViews.add(view) }

        fun build() = CustomFeedPromotionViewBinder(
            nativeAdView,
            mediaView,
            ctaView,
            titleTextView,
            descriptionTextView,
            iconImageView,
            sponsoredLayout,
            clickableViews
        )
    }
}
