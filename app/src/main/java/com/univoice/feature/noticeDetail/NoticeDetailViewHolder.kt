package com.univoice.feature.noticeDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.univoice.databinding.ItemNoticeDetailImageBinding

class NoticeDetailViewHolder(private val binding: ItemNoticeDetailImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.ivNoticeDetailContent.load(item)
    }

    companion object {
        fun from(parent: ViewGroup): NoticeDetailViewHolder {
            val binding =
                ItemNoticeDetailImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return NoticeDetailViewHolder(binding)
        }
    }
}