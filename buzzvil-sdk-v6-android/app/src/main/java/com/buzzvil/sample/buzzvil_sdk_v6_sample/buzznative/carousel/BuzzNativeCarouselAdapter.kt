package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.carousel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzzvil.buzzbenefit.buzznative.BuzzNative
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.BuzzNativeAdViewBinding

class BuzzNativeCarouselAdapter(
    private val list: List<BuzzNative>,
    private val isInfiniteLoopEnabled: Boolean
) : RecyclerView.Adapter<BuzzNativeCarouselViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuzzNativeCarouselViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = BuzzNativeAdViewBinding.inflate(inflater, parent, false)

        return BuzzNativeCarouselViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val actualItemCount = list.size

        return if (isInfiniteLoopEnabled && actualItemCount > 0) {
            // 무한 루프를 쉽게 구현하는 방법으로 매우 큰 수를 여기서 반환합니다.
            Int.MAX_VALUE
        } else actualItemCount
    }

    override fun onBindViewHolder(holder: BuzzNativeCarouselViewHolder, position: Int) {
        // position 대신 itemPosition을 사용합니다.
        val itemPosition = getItemPosition(position)

        val item = list[itemPosition]
        holder.bind(item)
    }

    override fun onViewRecycled(holder: BuzzNativeCarouselViewHolder) {
        super.onViewRecycled(holder)

        holder.unbind()
    }

    private fun getItemPosition(position: Int): Int {
        return if (isInfiniteLoopEnabled) {
            position % list.size
        } else {
            position
        }
    }
}
