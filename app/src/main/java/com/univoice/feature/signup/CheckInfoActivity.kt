package com.univoice.feature.signup

import android.content.Intent
import androidx.compose.ui.input.key.Key.Companion.I
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityCheckInfoBinding
import com.univoice.feature.entry.EntryActivity

class CheckInfoActivity :
    BindingActivity<ActivityCheckInfoBinding>(R.layout.activity_check_info) {

    override fun initView() {
        initButtonClickListener()
    }

    private fun initButtonClickListener() {
        binding.btnCheckInfoStart.setOnClickListener {
            Intent(this, EntryActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
        }
    }
}
