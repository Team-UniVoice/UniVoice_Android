package com.univoice.feature.quickscan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemQuickScanBinding
import com.univoice.domain.entity.QuickScanListEntity

class QuickScanAdapter(
    private val onClick: (Int, Boolean) -> Unit = { _, _ -> },
) : ListAdapter<QuickScanListEntity, QuickScanViewHolder>(
    QuickScanDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickScanViewHolder {
        val binding =
            ItemQuickScanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuickScanViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: QuickScanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val QuickScanDiffCallback =
            ItemDiffCallback<QuickScanListEntity>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new })
    }
}