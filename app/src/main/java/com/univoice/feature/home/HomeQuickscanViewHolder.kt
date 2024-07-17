package com.univoice.feature.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.univoice.databinding.ItemHomeNoticeQuickscanBinding
import com.univoice.domain.entity.HomeQuickScanListEntity

class HomeQuickscanViewHolder(
    private val binding: ItemHomeNoticeQuickscanBinding,
    private val click: (HomeQuickScanListEntity, Int) -> Unit = { _, _ -> },
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: HomeQuickScanListEntity) {
        with(binding) {
            if (data.count < 1) {
                layoutHomeNoticeQuickscanCount.visibility = View.INVISIBLE
                tvHomeNoticeQuickscanCount.visibility = View.INVISIBLE
            } else {
                tvHomeNoticeQuickscanCount.visibility = View.VISIBLE
                tvHomeNoticeQuickscanCount.text = data.count.toString()
            }

            tvHomeNoticeQuickscanName.text = data.name
            ivHomeNoticeQuickscanProfile.load(data.image)

            root.setOnClickListener {
                click(data, bindingAdapterPosition)
            }
        }
    }
}
