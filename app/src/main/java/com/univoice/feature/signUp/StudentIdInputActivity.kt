package com.univoice.feature.signUp

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentIdInputActivity :
    BindingActivity<ActivityStudentIdInputBinding>(R.layout.activity_student_id_input) {

    private var isSpinnerInitialized = false

    override fun initView() {
        setupSpinner()
        disableButton()
        loadIntentData()
        setupNextButtonClickListener()

        binding.spStudentIdInput.post {
            binding.spStudentIdInput.performClick()
        }
    }

    private fun setupSpinner() {
        val studentIdArray = resources.getStringArray(R.array.student_id_array)
        val arrayAdapter = CustomSpinnerAdapter(this, android.R.layout.simple_list_item_1, studentIdArray)
        binding.spStudentIdInput.adapter = arrayAdapter
        binding.spStudentIdInput.setSpinnerEventsListener(object : CustomSpinner.OnSpinnerEventsListener {
            override fun onSpinnerOpened(spinner: Spinner?) {
                binding.spStudentIdInput.isActivated = true
            }

            override fun onSpinnerClosed(spinner: Spinner?) {
                binding.spStudentIdInput.isActivated = false
            }
        })

        binding.spStudentIdInput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                (parent?.adapter as? ArrayAdapter<*>)?.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                disableButton()
            }
        }
    }

    private fun loadIntentData() {
        val selectedSchool = intent.getStringExtra("selectedSchool")
        val selectedDepartment = intent.getStringExtra("selectedDepartment")

        binding.tvStudentIdInputSchoolSelected.text = selectedSchool
        binding.tvStudentIdInputDepartmentSelected.text = selectedDepartment
    }

    private fun setupNextButtonClickListener() {
        binding.btnStudentIdInputInputNext.setOnClickListener {
            val intent = Intent(this, StudentCardPhotoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun enableButton() {
        binding.btnStudentIdInputInputNext.isEnabled = true
        binding.btnStudentIdInputInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_mint400_radius_40dp)
    }

    private fun disableButton() {
        binding.btnStudentIdInputInputNext.isEnabled = false
        binding.btnStudentIdInputInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_gray200_radius_40dp)
    }
}
