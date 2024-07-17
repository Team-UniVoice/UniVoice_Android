package com.univoice.feature.signup

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityStudentInfoCheckBinding
import com.univoice.feature.signup.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.USER_YEAR_KEY
import com.univoice.feature.util.setupToolbarClickListener

class StudentInfoCheckActivity :
    BindingActivity<ActivityStudentInfoCheckBinding>(R.layout.activity_student_info_check) {
    override fun initView() {
        setupToolbar()
        initTextViews()
        initConfirmBtnClickListener()
    }

    private fun setupToolbar() {
        with(binding.toolbarStudentInfoCheck) {
            tvToolbarTitle.text =
                applicationContext.getString(R.string.tv_toolbar_personal_information_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initConfirmBtnClickListener() {
        Intent(this, StudentCardPhotoActivity::class.java).apply {
            putExtra(USER_YEAR_KEY, intent.getStringExtra(USER_YEAR_KEY))
            putExtra(SCHOOL_KEY, intent.getStringExtra(SCHOOL_KEY))
            putExtra(DEPARTMENT_KEY, intent.getStringExtra(DEPARTMENT_KEY))
            startActivity(this)
        }
    }

    private fun initTextViews() {
        with(binding) {
            tvStudentInfoCheckIdSelected.text = intent.getStringExtra(USER_YEAR_KEY)
            tvStudentInfoCheckDepartmentSelected.text = intent.getStringExtra(DEPARTMENT_KEY)
            tvStudentInfoCheckSchoolSelected.text = intent.getStringExtra(SCHOOL_KEY)
        }
    }
}