package com.univoice.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.univoice.databinding.ItemHomeNoticeCategoryBinding

class HomeNoticeCategoryViewHolder(
    private val binding: ItemHomeNoticeCategoryBinding,
    private val click: (String, Int) -> Unit = { _, _ -> },
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String) {
        with(binding) {
            tvHomeNoticeCategory.text = data

            root.setOnClickListener {
                click(data, bindingAdapterPosition)
            }
        }
    }
}
