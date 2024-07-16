package com.univoice.feature.post.dateTimePicker.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.univoice.R

class CustomSnapHelper(
    private val rv: RecyclerView,
    private val snapListener: SnapListener
) : LinearSnapHelper() {

    init {
        this.attachToRecyclerView(rv)
    }

    private var lastSelectedPosition = RecyclerView.NO_POSITION

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        val view = super.findSnapView(layoutManager)

        if (view != null) {
            val newPosition = layoutManager?.getPosition(view) ?: RecyclerView.NO_POSITION
            if (newPosition != lastSelectedPosition && rv.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                lastSelectedPosition = newPosition
                snapListener.onViewSnapped(newPosition)

                setFont(newPosition)
            }
        }

        return view
    }

    private fun setFont(newPosition: Int) {
        val context = rv.context
        val gray300 = ContextCompat.getColor(context, R.color.gray_300)
        val mint400 = ContextCompat.getColor(context, R.color.mint_700)

        // 새로운 선택된 뷰의 텍스트 색을 민트색으로 변경
        val currentViewHolder = rv.findViewHolderForAdapterPosition(newPosition) as? DateViewHolder
        currentViewHolder?.binding?.tvDateNumber?.setTextColor(mint400)

        // 선택된 위치를 업데이트
        lastSelectedPosition = newPosition

        // 모든 항목의 텍스트 색 gray300으로 변경 (선택된 위치 제외)
        val adapterItemCount = rv.adapter?.itemCount ?: 0
        for (i in 0 until adapterItemCount) {
            val viewHolder = rv.findViewHolderForAdapterPosition(i) as? DateViewHolder
            if (i == newPosition) {
                viewHolder?.binding?.tvDateNumber?.setTextColor(mint400)
            } else {
                viewHolder?.binding?.tvDateNumber?.setTextColor(gray300)
            }
        }
    }

    interface SnapListener {
        fun onViewSnapped(position: Int)
    }
}