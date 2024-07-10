package com.univoice.feature.quickscan

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityQuickScanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuickScanActivity : BindingActivity<ActivityQuickScanBinding>(R.layout.activity_quick_scan) {
    private val viewModel by viewModels<QuickScanViewModel>()
    override fun initView() {
        initToolbar()
        initToolbarClickListener()
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = QuickScanAdapter() {
            id, isBookmark -> viewModel.updateBookmark(id, isBookmark)
        }
        binding.vpQuickScan.adapter = adapter
        adapter.submitList(viewModel.mockQuickScanList)
        initTabLayout()
        initPageChangeCallback(adapter)
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
                        val intent =
                            Intent(this@QuickScanActivity, QuickScanCompleteActivity::class.java)
                        startActivity(intent)
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
        if (viewModel.mockQuickScanList.size == 1) {
            binding.tabQuickScan.visibility = View.INVISIBLE
        } else {
            binding.tabQuickScan.visibility = View.VISIBLE
        }
        TabLayoutMediator(binding.tabQuickScan, binding.vpQuickScan) { _, _ -> }.attach()
    }

    private fun initToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            tvToolbarTitle.text = getString(R.string.quick_scan_toolbar_title)
        }
    }

    private fun initToolbarClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}