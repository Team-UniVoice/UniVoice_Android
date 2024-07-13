package com.univoice.feature.example.xml

import android.content.Intent
import android.net.Uri
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityStudentCardCheckBinding
import com.univoice.feature.util.setupToolbarClickListener

class StudentCardCheckActivity :
    BindingActivity<ActivityStudentCardCheckBinding>(R.layout.activity_student_card_check) {
    override fun initView() {
        setupToolbarClickListener(binding.ibToolbarStudentCardCheckIcon)

        val userName = intent.getStringExtra("userName") ?: ""
        val userStudentId = intent.getStringExtra("userStudentId") ?: ""
        val imageUriString = intent.getStringExtra("selectedImageUri") ?: ""
        val imageUri = Uri.parse(imageUriString)

        binding.tvStudentCardCheckName.text = userName
        binding.tvStudentCardCheckStudentId.text = userStudentId
        binding.ivStudentCardCheckPhoto.setImageURI(imageUri)

        binding.btnStudentCardNext.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

}