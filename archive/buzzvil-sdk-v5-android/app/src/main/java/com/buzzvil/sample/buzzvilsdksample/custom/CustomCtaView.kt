package com.buzzvil.sample.buzzvilsdksample.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import com.buzzvil.buzzad.benefit.presentation.media.CtaView
import com.buzzvil.sample.buzzvilsdksample.R
import com.buzzvil.sample.buzzvilsdksample.databinding.ViewCustomizedCtaBinding
import java.util.Locale

class CustomCtaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CtaView(context, attrs, defStyleAttr) {
    private val binding: ViewCustomizedCtaBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewCustomizedCtaBinding.inflate(inflater, this, true)
    }

    // 유저가 광고에 참여 중인 상태
    override fun renderViewParticipatingState(callToAction: String) {
        hideRewardIcon()
        setRewardText("")
        setCtaText("참여 확인 중")
    }

    // 유저가 광고 참여를 완료한 상태
    override fun renderViewParticipatedState(callToAction: String) {
        setRewardIcon(R.drawable.my_participated_icon)
        setRewardText("")
        setCtaText("참여 완료")
    }

    // 유저가 아직 광고에 참여하지 않은 상태
    override fun renderViewRewardAvailableState(callToAction: String, reward: Int) {
        setRewardIcon(R.drawable.my_reward_icon)
        setRewardText(String.format(Locale.US, "+%,d", reward))
        setCtaText(callToAction)
    }

    // 유저가 획득할 리워드가 없는 광고
    override fun renderViewRewardNotAvailableState(callToAction: String) {
        hideRewardIcon()
        setRewardText("")
        setCtaText(callToAction)
    }

    // CTA 텍스트 설정
    private fun setCtaText(ctaText: String?) {
        binding.textCta.text = ctaText
    }

    // CTA 리워드 값 설정
    private fun setRewardText(rewardText: String?) {
        binding.textReward.text = rewardText
    }

    // CTA 아이콘 설정
    private fun setRewardIcon(@DrawableRes iconResId: Int) {
        binding.imageReward.setImageResource(iconResId)
        binding.imageReward.visibility = View.VISIBLE
    }

    // CTA 아이콘 숨기기 처리
    private fun hideRewardIcon() {
        binding.imageReward.visibility = View.GONE
    }
}
