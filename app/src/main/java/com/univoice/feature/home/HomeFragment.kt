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

    private var clickedCategoryIndex: Int = 0

    override fun initView() {
        initQuickscanObserve()

        initNoticeAllObserve()
        initNoticeUniversityObserve()
        initNoticeCollegeObserve()
        initNoticeDepartmentObserve()

        initPostBtnClickListener()
    }

    private fun initPostBtnClickListener() {
        binding.btnHomePost.setOnClickListener {
            findNavController().navigate(
                R.id.action_fragment_home_to_fragment_notice_post,
            )
        }
    }

    private fun initNoticeDepartmentObserve() {
        homeViewModel.getNoticeDepartmentState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    initNoticeContentAdapter(it.data)
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("HomeFragment").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initNoticeCollegeObserve() {
        homeViewModel.getNoticeCollegeState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    initNoticeContentAdapter(it.data)
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("HomeFragment").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initNoticeUniversityObserve() {
        homeViewModel.getNoticeUniversityState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    initNoticeContentAdapter(it.data)
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("HomeFragment").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initNoticeAllObserve() {
        homeViewModel.getNoticeAllState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> initNoticeContentAdapter(it.data)
                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("HomeFragment").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initNoticeContentAdapter(noticeContentData: List<NoticeListEntity>) {
        binding.rvHomeNoticeContent.adapter =
            HomeNoticeContentAdapter(click = { _, _ ->
                binding.root.findNavController().navigate(
                    R.id.action_fragment_home_to_fragment_notice_detail,
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

    private fun initNoticeCategoryAdapter(categoryData: List<String>) {
        binding.rvHomeNoticeCategory.adapter =
            HomeNoticeCategoryAdapter(click = { _, position ->
                clickedCategoryIndex = position

                when (position) {
                    0 -> {
                        binding.tvHomeNoticeEmpty.visibility = View.GONE
                        homeViewModel.getNoticeAll()
                    }

                    1 -> {
                        binding.tvHomeNoticeEmpty.visibility = View.GONE
                        homeViewModel.getNoticeUniversity()
                    }

                    2 -> {
                        binding.tvHomeNoticeEmpty.visibility = View.GONE
                        homeViewModel.getNoticeCollege()
                    }

                    3 -> {
                        binding.tvHomeNoticeEmpty.visibility = View.GONE
                        homeViewModel.getNoticeDepartment()
                    }

                    else -> {
                        binding.tvHomeNoticeEmpty.visibility = View.VISIBLE
                        binding.rvHomeNoticeContent.visibility = View.INVISIBLE
                    }
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

    private fun initQuickscanObserve() {
        homeViewModel.getQuickScanState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    iniQuickscanAdapter(it.data)
                    initNoticeCategoryAdapter(it.data.map { it.name })
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("HomeFragment").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun iniQuickscanAdapter(quickscanData: List<QuickScanListEntity>) {
        binding.rvHomeQuickscan.adapter =
            HomeQuickscanAdapter(click = { _, _ ->
                startActivity(Intent(requireContext(), QuickScanActivity::class.java))
            }).apply {
                submitList(quickscanData)
            }

        if (binding.rvHomeQuickscan.itemDecorationCount == 0) {
            binding.rvHomeQuickscan.addItemDecoration(HomeQuickscanItemDecorator(requireContext()))
        }
    }
}