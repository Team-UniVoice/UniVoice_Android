package com.univoice.feature.quickscan

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.univoice.R
import com.univoice.databinding.ItemQuickScanBinding
import com.univoice.domain.entity.QuickScanEntity
import com.univoice.feature.util.CalculateTime

class QuickScanViewHolder(
    private val binding: ItemQuickScanBinding,
    private val onClick: (Int, Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: QuickScanEntity) {
        with(binding) {
            quickScan = data
            executePendingBindings()
            tvQuickScanTime.text = CalculateTime(itemView.context).getCalculateTime(data.time)
            ivQuickScanBookmark.isSelected = data.isBookmark
            ivQuickScanBookmark.setOnClickListener {
                val newBookmarkState = !data.isBookmark
                onClick(data.id, !data.isBookmark)
                ivQuickScanBookmark.isSelected = newBookmarkState
                data.isBookmark = newBookmarkState
            }
        }
    }
}