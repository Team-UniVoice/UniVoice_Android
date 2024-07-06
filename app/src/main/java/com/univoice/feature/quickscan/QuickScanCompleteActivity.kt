package com.univoice.feature.quickscan

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityQuickScanCompleteBinding
import com.univoice.feature.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuickScanCompleteActivity : BindingActivity<ActivityQuickScanCompleteBinding>(R.layout.activity_quick_scan_complete) {
    override fun initView() {
        initToolbar()
        initToolbarClickListener()
        initConfirmBtnClickListener()
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

    private fun initConfirmBtnClickListener() {
        binding.btnQuickScanConfirm.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}