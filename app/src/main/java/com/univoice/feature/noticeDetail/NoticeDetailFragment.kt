package com.univoice.feature.noticeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.univoice.feature.storage.StorageFragment
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
    private val debouncer = Debouncer<Int>()
    private var noticeId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val previousBackStackEntry = findNavController().previousBackStackEntry
        previousBackStackEntry?.destination?.id?.let { previousDestinationId ->
            when (previousDestinationId) {
                R.id.fragment_home -> noticeId = arguments?.getInt(HomeFragment.DETAIL_KEY)
                R.id.fragment_storage -> noticeId = arguments?.getInt(StorageFragment.STORAGE_KEY)
                else -> Timber.d("Unknown previous fragment")
            }
        }
        return view
    }

    override fun initView() {
        observeNoticeDetailBookmark()
        initLikeBtnClickListener()
        initBookMarkBtnClickListener()
        initBackBtnClickListener()
        getNoticeDetail()
        initNoticeDetailObserve()
    }

    private fun getNoticeDetail() {
        noticeId?.let {
            viewModel.getNoticeDetail(it)
            viewModel.postNoticeDetailViewCount(it)
        }
    }

    private fun initNoticeDetailObserve() {
        observeNoticeDetail()
    }

    private fun observeNoticeDetailBookmark() {
        viewModel.postNoticeDetailCancel.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Timber.tag("NoticeDetail").d(it.toString())
                is UiState.Success -> Timber.tag("NoticeDetail").d(it.toString())
                is UiState.Empty -> Timber.tag("NoticeDetail").d(it.toString())
                is UiState.Failure -> Timber.tag("NoticeDetail").d(it.msg)
            }
        }.launchIn(lifecycleScope)
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

            toolbarNoticeDetail.title = data.writeAffiliation
            tvNoticeDetailTitle.text = data.title
            tvNoticeDetailContent.text = data.content
            tvNoticeDetailViews.text = data.viewCount.toString()
            tvNoticeDetailTarget.text = data.target
            tvNoticeDetailCreateDate.text = CalculateDate().getCalculateDate(data.createdAt)
            tvNoticeDetailViews.text =
                context?.getString(R.string.tv_notice_detail_views, data.viewCount)
            tvNoticeDetailLikeCount.text = data.noticeLike.toString()

            if(data.likeCheck){
                btnNoticeDetailLike.isSelected = true
            }

            if(data.saveCheck){
                btnNoticeDetailBookmark.isSelected = true
            }

            if(data.target.isNullOrEmpty()){
                groupNoticeDetailTarget.visibility = View.GONE
            }

            tvNoticeDetailDate.let {
                groupNoticeDetailDate.visibility = View.VISIBLE
            }

            setNoticeDate(data.startTime, data.endTime)

            if (data.noticeImages.size > 2) {
                indicatorNoticeDetailImage.visibility = View.VISIBLE
            }
        }
    }

    private fun setNoticeDate(startTime: String?, endTime: String?) {
        if (startTime != null && endTime != null) {
            val startHasTime = CalculateDate().getHasTime(startTime)
            val endHasTime = CalculateDate().getHasTime(endTime)

            if (startHasTime && endHasTime) {
                val noticeDayString = CalculateDate().getCalculateNoticeDate(startTime, endTime)
                binding.tvNoticeDetailDate.text = noticeDayString
            } else {
                val noticeDayString = CalculateDate().getCalculateNoticeDay(startTime, endTime)
                binding.tvNoticeDetailDate.text = noticeDayString
            }
        } else {
            binding.groupNoticeDetailDate.visibility = View.GONE
        }
    }


    private fun initBackBtnClickListener() {
        binding.toolbarNoticeDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initLikeBtnClickListener() {
        with(binding) {
            btnNoticeDetailLike.setOnClickListener {
                if (!btnNoticeDetailLike.isSelected) {
                    noticeId?.let { id ->
                        tvNoticeDetailLikeCount.text = (tvNoticeDetailLikeCount.text.toString().toInt() + 1).toString()
                        viewModel.onLikeBtnClick(id)
                        }

                } else {
                    noticeId?.let { id ->
                        tvNoticeDetailLikeCount.text = (tvNoticeDetailLikeCount.text.toString().toInt() - 1).toString()
                       viewModel.onDisLikeBtnClick(id)
                    }
                }
                btnNoticeDetailLike.isSelected = !binding.btnNoticeDetailLike.isSelected
            }
        }
    }

    private fun initBookMarkBtnClickListener() {
        with(binding) {
            btnNoticeDetailBookmark.setOnClickListener {
                if (btnNoticeDetailBookmark.isSelected) {
                    noticeId?.let { id ->
                     viewModel.onBookmarkCancelBtnClick(id)
                    }
                } else {
                    noticeId?.let { id ->
                        viewModel.onBookmarkBtnClick(id)
                    }
                }
                btnNoticeDetailBookmark.isSelected = !binding.btnNoticeDetailBookmark.isSelected
            }
        }
    }

    private fun initNoticeDetailItemAdapter(noticeImgList: List<String>) {
        val adapter = NoticeDetailAdapter()
        binding.vpNoticeDetailImage.adapter = adapter
        adapter.submitList(noticeImgList)
        binding.indicatorNoticeDetailImage.setViewPager(binding.vpNoticeDetailImage)
    }
}