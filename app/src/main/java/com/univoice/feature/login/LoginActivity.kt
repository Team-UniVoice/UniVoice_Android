package com.univoice.feature.login

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityLoginBinding

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun initView() {
        initToolbar()
        initToolbarClickListener()
        initConfirmBtnClickListener()
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

    private fun initConfirmBtnClickListener() {
        binding.btnLoginConfirm.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}