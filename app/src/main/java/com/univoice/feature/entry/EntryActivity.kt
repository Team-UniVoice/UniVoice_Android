package com.univoice.feature.entry

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityEntryBinding
import com.univoice.feature.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryActivity : BindingActivity<ActivityEntryBinding>(R.layout.activity_entry) {
    override fun initView() {
        initLoginBtnClickListener()
    }

    private fun initLoginBtnClickListener() {
        binding.btnEntryLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}