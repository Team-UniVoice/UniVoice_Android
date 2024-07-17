package com.univoice.feature.noticeDetail

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback

class NoticeDetailAdapter :
    ListAdapter<String, NoticeDetailViewHolder>(
        NoticeDetailItemDiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeDetailViewHolder {
        return NoticeDetailViewHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: NoticeDetailViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val NoticeDetailItemDiffCallback =
            ItemDiffCallback<String>(onItemsTheSame = { old, new -> old == new },
                onContentsTheSame = { old, new -> old == new })
    }
}