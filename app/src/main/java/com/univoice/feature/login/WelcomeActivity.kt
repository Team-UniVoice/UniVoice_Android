package com.univoice.feature.login

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityWelcomeBinding
import com.univoice.feature.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : BindingActivity<ActivityWelcomeBinding>(R.layout.activity_welcome) {
    override fun initView() {
        initConfirmBtnClickListener()
    }

    private fun initConfirmBtnClickListener() {
        binding.btnWelcomeConfirm.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}