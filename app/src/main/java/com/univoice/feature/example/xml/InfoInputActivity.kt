package com.univoice.feature.example.xml

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityInfoInputBinding
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoInputActivity : BindingActivity<ActivityInfoInputBinding>(R.layout.activity_info_input) {

    private var selectedImageUri: String? = null

    override fun initView() {
        selectedImageUri = intent.getStringExtra("selectedImageUri")
        setupToolbarClickListener(binding.ibToolbarNameInputIcon)
        setupFocusChangeListeners()
        initEditTextNameInput()
        initConfirmBtnIsEnabled()
        initConfirmBtnClickListener()
    }

    private fun initEditTextNameInput() {
        binding.etNameInputName.requestFocus()
        binding.etNameInputStudentId.isEnabled=false
    }

    private fun initConfirmBtnIsEnabled() {
        with(binding) {
            etNameInputName.addTextChangedListener {
                btnNameInputNext.isEnabled = etNameInputName.text.isNotEmpty()
            }
            etNameInputStudentId.addTextChangedListener {
                btnNameInputNext.isEnabled =
                    etNameInputName.text.isNotEmpty() && etNameInputStudentId.text.isNotEmpty()
            }
        }
    }

    private fun initConfirmBtnClickListener() {
        with(binding) {
            btnNameInputNext.setOnClickListener {
                if (btnNameInputNext.isEnabled) {
                    if (etNameInputStudentId.text.isEmpty()) {
                        etNameInputStudentId.isEnabled = true
                        etNameInputStudentId.requestFocus()
                        btnNameInputNext.isEnabled = false
                    }
                    else {
                        val userName = etNameInputName.text.toString()
                        val userStudentId = etNameInputStudentId.text.toString()
                        val intent = Intent(this@InfoInputActivity, StudentCardCheckActivity::class.java).apply {
                            putExtra("userName", userName)
                            putExtra("userStudentId", userStudentId)
                            putExtra("selectedImageUri", selectedImageUri)
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun setupFocusChangeListeners() {
        setFocusChangeListener(binding.etNameInputName, binding.viewNameInputNameDivider)
        setFocusChangeListener(binding.etNameInputStudentId, binding.viewNameInputStudentIdDivider)
    }

    private fun setFocusChangeListener(editText: EditText, dividerView: View) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val colorResId = if (hasFocus) R.color.mint_400 else R.color.regular
            dividerView.backgroundTintList =
                ContextCompat.getColorStateList(this@InfoInputActivity, colorResId)
        }
    }
}