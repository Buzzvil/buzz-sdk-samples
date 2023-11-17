package com.buzzvil.sample.buzzvilsdksample.nativead.carousel

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PaddingDividerDecoration(private val paddingDp: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 여백을 한 쪽만 설정하면 PagerSnapHelper가 약간 어긋나게 동작하므로, 양쪽에 동일하게 여백을 넣습니다.
        val paddingPx = paddingDp.dpToPx() / 2
        outRect.left = paddingPx
        outRect.right = paddingPx
    }

    private fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }
}
