package com.univoice.feature.home

import android.content.Intent
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.util.fragment.toast
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.FragmentHomeBinding
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.feature.quickscan.QuickScanActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun initView() {
        initQuickscanObserve()
        initNoticeContentObserve()
        initPostBtnClickListener()
    }

    private fun initPostBtnClickListener() {
        binding.btnHomePost.setOnClickListener {
            findNavController().navigate(
                R.id.action_fragment_home_to_fragment_notice_post,
            )
        }
    }

    private fun initNoticeContentObserve() {
        homeViewModel.getNoticeContent.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    initNoticeContentAdapter(it.data)
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("hh").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initNoticeContentAdapter(noticeContentData: List<NoticeListEntity>) {
        binding.rvHomeNoticeContent.adapter =
            HomeNoticeContentAdapter(click = { noticeContent, position ->
                binding.root.findNavController().navigate(
                    R.id.action_fragment_home_to_fragment_notice_post,
                )
            }).apply {
                submitList(noticeContentData)
            }

        if (binding.rvHomeNoticeContent.itemDecorationCount == 0) {
            binding.rvHomeNoticeContent.addItemDecoration(
                HomeNoticeContentItemDecorator(requireContext())
            )
        }
    }

    private fun initQuickscanObserve() {
        homeViewModel.getQuickScanState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    iniQuickscanAdapter(it.data)
                    initNoticeCategoryAdapter(it.data.map { it.name })
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun initNoticeCategoryAdapter(categoryData: List<String>) {
        binding.rvHomeNoticeCategory.adapter =
            HomeNoticeCategoryAdapter(click = { category, position ->
                when (position) {
                    0 -> {
                        binding.tvHomeNoticeEmpty.visibility = View.GONE
                        homeViewModel.getNoticeContent()
                    }

                    1 -> binding.tvHomeNoticeEmpty.visibility = View.VISIBLE
                    2 -> binding.tvHomeNoticeEmpty.visibility = View.VISIBLE
                    3 -> binding.tvHomeNoticeEmpty.visibility = View.VISIBLE
                    else -> binding.tvHomeNoticeEmpty.visibility = View.VISIBLE
                }
            }).apply {
                (listOf("전체") + categoryData).also { submitList(it) }
            }

        if (binding.rvHomeNoticeCategory.itemDecorationCount == 0) {
            binding.rvHomeNoticeCategory.addItemDecoration(
                HomeNoticeCategoryItemDecorator(
                    requireContext()
                )
            )
        }
    }

    private fun iniQuickscanAdapter(quickscanData: List<QuickScanListEntity>) {
        binding.rvHomeQuickscan.adapter =
            HomeQuickscanAdapter(click = { quickscan, position ->
                startActivity(Intent(requireContext(), QuickScanActivity::class.java))
            }).apply {
                submitList(quickscanData)
            }

        if (binding.rvHomeQuickscan.itemDecorationCount == 0) {
            binding.rvHomeQuickscan.addItemDecoration(HomeQuickscanItemDecorator(requireContext()))
        }
    }
}