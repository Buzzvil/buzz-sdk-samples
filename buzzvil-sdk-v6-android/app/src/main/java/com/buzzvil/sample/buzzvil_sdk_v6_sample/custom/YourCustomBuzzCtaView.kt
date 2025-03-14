package com.buzzvil.sample.buzzvil_sdk_v6_sample.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.buzzvil.buzzbenefit.BuzzCtaView
import com.buzzvil.sample.buzzvil_sdk_v6_sample.R
import java.util.Locale

class YourCustomBuzzCtaView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BuzzCtaView(context, attrs, defStyleAttr) {
    private val rewardImageView: ImageView
    private val rewardTextView: TextView
    private val ctaTextView: TextView

    constructor(context: Context) : this(
        context,
        null
    )

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        0
    )

    init {
        inflate(getContext(), R.layout.view_customized_cta, this)
        rewardImageView = findViewById(R.id.imageReward)
        rewardTextView = findViewById(R.id.textReward)
        ctaTextView = findViewById(R.id.textCta)
    }

    private fun setRewardIcon(@DrawableRes iconResId: Int) {
        rewardImageView.setImageResource(iconResId)
        rewardImageView.visibility = View.VISIBLE
    }

    private fun hideRewardIcon() {
        rewardImageView.visibility = View.GONE
    }

    private fun setCtaText(ctaText: String) {
        ctaTextView.text = ctaText
    }

    private fun setRewardText(rewardText: String) {
        rewardTextView.text = rewardText
    }

    override fun renderViewParticipatingState(callToAction: String) {
        setCtaText("참여 확인 중")
        hideRewardIcon()
        setRewardText("")
    }

    override fun renderViewParticipatedState(callToAction: String) {
        setRewardIcon(R.drawable.custom_participated_icon)
        setRewardText("")
        setCtaText("참여 완료")
    }

    override fun renderViewRewardAvailableState(callToAction: String, reward: Int) {
        setRewardIcon(R.drawable.custom_reward_icon)
        setRewardText(String.format(Locale.US, "+%,d", reward))
        setCtaText(callToAction)
    }

    override fun renderViewRewardNotAvailableState(callToAction: String) {
        hideRewardIcon()
        setRewardText("")
        setCtaText(callToAction)
    }
}