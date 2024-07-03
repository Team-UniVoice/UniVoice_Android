package com.univoice.feature.login

import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityLoginBinding

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun initView() {
        initToolbar()
        initToolbarClickListener()
    }

    private fun initToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            tvToolbarTitle.text = getString(R.string.login_toolbar_title)
        }
    }

    private fun initToolbarClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}