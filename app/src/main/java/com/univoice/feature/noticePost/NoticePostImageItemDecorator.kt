package com.univoice.feature.noticePost

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.univoice.core_ui.util.context.pxToDp

class NoticePostImageItemDecorator(val context: Context) : RecyclerView.ItemDecoration() {
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
                outRect.left = context.pxToDp(16)
                outRect.right = context.pxToDp(12)
            }

            itemCount - 1 -> outRect.right = context.pxToDp(16)

            else -> outRect.right = context.pxToDp(12)
        }
    }
}
