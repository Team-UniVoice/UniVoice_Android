package com.univoice.feature.example.xml

import androidx.recyclerview.widget.RecyclerView
import com.univoice.databinding.ItemExampleBinding
import com.univoice.domain.entity.UserEntity

class ExampleViewHolder(
    private val binding: ItemExampleBinding,
    private val click: (UserEntity, Int) -> Unit = { _, _ -> }
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: UserEntity) {
        with(binding) {
            example = data
            executePendingBindings()
            binding.root.setOnClickListener {
                click(data, adapterPosition)
            }
        }
    }
}