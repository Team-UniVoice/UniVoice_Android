package com.univoice.feature.quickscan

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityQuickScanCompleteBinding
import com.univoice.feature.MainActivity

class QuickScanCompleteActivity :
    BindingActivity<ActivityQuickScanCompleteBinding>(R.layout.activity_quick_scan_complete) {
    override fun initView() {
        initConfirmBtnClickListener()
    }

    private fun initConfirmBtnClickListener() {
        binding.btnQuickScanCompleteConfirm.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}