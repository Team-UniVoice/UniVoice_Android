package com.univoice.feature.quickscan

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityQuickScanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuickScanActivity : BindingActivity<ActivityQuickScanBinding>(R.layout.activity_quick_scan) {
    private val viewModel by viewModels<QuickScanViewModel>()
    override fun initView() {
        initToolbarClickListener()
        initQuickScanAdapter()
        addMarginsToTabs(binding.tabQuickScan)
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

    private fun initQuickScanAdapter() {
        QuickScanAdapter() { id, isBookmark ->
            viewModel.updateBookmark(id, isBookmark)
        }.also {
            binding.vpQuickScan.adapter = it
            it.submitList(viewModel.mockQuickScanList)
            initTabLayout()
            initPageChangeCallback(it)
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
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun initTabLayout() {
        with(binding.tabQuickScan) {
            if (viewModel.mockQuickScanList.size > 1) {
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