package com.buzzvil.buzzad.benefit.sample.publisher.nativead2.carousel;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2View;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

import java.util.List;

public class NativeAd2CarouselAdapter extends RecyclerView.Adapter<NativeAd2CarouselViewHolder> {
    private String unitId;
    private List<NativeAd2CarouselItem> list;
    private NativeAd2Pool carouselPool;
    private boolean isInfiniteLoopEnabled;

    public NativeAd2CarouselAdapter(String unitId, List<NativeAd2CarouselItem> list, NativeAd2Pool carouselPool, boolean isInfiniteLoopEnabled) {
        this.unitId = unitId;
        this.list = list;
        this.carouselPool = carouselPool;
        this.isInfiniteLoopEnabled = isInfiniteLoopEnabled;
    }

    @NonNull
    @Override
    public NativeAd2CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NativeAd2View nativeAd2View = (NativeAd2View) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_native_ad2_item, parent, false);
        return new NativeAd2CarouselViewHolder(unitId, nativeAd2View);
    }

    @Override
    public void onBindViewHolder(@NonNull NativeAd2CarouselViewHolder holder, int position) {
        int itemPosition = getItemPosition(position);

        // 해당 position에 해당하는 NativeAd2ViewBinder가 carouselPool을 사용하도록 합니다.
        holder.setPool(carouselPool, itemPosition);

        // 아이템의 타입을 구분하여 적절한 bind() 함수를 호출합니다.
        NativeAd2CarouselItem item = list.get(itemPosition);

        if (item instanceof NativeAd2CarouselItem.NativeAd2Item) {
            holder.bind(itemPosition);
        } else if (item instanceof NativeAd2CarouselItem.CarouselToFeedSlideItem) {
            holder.bind(itemPosition, ((NativeAd2CarouselItem.CarouselToFeedSlideItem) item).feedPromotion);
        }
    }

    @Override
    public void onViewRecycled(NativeAd2CarouselViewHolder holder) {
        super.onViewRecycled(holder);
        int itemPosition = getItemPosition(holder.getAdapterPosition());

        // unbind를 반드시 호출하여 뷰를 재사용할 때 문제가 발생하지 않게 합니다.
        holder.unbind(itemPosition);
    }


    @Override
    public int getItemCount() {
        int actualItemCount = list.size();
        if (isInfiniteLoopEnabled && actualItemCount > 0) {
            // 무한 루프를 쉽게 구현하는 방법으로 매우 큰 수를 여기서 반환합니다.
            return Integer.MAX_VALUE;
        }
        return actualItemCount;
    }

    private int getItemPosition(int position) {
        if (isInfiniteLoopEnabled) {
            return position % list.size();
        } else {
            return position;
        }
    }
}
