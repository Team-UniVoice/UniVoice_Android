package com.univoice.feature.signup

import android.content.Intent
import android.net.Uri
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityStudentCardCheckBinding
import com.univoice.feature.signup.InfoInputActivity.Companion.NAME_KEY
import com.univoice.feature.signup.StudentCardPhotoActivity.Companion.IMAGE_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.ID_KEY
import com.univoice.feature.util.setupToolbarClickListener

class StudentCardCheckActivity :
    BindingActivity<ActivityStudentCardCheckBinding>(R.layout.activity_student_card_check) {

    override fun initView() {
        initToolbar()
        loadUserData()
        setupNextButtonClickListener()
    }

    private fun initToolbar() {
        with(binding.toolbarStudentCardCheck) {
            tvToolbarTitle.text = applicationContext.getString(R.string.tb_card_check)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun loadUserData() {
        val userName = intent.getStringExtra(NAME_KEY) ?: ""
        val userStudentId = intent.getStringExtra(ID_KEY) ?: ""
        val imageUriString = intent.getStringExtra(IMAGE_KEY) ?: ""
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
