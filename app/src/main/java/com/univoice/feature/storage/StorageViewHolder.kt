package com.univoice.feature.storage

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.univoice.databinding.ItemNoticeBinding
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.feature.util.CalculateDate

class StorageViewHolder(
    private val binding: ItemNoticeBinding,
    private val onClick: (NoticeListEntity, Int) -> Unit = { _, _ -> },
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: NoticeListEntity) {
        with(binding) {
            btnItemNoticeHeader.text = data.category
            tvItemNoticeTitle.text = data.title
            tvItemNoticeDate.text = CalculateDate().getCalculateDate(data.date)
            tvItemNoticeLike.text = data.likeCount.toString()
            tvItemNoticeViews.text = data.viewCount.toString()
            ivItemNoticeThumbnail.load(data.image) {
                transformations(RoundedCornersTransformation(5f))
            }
            root.setOnClickListener {
                onClick(data, layoutPosition)
            }
        }
    }
}