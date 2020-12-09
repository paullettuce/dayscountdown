package pl.paullettuce.dayscountdown.commons

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL

class RecyclerViewMargin(
    @DimenRes private val verticalMarginDp: Int = 0,
    @DimenRes private val horizontalMarginDp: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        val itemCount = parent.adapter?.itemCount ?: return
        val lastItemIndex = itemCount - 1
        setHorizontalMargins(position, lastItemIndex, outRect, parent)
        setVerticalMargins(position, lastItemIndex, outRect, parent)
    }

    private fun setHorizontalMargins(
        position: Int,
        lastItemIndex: Int,
        outRect: Rect,
        recView: RecyclerView
    ) {
        if (horizontalMarginDp == 0) return
        val horizontalMarginPx = recView.context.getMarginInPx(horizontalMarginDp)
        outRect.apply {
            if (position < lastItemIndex || recView.isVertical()) right = horizontalMarginPx
            if (position > 0 || recView.isVertical()) left = horizontalMarginPx
        }
    }

    private fun setVerticalMargins(
        position: Int,
        lastItemIndex: Int,
        outRect: Rect,
        recView: RecyclerView
    ) {
        if (verticalMarginDp == 0) return
        val verticalMarginPx = recView.context.getMarginInPx(verticalMarginDp)
        outRect.apply {
            if (position < lastItemIndex || recView.isHorizontal()) bottom = verticalMarginPx
            if (position > 0 || recView.isHorizontal()) top = verticalMarginPx
        }
    }

    private fun RecyclerView.isVertical() = orientation() == VERTICAL
    private fun RecyclerView.isHorizontal() = orientation() == HORIZONTAL
    private fun RecyclerView.orientation() = (this.layoutManager as LinearLayoutManager).orientation

    @Px
    private fun Context.getMarginInPx(@DimenRes dimenRes: Int) =
        this.resources.getDimensionPixelOffset(dimenRes)
}