package com.buzzvil.buzzad.benefit.sample.publisher.carousel;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotion;
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionViewBinder;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd;
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdViewBinder;
import com.buzzvil.buzzad.benefit.sample.publisher.databinding.ViewItemCarouselBinding;

/**
 * Carousel을 구현하기 위한 Adapter
 * ListAdapter를 상속하여 구현하고 있지만, 원한다면 다른 방식으로 구현할 수도 있다.
 */
class CarouselAdapter extends ListAdapter<CarouselItem, CarouselAdapter.ViewHolder> {

    private final boolean isInfiniteLoopEnabled;

    /**
     * @param isInfiniteLoopEnabled: 무한 루프를 사용할지 설정하는 변수
     */
    public CarouselAdapter(final boolean isInfiniteLoopEnabled) {
        super(new CarouselDiff());
        this.isInfiniteLoopEnabled = isInfiniteLoopEnabled;
    }

    @Override
    public CarouselAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType
    ) {
        return new ViewHolder(ViewItemCarouselBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(final CarouselAdapter.ViewHolder holder, final int position) {
        final CarouselItem item = getItem(position);
        if (item instanceof CarouselItem.NativeAdItem) {
            holder.bind(((CarouselItem.NativeAdItem) item).nativeAd);
        } else if (item instanceof CarouselItem.CarouselToFeedSlideItem) {
            holder.bind(((CarouselItem.CarouselToFeedSlideItem) item).feedPromotion);
        }
    }

    @Override
    public void onViewRecycled(final ViewHolder holder) {
        super.onViewRecycled(holder);
        // unbind를 반드시 호출하여 NativeAd나 FeedPromotion을 재사용할 때 문제가 발생하지 않게 한다.
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        final int actualItemCount = super.getItemCount();
        if (isInfiniteLoopEnabled && actualItemCount > 0) {
            // 무한 루프를 쉽게 구현하는 방법으로 매우 큰 수를 여기서 반환한다.
            return Integer.MAX_VALUE;
        }
        return actualItemCount;
    }

    @Override
    public CarouselItem getItem(final int position) {
        int newPosition;
        if (isInfiniteLoopEnabled) {
            // 무한 루프인 경우의 position은 매우 큰 수이므로 실제 아이템 수로 나눈 나머지를 사용한다.
            newPosition = position % super.getItemCount();
        } else {
            newPosition = position;
        }
        return super.getItem(newPosition);
    }

    private static class CarouselDiff extends DiffUtil.ItemCallback<CarouselItem> {
        @Override
        public boolean areItemsTheSame(final CarouselItem oldItem, final CarouselItem newItem) {
            if (oldItem instanceof CarouselItem.NativeAdItem
                    && newItem instanceof CarouselItem.NativeAdItem
                    && ((CarouselItem.NativeAdItem) oldItem).nativeAd == ((CarouselItem.NativeAdItem) newItem).nativeAd) {
                return true;
            } else if (oldItem instanceof CarouselItem.CarouselToFeedSlideItem
                    && newItem instanceof CarouselItem.CarouselToFeedSlideItem) {
                return true;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(final CarouselItem oldItem, final CarouselItem newItem) {
            if (oldItem instanceof CarouselItem.NativeAdItem
                    && newItem instanceof CarouselItem.NativeAdItem
                    && ((CarouselItem.NativeAdItem) oldItem).nativeAd.getId() == ((CarouselItem.NativeAdItem) newItem).nativeAd.getId()) {
                return true;
            } else if (oldItem instanceof CarouselItem.CarouselToFeedSlideItem
                    && newItem instanceof CarouselItem.CarouselToFeedSlideItem) {
                return true;
            }
            return false;
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        // 레이아웃과 NativeAd를 연결해주는 ViewBinder
        private final NativeAdViewBinder nativeAdViewBinder;

        // 레이아웃과 FeedPromotion을 연결해주는 ViewBinder
        private final FeedPromotionViewBinder carouselToFeedSlideViewBinder;

        public ViewHolder(final ViewItemCarouselBinding binding) {
            super(binding.getRoot());

            nativeAdViewBinder = new NativeAdViewBinder.Builder(
                    binding.getRoot(),
                    binding.adMediaView
            )
                    .titleTextView(binding.adTitleText)
                    .descriptionTextView(binding.adDescriptionText)
                    .iconImageView(binding.adIconImage)
                    .ctaView(binding.adCtaView)
                    .build();

            carouselToFeedSlideViewBinder = new FeedPromotionViewBinder.Builder(
                    binding.getRoot(),
                    binding.adMediaView
            )
                    .titleTextView(binding.adTitleText)
                    .descriptionTextView(binding.adDescriptionText)
                    .iconImageView(binding.adIconImage)
                    .ctaView(binding.adCtaView)
                    .build();
        }

        /**
         * 레이아웃과 NativeAd를 연결한다.
         *
         * @param nativeAd: 레이아웃에 연결할 NativeAd 객체
         */
        void bind(final NativeAd nativeAd) {
            nativeAdViewBinder.bind(nativeAd);
        }

        /**
         * 레이아웃과 FeedPromotion을 연결하여 Carousel To Feed Slide를 만든다.
         *
         * @param feedPromotion: 레이아웃에 연결할 FeedPromotion 객체
         */
        void bind(final FeedPromotion feedPromotion) {
            carouselToFeedSlideViewBinder.bind(feedPromotion);
        }

        /**
         * 레이아웃과 NativeAd / FeedPromotion 간의 연결을 해제
         */
        void unbind() {
            this.nativeAdViewBinder.unbind();
            this.carouselToFeedSlideViewBinder.unbind();
        }
    }
}

