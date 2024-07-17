package com.univoice.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemHomeNoticeContentBinding
import com.univoice.domain.entity.NoticeListEntity

class HomeNoticeContentAdapter(
    private val click: (NoticeListEntity, Int) -> Unit = { _, _ -> },
) : ListAdapter<NoticeListEntity, HomeNoticeContentViewHolder>(
    HomeNoticeContentDiffCallback,
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeNoticeContentViewHolder {
        val binding =
            ItemHomeNoticeContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HomeNoticeContentViewHolder(binding, click)
    }

    override fun onBindViewHolder(
        holder: HomeNoticeContentViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
    }

    companion object {
        private val HomeNoticeContentDiffCallback =
            ItemDiffCallback<NoticeListEntity>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new },
            )
    }
}
