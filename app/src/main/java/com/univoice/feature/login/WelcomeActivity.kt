package com.univoice.feature.login

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityWelcomeBinding
import com.univoice.feature.MainActivity

class WelcomeActivity : BindingActivity<ActivityWelcomeBinding>(R.layout.activity_welcome) {
    override fun initView() {
        initConfirmBtnClickListener()
    }

    private fun initConfirmBtnClickListener() {
        binding.btnWelcomeConfirm.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }.let { startActivity(it) }
        }
    }
}