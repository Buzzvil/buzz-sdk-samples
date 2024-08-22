package com.buzzvil.buzzad.benefit.sample.publisher.nativead2.carousel;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaddingDividerDecoration extends RecyclerView.ItemDecoration {
    private final int paddingDp;

    public PaddingDividerDecoration(int paddingDp) {
        this.paddingDp = paddingDp;
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect,
            @NonNull View view,
            @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state
    ) {
        // 여백을 한 쪽만 설정하면 PagerSnapHelper가 약간 어긋나게 동작하므로, 양쪽에 동일하게 여백을 넣습니다.
        int paddingPx = dpToPx(paddingDp / 2);
        outRect.left = paddingPx;
        outRect.right = paddingPx;
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
