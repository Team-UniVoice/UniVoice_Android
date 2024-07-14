package com.univoice.feature.signUp

import android.content.Intent
import android.net.Uri
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityStudentCardCheckBinding
import com.univoice.feature.util.setupToolbarClickListener

class StudentCardCheckActivity :
    BindingActivity<ActivityStudentCardCheckBinding>(R.layout.activity_student_card_check) {

    companion object {
        const val USER_NAME = "userName"
        const val USER_STUDENT_ID = "userStudentId"
        const val SELECTED_IMAGE_URI = "selectedImageUri"
    }

    override fun initView() {
        setupToolbar()
        loadUserData()
        setupNextButtonClickListener()
    }

    private fun setupToolbar() {
        setupToolbarClickListener(binding.ibToolbarStudentCardCheckIcon)
    }

    private fun loadUserData() {
        val userName = intent.getStringExtra(USER_NAME) ?: ""
        val userStudentId = intent.getStringExtra(USER_STUDENT_ID) ?: ""
        val imageUriString = intent.getStringExtra(SELECTED_IMAGE_URI) ?: ""
        val imageUri = Uri.parse(imageUriString)

        binding.tvStudentCardCheckName.text = userName
        binding.tvStudentCardCheckStudentId.text = userStudentId
        binding.ivStudentCardCheckPhoto.setImageURI(imageUri)
    }

    private fun setupNextButtonClickListener() {
        binding.btnStudentCardNext.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}
