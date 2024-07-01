package com.univoice.feature.example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemExampleBinding
import com.univoice.domain.entity.UserEntity

class ExampleAdapter(
    private val click: (UserEntity, Int) -> Unit = { _, _ -> }
) : ListAdapter<UserEntity, ExampleViewHolder>(
    ExampleDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val binding = ItemExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val ExampleDiffCallback =
            ItemDiffCallback<UserEntity>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new })
    }
}