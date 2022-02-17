package com.buzzvil.buzzad.benefit.sample.publisher.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionViewBinder
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdViewBinder
import com.buzzvil.buzzad.benefit.sample.publisher.databinding.ViewItemCarouselBinding

/**
 * Carousel을 구현하기 위한 Adapter
 * ListAdapter를 상속하여 구현하고 있지만, 원한다면 다른 방식으로 구현할 수도 있다.
 *
 * @param infiniteLoop: 무한 스크롤을 사용할지 설정하는 변수
 */
class CarouselAdapter(
        private val infiniteLoop: Boolean,
) :
        ListAdapter<CarouselItem, CarouselAdapter.ViewHolder>(CarouselDiff()) {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CarouselAdapter.ViewHolder {
        return ViewHolder(ViewItemCarouselBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CarouselAdapter.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is CarouselItem.NativeAdItem -> {
                holder.bind(item.nativeAd)
            }
            is CarouselItem.CarouselToFeedSlideItem -> {
                holder.bind(item.feedPromotion)
            }
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        // unbind를 반드시 호출하여 NativeAd나 FeedPromotion을 재사용할 때 문제가 발생하지 않게 한다.
        holder.unbind()
    }

    override fun getItemCount(): Int {
        val actualItemCount = super.getItemCount()
        if (infiniteLoop && actualItemCount > 0) {
            // 무한 스크롤을 쉽게 구현하는 방법으로 매우 큰 수를 여기서 반환한다.
            return Integer.MAX_VALUE
        }
        return actualItemCount
    }

    override fun getItem(position: Int): CarouselItem {
        return super.getItem(
                if (infiniteLoop) {
                    // 무한 스크롤인 경우의 position은 매우 큰 수이므로 실제 아이템 수로 나눈 나머지를 사용한다.
                    position % super.getItemCount()
                } else {
                    position
                }
        )
    }

    private class CarouselDiff : DiffUtil.ItemCallback<CarouselItem>() {
        override fun areItemsTheSame(oldItem: CarouselItem, newItem: CarouselItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CarouselItem, newItem: CarouselItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(binding: ViewItemCarouselBinding) :
            RecyclerView.ViewHolder(binding.root) {

        // 레이아웃과 NativeAd를 연결해주는 ViewBinder
        private val nativeAdViewBinder = NativeAdViewBinder.Builder(
                binding.root,
                binding.adMediaView
        )
                .titleTextView(binding.adTitleText)
                .descriptionTextView(binding.adDescriptionText)
                .iconImageView(binding.adIconImage)
                .ctaView(binding.adCtaView)
                .build()

        // 레이아웃과 FeedPromotion을 연결해주는 ViewBinder
        private val carouselToFeedSlideViewBinder = FeedPromotionViewBinder.Builder(
                binding.root,
                binding.adMediaView
        )
                .titleTextView(binding.adTitleText)
                .descriptionTextView(binding.adDescriptionText)
                .iconImageView(binding.adIconImage)
                .ctaView(binding.adCtaView)
                .build()

        /**
         * 레이아웃과 NativeAd를 연결한다.
         *
         * @param nativeAd: 레이아웃에 연결할 NativeAd 객체
         */
        fun bind(nativeAd: NativeAd) {
            nativeAdViewBinder.bind(nativeAd)
        }

        /**
         * 레이아웃과 FeedPromotion을 연결하여 Carousel To Feed Slide를 만든다.
         *
         * @param feedPromotion: 레이아웃에 연결할 FeedPromotion 객체
         */
        fun bind(feedPromotion: FeedPromotion) {
            carouselToFeedSlideViewBinder.bind(feedPromotion)
        }

        /**
         * 레이아웃과 NativeAd / FeedPromotion 간의 연결을 해제
         */
        fun unbind() {
            this.nativeAdViewBinder.unbind()
            this.carouselToFeedSlideViewBinder.unbind()
        }
    }
}

