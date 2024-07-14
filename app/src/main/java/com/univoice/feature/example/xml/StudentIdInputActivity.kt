package com.univoice.feature.example.xml

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.view.CustomSpinner
import com.univoice.databinding.ActivityStudentIdInputBinding
import com.univoice.feature.example.xml.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.example.xml.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentIdInputActivity :
    BindingActivity<ActivityStudentIdInputBinding>(R.layout.activity_student_id_input) {

    private var isSpinnerInitialized = false

    override fun initView() {
        setupToolbar()
        initFocus()
        setupSpinner()
        setupNextButton()
        setupTextViews()
        disableButton()
    }

    private fun setupToolbar() {
        with(binding.toolbarStudentIdInput) {
            tvToolbarTitle.text =
                applicationContext.getString(R.string.tv_toolbar_personal_information_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initFocus() {
        with(binding.spStudentIdInput) {
            requestFocus()
            // 스피너가 펼쳐진 상태이도록
            // post { performClick() }
        }
    }

    private fun setupSpinner() {
        initSpinnerAdapter()
        setSpinnerListeners()
    }

    private fun initSpinnerAdapter() {
        val studentIdArray = resources.getStringArray(R.array.student_id_array)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentIdArray)
        binding.spStudentIdInput.adapter = arrayAdapter
    }

    private fun setSpinnerListeners() {
        with(binding.spStudentIdInput) {
            setSpinnerEventsListener(object : CustomSpinner.OnSpinnerEventsListener {
                override fun onSpinnerOpened(spinner: Spinner?) {
                    isActivated = true
                }

                override fun onSpinnerClosed(spinner: Spinner?) {
                    isActivated = false
                }
            })

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (isSpinnerInitialized) {
                        enableButton()
                    } else {
                        isSpinnerInitialized = true
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    disableButton()
                }
            }
        }
    }

    private fun setupNextButton() {
        binding.btnStudentIdInputNext.btnSignupNext.setOnClickListener {
            navigateToStudentInfoCheck()
        }
    }

    private fun navigateToStudentInfoCheck() {
        Intent(this, StudentInfoCheckActivity::class.java).apply {
            putExtra(SCHOOL_KEY, intent.getStringExtra(SCHOOL_KEY))
            putExtra(DEPARTMENT_KEY, intent.getStringExtra(DEPARTMENT_KEY))
            putExtra(ID_KEY, binding.spStudentIdInput.selectedItem.toString())
            startActivity(this)
        }
    }

    private fun setupTextViews() {
        with(binding) {
            tvStudentIdInputSchoolSelected.text = intent.getStringExtra(SCHOOL_KEY)
            tvStudentIdInputDepartmentSelected.text = intent.getStringExtra(DEPARTMENT_KEY)
        }
    }

    private fun enableButton() {
        with(binding.btnStudentIdInputNext.btnSignupNext) {
            isEnabled = true
            background = ContextCompat.getDrawable(
                this@StudentIdInputActivity,
                R.drawable.shape_mint400_fill_40_rect
            )
        }
    }

    private fun disableButton() {
        with(binding.btnStudentIdInputNext.btnSignupNext) {
            isEnabled = false
            background = ContextCompat.getDrawable(
                this@StudentIdInputActivity,
                R.drawable.shape_gray200_fill_40_rect
            )
        }
    }

    companion object {
        const val ID_KEY = "selectedId"
    }
}
