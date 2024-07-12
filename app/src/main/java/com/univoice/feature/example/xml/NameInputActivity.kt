package com.univoice.feature.example.xml

import android.os.Bundle
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityNameInputBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NameInputActivity : BindingActivity<ActivityNameInputBinding>(R.layout.activity_name_input) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {

    }
}