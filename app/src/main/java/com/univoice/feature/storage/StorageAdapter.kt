package com.univoice.feature.storage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemNoticeBinding
import com.univoice.domain.entity.NoticeListEntity

class StorageAdapter(
    private val onClick: (NoticeListEntity, Int) -> Unit = { _, _ -> },
) : ListAdapter<NoticeListEntity, StorageViewHolder>(
    StorageAdapterDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StorageViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: StorageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val StorageAdapterDiffCallback =
            ItemDiffCallback<NoticeListEntity>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new })
    }
}