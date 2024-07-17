package com.univoice.feature.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.activity.OnBackPressedCallback
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityWelcomeBinding
import com.univoice.feature.MainActivity
import com.univoice.feature.entry.EntryActivity

class WelcomeActivity : BindingActivity<ActivityWelcomeBinding>(R.layout.activity_welcome) {
    override fun initView() {
        backPressedListener()
        initConfirmBtnClickListener()
    }

    private fun backPressedListener() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@WelcomeActivity, EntryActivity::class.java).apply {
                    flags = FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK
                    startActivity(this)
                }
            }
        })
    }

    private fun initConfirmBtnClickListener() {
        binding.btnWelcomeConfirm.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
        }
    }
}