package com.univoice.feature.home

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.univoice.core_ui.util.context.pxToDp

class HomeNoticeContentItemDecorator(val context: Context) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        when (position) {
            0 -> {
                outRect.top = context.pxToDp(16)
                outRect.bottom = context.pxToDp(12)
            }

            itemCount - 1 -> outRect.bottom = context.pxToDp(16)

            else -> outRect.bottom = context.pxToDp(12)
        }
    }
}
