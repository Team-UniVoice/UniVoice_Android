package com.univoice.feature.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.univoice.databinding.ItemNoticeBinding
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.feature.util.CalculateDate

class HomeNoticeViewHolder(
    private val binding: ItemNoticeBinding,
    private val onClick: (NoticeListEntity, Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: NoticeListEntity) {
        with(binding) {
            btnItemNoticeHeader.text = data.subTitle
            tvItemNoticeTitle.text = data.title
            tvItemNoticeDate.text = CalculateDate().getCalculateDate(data.date)
            tvItemNoticeLike.text = data.like.toString()
            tvItemNoticeViews.text = data.views.toString()
            ivItemNoticeThumbnail.load(data.image) {
                transformations(RoundedCornersTransformation(5f))
            }
            root.setOnClickListener {
                onClick(data, layoutPosition)
            }
        }
    }
}