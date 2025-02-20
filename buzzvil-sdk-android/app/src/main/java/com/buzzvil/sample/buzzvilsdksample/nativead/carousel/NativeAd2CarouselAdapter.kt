package com.buzzvil.sample.buzzvilsdksample.nativead.carousel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool
import com.buzzvil.sample.buzzvilsdksample.nativead.carousel.NativeAd2CarouselItem.CarouselToFeedSlideItem
import com.buzzvil.sample.buzzvilsdksample.nativead.carousel.NativeAd2CarouselItem.NativeAd2Item
import com.buzzvil.sample.buzzvilsdksample.databinding.CarouselItemBinding

class NativeAd2CarouselAdapter(
    private val list: List<NativeAd2CarouselItem>,
    private val carouselPool: NativeAd2Pool,
    private val isInfiniteLoopEnabled: Boolean,
) : RecyclerView.Adapter<NativeAd2CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NativeAd2CarouselViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = CarouselItemBinding.inflate(inflater, parent, false)
        return NativeAd2CarouselViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val actualItemCount = list.size
        return if (isInfiniteLoopEnabled && actualItemCount > 0) {
            // 무한 루프를 쉽게 구현하는 방법으로 매우 큰 수를 여기서 반환합니다.
            Int.MAX_VALUE
        } else actualItemCount
    }

    override fun onBindViewHolder(holder: NativeAd2CarouselViewHolder, position: Int) {
        // position 대신 itemPosition을 사용합니다.
        val itemPosition = getItemPosition(position)

        // 해당 position에 해당하는 NativeAd2ViewBinder가 carouselPool을 사용하도록 합니다.
        holder.setPool(carouselPool, itemPosition)

        // 아이템의 타입을 구분하여 적절한 bind() 함수를 호출합니다.
        val item = list[itemPosition]

        if (item is NativeAd2Item) {
            holder.bind(itemPosition)
        } else if (item is CarouselToFeedSlideItem) {
            holder.bindCarouselToFeedSlideItem(itemPosition)
        }
    }

    override fun onViewRecycled(holder: NativeAd2CarouselViewHolder) {
        super.onViewRecycled(holder)

        // position 대신 itemPosition을 사용합니다.
        val itemPosition = getItemPosition(holder.adapterPosition)

        // unbind를 반드시 호출하여 뷰를 재사용할 때 문제가 발생하지 않게 합니다.
        holder.unbind(itemPosition)
    }

    private fun getItemPosition(position: Int): Int {
        return if (isInfiniteLoopEnabled) {
            position % list.size
        } else {
            position
        }
    }
}
