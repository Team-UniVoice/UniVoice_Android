package com.univoice.feature.example.xml

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
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
        val studentIdArray = resources.getStringArray(R.array.student_id_array)
        val arrayAdapter: ArrayAdapter<String> = object : ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, studentIdArray
        ) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val textView = view as TextView
                textView.setTextAppearance(R.style.TextAppearance_UniVoice_title4Semi) //드롭다운 아이템 폰트 (나중에 바꿔주기 !)
                if (position == 0) {
                    view.visibility = View.GONE
                    view.layoutParams = ViewGroup.LayoutParams(0, 1)
                } else {
                    view.visibility = View.VISIBLE
                    view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                }
                return view
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view as TextView
                textView.setPadding(21, textView.paddingTop, textView.paddingRight, textView.paddingBottom)
                if (position == binding.spStudentIdInput.selectedItemPosition) {
                    textView.setTextAppearance(R.style.TextAppearance_UniVoice_title4Semi) //선택 했을때 폰트
                }
                if (position == 0) {
                    textView.setTextAppearance(R.style.TextAppearance_UniVoice_head7Regular) //선택 안했을때 힌트 글자 폰트랑 색
                    textView.setTextColor(ContextCompat.getColor(context, R.color.font_B04))
                }
                return view
            }
        }
        binding.spStudentIdInput.adapter = arrayAdapter
        binding.spStudentIdInput.setSpinnerEventsListener(object :
            CustomSpinner.OnSpinnerEventsListener {
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
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    Log.d("ExampleActivity", "Selected item: $selectedItem")
                    enableButton()
                } else {
                    isSpinnerInitialized = true
                }
                // Spinner 선택 항목 업데이트
                (parent?.adapter as? ArrayAdapter<*>)?.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("ExampleActivity", "Nothing selected")
                disableButton()
            }
        }

        disableButton()

        val selectedSchool = intent.getStringExtra("selectedSchool")
        val selectedDepartment = intent.getStringExtra("selectedDepartment")

        binding.tvStudentIdInputSchoolSelected.text = selectedSchool
        binding.tvStudentIdInputDepartmentSelected.text = selectedDepartment

        binding.btnStudentIdInputInputNext.setOnClickListener {
            val intent = Intent(this, StudentCardPhotoActivity::class.java)
            startActivity(intent)
        }

        binding.spStudentIdInput.post {
            binding.spStudentIdInput.performClick()
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
