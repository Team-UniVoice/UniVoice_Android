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

        view?.let {
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

        val currentViewHolder = rv.findViewHolderForAdapterPosition(newPosition) as? DateViewHolder
        currentViewHolder?.binding?.tvDateNumber?.setTextColor(ContextCompat.getColor(rv.context, R.color.mint_700))

        lastSelectedPosition = newPosition

        val adapterItemCount = rv.adapter?.itemCount ?: 0
        for (i in 0 until adapterItemCount) {
            val viewHolder = rv.findViewHolderForAdapterPosition(i) as? DateViewHolder
            if (i == newPosition) {
                viewHolder?.binding?.tvDateNumber?.setTextColor(ContextCompat.getColor(rv.context, R.color.mint_700))
            } else {
                viewHolder?.binding?.tvDateNumber?.setTextColor(ContextCompat.getColor(rv.context, R.color.gray_300))
            }
        }
    }

    interface SnapListener {
        fun onViewSnapped(position: Int)
    }
}