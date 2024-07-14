package com.univoice.feature.entry

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityEntryBinding
import com.univoice.feature.example.xml.SignUpActivity
import com.univoice.feature.login.LoginActivity

class EntryActivity : BindingActivity<ActivityEntryBinding>(R.layout.activity_entry) {
    override fun initView() {
        initLoginBtnClickListener()
        initSignupBtnClickListener()
    }

    private fun initSignupBtnClickListener() {
        binding.btnEntrySignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btnEntryLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}