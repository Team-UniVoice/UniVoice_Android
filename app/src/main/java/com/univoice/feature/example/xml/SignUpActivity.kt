package com.univoice.feature.example.xml

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivitySignupBinding

class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    override fun initView() {
        initStartBtnClickListener()
    }

    private fun initStartBtnClickListener() {
        binding.btnSignupStart.setOnClickListener {
            startActivity(Intent(this, SchoolInputActivity::class.java))
        }
    }
}