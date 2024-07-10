package com.univoice.feature.quickscan

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.univoice.R
import com.univoice.databinding.ItemQuickScanBinding
import com.univoice.domain.entity.QuickScanEntity

class QuickScanViewHolder(
    private val binding: ItemQuickScanBinding,
    private val onClick: (Int, Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root){
    fun bind(data: QuickScanEntity) {
        with(binding) {
            quickScan = data
            executePendingBindings()
        }
        with(binding) {
            ivQuickScanBookmark.isSelected = data.isBookmark
            ivQuickScanBookmark.setOnClickListener {
                onClick(data.id, !data.isBookmark)
                ivQuickScanBookmark.isSelected = !data.isBookmark
            }
        }
    }
}