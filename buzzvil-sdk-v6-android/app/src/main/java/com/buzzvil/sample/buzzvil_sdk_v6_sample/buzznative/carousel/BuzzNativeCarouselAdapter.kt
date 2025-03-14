package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.carousel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.BuzzNativeAdViewBinding

class BuzzNativeCarouselAdapter(
    private val nativeUnitId: String,
    private val list: List<BuzzNativeCarouselItem>,
    private val isInfiniteLoopEnabled: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = BuzzNativeAdViewBinding.inflate(inflater, parent, false)


        return when (viewType) {
            VIEW_TYPE_NATIVE_AD -> {
                BuzzNativeCarouselViewHolder(binding)
            }

            VIEW_TYPE_CAROUSEL_TO_FEED_SLIDE -> {
                BuzzBenefitHubPromotionViewHolder(binding, nativeUnitId)
            }

            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun getItemCount(): Int {
        val actualItemCount = list.size
        return if (isInfiniteLoopEnabled && actualItemCount > 0) {
            // 무한 루프를 쉽게 구현하는 방법으로 매우 큰 수를 여기서 반환합니다.
            Int.MAX_VALUE
        } else actualItemCount
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position % list.size]) {
            is BuzzNativeCarouselItem.BuzzNativeItem -> VIEW_TYPE_NATIVE_AD
            is BuzzNativeCarouselItem.BuzzBenefitHubPromotionItem -> VIEW_TYPE_CAROUSEL_TO_FEED_SLIDE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // position 대신 itemPosition을 사용합니다.
        val itemPosition = getItemPosition(position)

        val item = list[itemPosition]

        if (holder is BuzzNativeCarouselViewHolder) {
            holder.bind((item as BuzzNativeCarouselItem.BuzzNativeItem).buzzNative)
        } else if (holder is BuzzBenefitHubPromotionViewHolder) {
            holder.bind()
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)

        if (holder is BuzzNativeCarouselViewHolder) {
            holder.unbind()
        } else if (holder is BuzzBenefitHubPromotionViewHolder) {
            holder.unbind()
        }
    }


    private fun getItemPosition(position: Int): Int {
        return if (isInfiniteLoopEnabled) {
            position % list.size
        } else {
            position
        }
    }

    companion object {
        private const val VIEW_TYPE_NATIVE_AD = 0
        private const val VIEW_TYPE_CAROUSEL_TO_FEED_SLIDE = 1
    }
}