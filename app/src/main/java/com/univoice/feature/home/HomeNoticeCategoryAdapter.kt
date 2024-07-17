package com.univoice.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemHomeNoticeCategoryBinding

class HomeNoticeCategoryAdapter(
    private val click: (String, Int) -> Unit = { _, _ -> },
) :
    ListAdapter<String, HomeNoticeCategoryViewHolder>(
        HomeNoticeCategoryDiffCallback,
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeNoticeCategoryViewHolder {
        val binding =
            ItemHomeNoticeCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HomeNoticeCategoryViewHolder(binding, click)
    }

    override fun onBindViewHolder(
        holder: HomeNoticeCategoryViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
    }

    companion object {
        private val HomeNoticeCategoryDiffCallback =
            ItemDiffCallback<String>(
                onItemsTheSame = { old, new -> old == new },
                onContentsTheSame = { old, new -> old == new },
            )
    }
}
