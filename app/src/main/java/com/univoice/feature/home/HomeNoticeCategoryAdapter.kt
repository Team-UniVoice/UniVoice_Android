package com.univoice.feature.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.univoice.R
import com.univoice.core_ui.util.context.colorOf
import com.univoice.core_ui.view.ItemDiffCallback
import com.univoice.databinding.ItemHomeNoticeCategoryBinding

class HomeNoticeCategoryAdapter(
    private val click: (String, Int) -> Unit = { _, _ -> },
) :
    ListAdapter<String, HomeNoticeCategoryAdapter.HomeNoticeCategoryViewHolder>(
        HomeNoticeCategoryDiffCallback,
    ) {
    private var selectedCategoryIndex: Int = 0 // 초기에는 첫 번째 아이템을 선택

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeNoticeCategoryViewHolder {
        val binding =
            ItemHomeNoticeCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HomeNoticeCategoryViewHolder(binding, click)
    }

    override fun onBindViewHolder(
        holder: HomeNoticeCategoryViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedCategory(position: Int) {
        selectedCategoryIndex = position
        notifyDataSetChanged() // 선택된 탭을 갱신하기 위해 RecyclerView를 다시 그리도록 함
    }

    companion object {
        private val HomeNoticeCategoryDiffCallback =
            ItemDiffCallback<String>(
                onItemsTheSame = { old, new -> old == new },
                onContentsTheSame = { old, new -> old == new },
            )
    }

    inner class HomeNoticeCategoryViewHolder(
        private val binding: ItemHomeNoticeCategoryBinding,
        private val click: (String, Int) -> Unit = { _, _ -> },
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            with(binding) {
                tvHomeNoticeCategory.text = data

                root.setOnClickListener {
                    click(data, bindingAdapterPosition)
                    setSelectedCategory(adapterPosition) // 클릭된 탭의 위치를 설정
                }

                if (adapterPosition == selectedCategoryIndex) {
                    tvHomeNoticeCategory.setTextColor(root.context.colorOf(R.color.white))
                    layoutHomeNoticeCategory.setBackgroundResource(R.drawable.shape_mint400_fill_10_rect)

                } else {
                    tvHomeNoticeCategory.setTextColor(root.context.colorOf(R.color.gray_800))
                    layoutHomeNoticeCategory.setBackgroundResource(R.drawable.shape_gray50_fill_10_rect)
                }
            }
        }
    }

}
