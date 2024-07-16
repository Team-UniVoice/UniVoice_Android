package com.univoice.feature.post.dateTimePicker

import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityPostDateBinding
import java.util.Calendar

class PostDateActivity : BindingActivity<ActivityPostDateBinding>(R.layout.activity_post_date) {
    override fun initView() {
        initDateTimePickerClickListener()
    }

    private fun initDateTimePickerClickListener() {
        binding.btnShowPicker.setOnClickListener {
            val startDate: Calendar = Calendar.getInstance()
            val timeBottomSheetFragment = TimeBottomSheetFragment(startDate, 12)
            timeBottomSheetFragment.show(supportFragmentManager, timeBottomSheetFragment.tag)
        }
    }
}