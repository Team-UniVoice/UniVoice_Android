package com.univoice.feature.example.xml

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.view.CustomSpinner
import com.univoice.databinding.ActivityStudentIdInputBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentIdInputActivity :
    BindingActivity<ActivityStudentIdInputBinding>(R.layout.activity_student_id_input) {
    private val viewModel by viewModels<ExampleViewModel>()
    override fun initView() {
        val studentIdArray = resources.getStringArray(R.array.student_id_array)
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, studentIdArray
        )
        binding.spStudentId.adapter = arrayAdapter
        binding.spStudentId.setSpinnerEventsListener(object :
            CustomSpinner.OnSpinnerEventsListener {
            override fun onSpinnerOpened(spinner: Spinner?) {
                binding.spStudentId.isActivated = true
            }

            override fun onSpinnerClosed(spinner: Spinner?) {
                binding.spStudentId.isActivated = false
            }
        })

        binding.spStudentId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Log.d("ExampleActivity", "Selected item: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("ExampleActivity", "Nothing selected")
            }
        }
    }
}
