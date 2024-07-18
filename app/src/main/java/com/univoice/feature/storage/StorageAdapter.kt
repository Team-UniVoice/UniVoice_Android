package com.univoice.feature.storage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemNoticeBinding
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.StorageListEntity

class StorageAdapter(
    private val onClick: (StorageListEntity) -> Unit = { _ -> },
) : ListAdapter<StorageListEntity, StorageViewHolder>(
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
            ItemDiffCallback<StorageListEntity>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new })
    }
}