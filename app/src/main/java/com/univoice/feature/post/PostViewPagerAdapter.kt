package com.univoice.feature.post

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback

class PostViewPagerAdapter :
    ListAdapter<String, PostViewPagerViewHolder>(
        PostItemDiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewPagerViewHolder {
        return PostViewPagerViewHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: PostViewPagerViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }


    companion object {
        private val PostItemDiffCallback =
            ItemDiffCallback<String>(onItemsTheSame = { old, new -> old == new },
                onContentsTheSame = { old, new -> old == new })
    }
}