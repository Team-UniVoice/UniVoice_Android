package com.univoice.feature.quickscan

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.ActivityQuickScanBinding
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.feature.home.HomeFragment.Companion.AFFILIATION_KEY
import com.univoice.feature.home.HomeFragment.Companion.IMAGE_KEY
import com.univoice.feature.util.Debouncer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class QuickScanActivity : BindingActivity<ActivityQuickScanBinding>(R.layout.activity_quick_scan) {
    private val viewModel by viewModels<QuickScanViewModel>()
    private val debouncer = Debouncer<Boolean>()
    override fun initView() {
        initToolbarClickListener()
        initPostQuickScanList()
        setupQuickScanObserve()
        addMarginsToTabs(binding.tabQuickScan)
    }

    private fun initPostQuickScanList() {
        val position = intent.getIntExtra(AFFILIATION_KEY, 0)
        val writeAffiliation = when (position) {
            0 -> "총학생회"
            1 -> "단과대학 학생회"
            else -> "학과 학생회"
        }
        viewModel.postQuickScanList(writeAffiliation)
    }

    private fun setupQuickScanObserve() {
        observeQuickScanList()
        observeQuickScanViewCheck()
    }

    private fun observeQuickScanViewCheck() {
        viewModel.postQuickScanViewCheck.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> Timber.tag("QuickScanSuccess").d(it.data.toString())
                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("QuickScanFailure").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun observeQuickScanList() {
        viewModel.postQuickScanList.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> initQuickScanAdapter(it.data)
                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("QuickScanFailure").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun addMarginsToTabs(tabLayout: TabLayout) {
        tabLayout.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            for (i in 0 until tabLayout.tabCount - 1) {
                val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
                val params = tab.layoutParams as ViewGroup.MarginLayoutParams
                when (tabLayout.tabCount) {
                    in MIN_TAB_COUNT_FOR_WIDE_MARGIN..MAX_TAB_COUNT_FOR_WIDE_MARGIN -> params.setMargins(
                        0,
                        0,
                        8,
                        0
                    )

                    in MIN_TAB_COUNT_FOR_MEDIUM_MARGIN..MAX_TAB_COUNT_FOR_MEDIUM_MARGIN -> params.setMargins(
                        0,
                        0,
                        6,
                        0
                    )

                    else -> params.setMargins(0, 0, 4, 0)
                }
                tab.requestLayout()
            }
        }

    }

    private fun initQuickScanAdapter(data: List<QuickScanListEntity>) {
        val image = intent.getStringExtra(IMAGE_KEY)
        if(image != null) {
            val adapter = QuickScanAdapter(image) { id, isChecked ->
                if(isChecked) {
                    debouncer.setDelay(isChecked, 1000L) {
                        viewModel.postQuickScanSave(id)
                    }
                } else {
                    debouncer.setDelay(isChecked, 1000L) {
                        viewModel.postQuickScanCancel(id)
                    }
                }
            }
            adapter.submitList(data)
            binding.vpQuickScan.adapter = adapter
            initTabLayout(data.size)
            initPageChangeCallback(adapter)
        }
    }

    private fun initPageChangeCallback(adapter: QuickScanAdapter) {
        var currentPos = 0
        var currentState = ViewPager2.SCROLL_STATE_IDLE
        binding.vpQuickScan.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (currentState == ViewPager2.SCROLL_STATE_DRAGGING) {
                    if (currentPos == adapter.itemCount - 1 && position == adapter.itemCount - 1) {
                        startActivity(
                            Intent(
                                this@QuickScanActivity,
                                QuickScanCompleteActivity::class.java
                            )
                        )
                    }
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPos = position
                viewModel.postQuickScanViewCheck(adapter.currentList[position].id)
                viewModel.postNoticeDetailViewCount(adapter.currentList[position].id)
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun initTabLayout(size: Int) {
        with(binding.tabQuickScan) {
            if (size > 1) {
                visibility = View.VISIBLE
            } else {
                visibility = View.INVISIBLE
            }
            TabLayoutMediator(this, binding.vpQuickScan) { tab, position ->
                tab.view.isClickable = false
            }.attach()
        }
    }

    private fun initToolbarClickListener() {
        binding.ibToolbarQuickScanIcon.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val MIN_TAB_COUNT_FOR_WIDE_MARGIN = 2
        const val MAX_TAB_COUNT_FOR_WIDE_MARGIN = 5
        const val MIN_TAB_COUNT_FOR_MEDIUM_MARGIN = 6
        const val MAX_TAB_COUNT_FOR_MEDIUM_MARGIN = 8
    }

}