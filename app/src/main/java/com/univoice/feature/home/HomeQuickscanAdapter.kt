package com.univoice.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemHomeNoticeQuickscanBinding
import com.univoice.domain.entity.HomeQuickScanListEntity

class HomeQuickscanAdapter(
    private val click: (HomeQuickScanListEntity, Int) -> Unit = { _, _ -> },
) :
    ListAdapter<HomeQuickScanListEntity, HomeQuickscanViewHolder>(
        HomeQuickscanAdapterDiffCallback,
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeQuickscanViewHolder {
        val binding =
            ItemHomeNoticeQuickscanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HomeQuickscanViewHolder(binding, click)
    }

    override fun onBindViewHolder(
        holder: HomeQuickscanViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
    }

    companion object {
        private val HomeQuickscanAdapterDiffCallback =
            ItemDiffCallback<HomeQuickScanListEntity>(
                onItemsTheSame = { old, new -> old.name == new.name },
                onContentsTheSame = { old, new -> old == new },
            )
    }
}
