package com.univoice.feature.post

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostViewPagerAdapter(private val items: List<String>) :
    RecyclerView.Adapter<PostViewPagerViewHolder>() {
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
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}