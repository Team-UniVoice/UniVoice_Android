package com.univoice.feature.signup

import android.content.Intent
import android.net.Uri
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityStudentCardCheckBinding
import com.univoice.feature.signup.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.signup.InfoInputActivity.Companion.USER_ID_KEY
import com.univoice.feature.signup.InfoInputActivity.Companion.USER_NAME_KEY
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.signup.StudentCardPhotoActivity.Companion.USER_IMAGE_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.USER_YEAR_KEY
import com.univoice.feature.util.setupToolbarClickListener

class StudentCardCheckActivity :
    BindingActivity<ActivityStudentCardCheckBinding>(R.layout.activity_student_card_check) {

    override fun initView() {
        initToolbar()
        initUserData()
        setupNextButtonClickListener()
    }

    private fun initToolbar() {
        with(binding.toolbarStudentCardCheck) {
            tvToolbarTitle.text = applicationContext.getString(R.string.tb_card_check)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initUserData() {
        val userName = intent.getStringExtra(USER_NAME_KEY) ?: ""
        val userStudentId = intent.getStringExtra(USER_ID_KEY) ?: ""
        val imageUriString = intent.getStringExtra(USER_IMAGE_KEY) ?: ""
        val imageUri = Uri.parse(imageUriString)

        binding.tvStudentCardCheckName.text = userName
        binding.tvStudentCardCheckStudentId.text = userStudentId
        binding.ivStudentCardCheckPhoto.setImageURI(imageUri)
    }

    private fun setupNextButtonClickListener() {
        binding.btnStudentCardCheckNext.setOnClickListener {
            Intent(this, CreateAccountActivity::class.java).apply {
                putExtra(USER_ID_KEY, intent.getStringExtra(USER_ID_KEY))
                putExtra(USER_NAME_KEY, intent.getStringExtra(USER_NAME_KEY))
                putExtra(USER_IMAGE_KEY, intent.getStringExtra(USER_IMAGE_KEY))
                putExtra(USER_YEAR_KEY, intent.getStringExtra(USER_YEAR_KEY))
                putExtra(SCHOOL_KEY, intent.getStringExtra(SCHOOL_KEY))
                putExtra(DEPARTMENT_KEY, intent.getStringExtra(DEPARTMENT_KEY))
                startActivity(this)
            }
        }
    }
}
