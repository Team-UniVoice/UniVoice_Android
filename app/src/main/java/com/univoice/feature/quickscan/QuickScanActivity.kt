package com.univoice.feature.quickscan

import android.content.Intent
import android.util.Log
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
    private var currentPage = 0
    override fun initView() {
        initToolbar()
        initToolbarClickListener()
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = QuickScanAdapter()
        binding.vpQuickScan.adapter = adapter
        adapter.submitList(viewModel.mockQuickScanList)
        initIndicator()
    }

    private fun initIndicator() {
        TabLayoutMediator(binding.indicatorQuickScan, binding.vpQuickScan) { _, _ -> }.attach()
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