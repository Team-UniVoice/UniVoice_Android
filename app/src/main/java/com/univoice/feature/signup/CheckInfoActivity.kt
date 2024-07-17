package com.univoice.feature.signup

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.activity.OnBackPressedCallback
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityCheckInfoBinding
import com.univoice.feature.entry.EntryActivity

class CheckInfoActivity :
    BindingActivity<ActivityCheckInfoBinding>(R.layout.activity_check_info) {

    override fun initView() {
        backPressedListener()
        initButtonClickListener()
    }

    private fun backPressedListener() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@CheckInfoActivity, EntryActivity::class.java).apply {
                    flags = FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK
                    startActivity(this)
                }
            }
        })
    }

    private fun initButtonClickListener() {
        binding.btnCheckInfoStart.setOnClickListener {
            Intent(this, EntryActivity::class.java).apply {
                flags = FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
        }
    }
}
