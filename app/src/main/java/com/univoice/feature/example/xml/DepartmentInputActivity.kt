package com.univoice.feature.example.xml

import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.util.context.toast
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.ActivityDepartmentInputBinding
import com.univoice.databinding.ActivitySchoolInputBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DepartmentInputActivity : BindingActivity<ActivityDepartmentInputBinding>(R.layout.activity_department_input) {

    override fun initView() {
    }

    private fun EditTextListener() {
        binding.etDepartmentInputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString().trim()
                if (input.isNotEmpty()) {
                    enableButton()
                } else {
                    disableButton()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun enableButton() {
        binding.btnDepartmentInputNext.isEnabled = true
        binding.btnDepartmentInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_mint400_radius_40dp)
    }

    private fun disableButton() {
        binding.btnDepartmentInputNext.isEnabled = false
        binding.btnDepartmentInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_gray200_radius_40dp)
    }
}
