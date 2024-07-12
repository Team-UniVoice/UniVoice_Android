package com.univoice.feature.example.xml

import android.content.Intent
import android.os.Bundle
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        binding.btnSignupStart.setOnClickListener {
            val intent = Intent(this, SchoolInputActivity::class.java)
            startActivity(intent)
        }
    }
}