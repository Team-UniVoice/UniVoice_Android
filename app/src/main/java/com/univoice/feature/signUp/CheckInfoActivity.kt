package com.univoice.feature.signUp

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityCheckInfoBinding
import com.univoice.feature.entry.EntryActivity

class CheckInfoActivity :
    BindingActivity<ActivityCheckInfoBinding>(R.layout.activity_check_info) {

    override fun initView() {
        initButtonClickListener()
    }

    private fun initButtonClickListener() {
        binding.btnCheckInfoStart.setOnClickListener {
            startActivity(Intent(this, EntryActivity::class.java))
        }
    }
}
