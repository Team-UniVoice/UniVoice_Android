package com.univoice.feature.noticeDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.univoice.databinding.ItemNoticeDetailImageBinding

class NoticeDetailViewHolder(private val binding: ItemNoticeDetailImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(image: String) {
        with(binding) {
            if (image.isEmpty()) {
                ivNoticeDetailContent.visibility = View.GONE
            } else {
                ivNoticeDetailContent.visibility = View.VISIBLE
                ivNoticeDetailContent.load(image)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): NoticeDetailViewHolder {
            val binding = ItemNoticeDetailImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return NoticeDetailViewHolder(binding)
        }
    }
}
