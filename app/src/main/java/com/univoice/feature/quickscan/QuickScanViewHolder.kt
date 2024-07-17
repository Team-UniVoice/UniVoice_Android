package com.univoice.feature.quickscan

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.univoice.databinding.ItemQuickScanBinding
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.feature.util.CalculateDate
import com.univoice.feature.util.CalculateTime

class QuickScanViewHolder(
    private val image: String,
    private val binding: ItemQuickScanBinding,
    private val onClick: (Int, Boolean) -> Unit = { _, _ -> },
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: QuickScanListEntity) {
        with(binding) {
            if(data.startTime == null && data.endTime == null) layoutQuickScanDate.visibility = View.GONE
            if(data.target == null) layoutQuickScanSubject.visibility = View.GONE

            tvQuickScanTime.text = CalculateTime(itemView.context).getCalculateTime(data.createdAt)
            tvQuickScanCouncilName.text = data.writeAffiliation
            tvQuickScanTitle.text = data.title
            tvQuickScanViewCount.text = "${data.viewCount}회"
            tvQuickScanSubjectContent.text = data.target
            tvQuickScanSummaryContent.text = data.contentSummary
            if(data.startTime != null && data.endTime != null)
                tvQuickScanDateContent.text = CalculateDate().getCalculateNoticeDate(data.startTime, data.endTime)

            ivQuickScanAvatar.load(image)
            ivQuickScanBookmark.isSelected = data.saveCheck
            ivQuickScanBookmark.setOnClickListener {
                val newBookmarkState = !data.saveCheck
                onClick(data.id, !data.saveCheck)
                ivQuickScanBookmark.isSelected = newBookmarkState
                data.saveCheck = newBookmarkState
            }
        }
    }
}