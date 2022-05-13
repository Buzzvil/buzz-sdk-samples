package com.buzzvil.buzzad.benefit.sample.publisher.carousel;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 아이템 사이의 간격을 넣기 위한 ItemDecoration
 */
class PaddingDividerDecoration extends RecyclerView.ItemDecoration {
    private final int paddingDp;

    /**
     * @param paddingDp: 아이템 간의 간격, 단위는 dp
     */
    public PaddingDividerDecoration(final int paddingDp) {
        this.paddingDp = paddingDp;
    }

    @Override
    public void getItemOffsets(
            final Rect outRect,
            final View view,
            final RecyclerView parent,
            final RecyclerView.State state
    ) {
        // 여백을 한 쪽만 설정하면 PagerSnapHelper가 약간 어긋나게 동작하므로, 양쪽에 동일하게 여백을 넣는다.
        outRect.left = dpToPx(paddingDp / 2);
        outRect.right = dpToPx(paddingDp / 2);
    }

    private int dpToPx(final int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
