package com.univoice.feature.example.xml

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityStudentInfoCheckBinding
import com.univoice.feature.example.xml.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.example.xml.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.example.xml.StudentIdInputActivity.Companion.ID_KEY
import com.univoice.feature.util.setupToolbarClickListener

class StudentInfoCheckActivity : BindingActivity<ActivityStudentInfoCheckBinding>(R.layout.activity_student_info_check) {
    override fun initView() {
        setupToolbar()
        initTextViews()
        initConfirmBtnClickListener()
    }

    private fun setupToolbar() {
        setupToolbarClickListener(binding.ibToolbarStudentInfoCheckIcon)
    }

    private fun initConfirmBtnClickListener() {
        binding.btnStudentInfoCheckConfirm.setOnClickListener {
            startActivity(Intent(this, StudentCardPhotoActivity::class.java))
        }
    }

    private fun initTextViews() {
        with(binding) {
            tvStudentInfoCheckIdSelected.text = intent.getStringExtra(ID_KEY)
            tvStudentInfoCheckDepartmentSelected.text = intent.getStringExtra(DEPARTMENT_KEY)
            tvStudentInfoCheckSchoolSelected.text = intent.getStringExtra(SCHOOL_KEY)
        }
    }
}