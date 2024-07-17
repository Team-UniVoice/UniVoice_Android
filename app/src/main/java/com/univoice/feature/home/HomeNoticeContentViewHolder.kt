package com.univoice.feature.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.univoice.databinding.ItemHomeNoticeContentBinding
import com.univoice.domain.entity.NoticeListEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeNoticeContentViewHolder(
    private val binding: ItemHomeNoticeContentBinding,
    private val click: (NoticeListEntity, Int) -> Unit = { _, _ -> },
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: NoticeListEntity) {
        with(binding) {
            btnHomeNoticeContentCategory.text = data.category
            tvHomeNoticeContentTitle.text = data.title
            tvHomeNoticeContentLike.text = data.likeCount.toString()
            tvHomeNoticeContentView.text = data.viewCount.toString()

            val localDate = LocalDate.parse(data.date.substringBefore('T'))
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            tvHomeNoticeContentDate.text = localDate.format(outputFormatter)

            if (data.image.isEmpty()) {
                ivHomeNoticeContent.visibility = View.INVISIBLE
            } else {
                ivHomeNoticeContent.load(data.image) {
                    ivHomeNoticeContent.visibility = View.VISIBLE
                    transformations(RoundedCornersTransformation(5f))
                }
            }

            root.setOnClickListener {
                click(data, bindingAdapterPosition)
            }
        }
    }
}
