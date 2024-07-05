package com.univoice.feature.quickscan

import androidx.compose.ui.unit.dp
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.univoice.databinding.ItemQuickScanBinding
import com.univoice.domain.entity.QuickScanEntity

class QuickScanViewHolder(
    private val binding: ItemQuickScanBinding,
) : RecyclerView.ViewHolder(binding.root){
    fun bind(data: QuickScanEntity) {
        with(binding) {
            quickScan = data
            executePendingBindings()
        }
    }
}