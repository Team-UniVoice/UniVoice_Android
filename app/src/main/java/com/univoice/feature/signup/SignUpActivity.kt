package com.univoice.feature.signup

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivitySignupBinding
import com.univoice.feature.util.setupToolbarClickListener

class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    override fun initView() {
        initStartBtnClickListener()
        initToolbar()
    }

    private fun initToolbar() {
        with(binding.toolbarSignup) {
            tvToolbarTitle.text = applicationContext.getString(R.string.tv_toolbar_signup_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initStartBtnClickListener() {
        binding.btnSignupStart.setOnClickListener {
            startActivity(Intent(this, SchoolInputActivity::class.java))
        }
    }
}