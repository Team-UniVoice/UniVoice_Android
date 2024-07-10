package com.univoice.feature.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.univoice.databinding.ItemPostImageBinding

class PostViewPagerViewHolder(private val binding: ItemPostImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.ivPostContent.load(item)
    }

    companion object {
        fun from(parent: ViewGroup): PostViewPagerViewHolder {
            val binding =
                ItemPostImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return PostViewPagerViewHolder(binding)
        }
    }
}