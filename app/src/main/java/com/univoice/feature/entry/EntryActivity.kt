package com.univoice.feature.entry

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityEntryBinding
import com.univoice.feature.login.LoginActivity

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