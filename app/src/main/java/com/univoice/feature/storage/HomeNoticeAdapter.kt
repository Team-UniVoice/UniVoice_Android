package com.univoice.feature.storage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemNoticeBinding
import com.univoice.domain.entity.NoticeListEntity

class HomeNoticeAdapter(
    private val onClick: (NoticeListEntity, Int) -> Unit
) : ListAdapter<NoticeListEntity, HomeNoticeViewHolder>(
    HomeNoticeDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeNoticeViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: HomeNoticeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val HomeNoticeDiffCallback =
            ItemDiffCallback<NoticeListEntity>(
                onItemsTheSame = { old, new -> old.title == new.title },
                onContentsTheSame = { old, new -> old == new })
    }
}