package com.univoice.feature.noticeDetail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.FragmentNoticeDetailBinding
import com.univoice.domain.entity.NoticeDetailEntity
import com.univoice.feature.home.HomeFragment
import com.univoice.feature.util.CalculateDate
import com.univoice.feature.util.Debouncer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class NoticeDetailFragment :
    BindingFragment<FragmentNoticeDetailBinding>(R.layout.fragment_notice_detail) {
    private val viewModel by viewModels<NoticeDetailViewModel>()
    private val debouncer = Debouncer<String>()

    override fun initView() {
        initLikeBtnClickListener()
        initBookMarkBtnClickListener()
        initBackBtnClickListener()
        getNoticeDetail()
        initNoticeDetailObserve()
    }

    private fun getNoticeDetail() {
        val noticeId = arguments?.getInt(HomeFragment.DETAIL_KEY)
        noticeId?.let {
            viewModel.getNoticeDetail(it)
            viewModel.postNoticeDetailViewCount(it)
        }
    }

    private fun initNoticeDetailObserve() {
        observeNoticeDetail()
    }

    private fun observeNoticeDetail() {
        viewModel.getNoticeDetail.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    setTextNoticeDetail(it.data)
                    initNoticeDetailItemAdapter(it.data.noticeImages)
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("NoticeDetailFailure").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun setTextNoticeDetail(data: NoticeDetailEntity) {
        with(binding) {
            tvNoticeDetailTitle.text = data.title
            tvNoticeDetailDate.text = data.startTime
            tvNoticeDetailTarget.text = data.target
            tvNoticeDetailContent.text = data.content
            tvNoticeDetailViews.text = data.viewCount.toString()

            if (data.startTime != null && data.endTime != null)
                tvNoticeDetailDate.text =
                    CalculateDate().getCalculateNoticeDate(data.startTime, data.endTime)

            if (data.target == null)
                groupNoticeDetailTarget.visibility = View.GONE

            if (data.startTime == null)
                groupNoticeDetailDate.visibility = View.GONE

            if (data.noticeImages.isNotEmpty())
                indicatorNoticeDetailImage.visibility = View.VISIBLE
        }
    }

    private fun initBackBtnClickListener() {
        binding.toolbarNoticeDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    // 좋아요 버튼 클릭
    private fun initLikeBtnClickListener() {
        with(binding) {
            btnNoticeDetailLike.setOnClickListener {
                btnNoticeDetailLike.isSelected = !binding.btnNoticeDetailLike.isSelected
            }
        }
    }

    // 북마크 버튼 클릭
    private fun initBookMarkBtnClickListener() {
        val noticeId = arguments?.getInt(HomeFragment.DETAIL_KEY)
        with(binding) {
            btnNoticeDetailBookmark.setOnClickListener {
                if(btnNoticeDetailBookmark.isSelected) {
                    noticeId?.let {
                        debouncer.setDelay(it.toString(), 1000L) {
                            viewModel.postNoticeDetailCancel(noticeId)
                        }
                    }
                } else {
                    noticeId?.let {
                        debouncer.setDelay(it.toString(), 1000L) {
                            viewModel.postNoticeDetailSave(noticeId)
                        }
                    }
                }
                btnNoticeDetailBookmark.isSelected = !binding.btnNoticeDetailBookmark.isSelected
            }
        }
    }

    // Adapter 설정
    private fun initNoticeDetailItemAdapter(noticeImgList: List<String>) {
        val adapter = NoticeDetailAdapter()
        binding.vpNoticeDetailImage.adapter = adapter
        adapter.submitList(noticeImgList)
    }
}