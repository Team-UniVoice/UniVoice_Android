package com.univoice.feature.signup

import android.content.Intent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityInfoInputBinding
import com.univoice.feature.signup.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.signup.StudentCardPhotoActivity.Companion.USER_IMAGE_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.USER_YEAR_KEY
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoInputActivity : BindingActivity<ActivityInfoInputBinding>(R.layout.activity_info_input) {

    override fun initView() {
        initDisableButton()
        initToolbar()
        setupFocusChangeListeners()
        initEditTextNameInput()
        initConfirmBtnIsEnabled()
        initConfirmBtnClickListener()
    }

    private fun initDisableButton() {
        binding.btnNameInputNext.btnSignupNext.isEnabled = false
    }

    private fun initToolbar() {
        with(binding.toolbarNameInput) {
            tvToolbarTitle.text = applicationContext.getString(R.string.tb_card_check)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initEditTextNameInput() {
        binding.etNameInputName.requestFocus()
        binding.etNameInputStudentId.isEnabled = false
    }

    private fun initConfirmBtnIsEnabled() {
        with(binding) {
            etNameInputName.addTextChangedListener {
                btnNameInputNext.btnSignupNext.isEnabled = etNameInputName.text.isNotEmpty()
            }
            etNameInputStudentId.addTextChangedListener {
                btnNameInputNext.btnSignupNext.isEnabled =
                    etNameInputName.text.isNotEmpty() && etNameInputStudentId.text.isNotEmpty()
            }
        }
    }

    private fun initConfirmBtnClickListener() {
        with(binding) {
            btnNameInputNext.btnSignupNext.setOnClickListener {
                if (btnNameInputNext.btnSignupNext.isEnabled) {
                    if (etNameInputStudentId.text.isEmpty()) {
                        etNameInputStudentId.isEnabled = true
                        etNameInputStudentId.requestFocus()
                        btnNameInputNext.btnSignupNext.isEnabled = false
                    } else {
                        navigateToStudentCardCheck()
                    }
                }
            }
        }
    }

    private fun navigateToStudentCardCheck() {
        Intent(this, StudentCardCheckActivity::class.java).apply {
            putExtra(USER_ID_KEY, binding.etNameInputStudentId.text.toString())
            putExtra(USER_NAME_KEY, binding.etNameInputName.text.toString())
            putExtra(USER_IMAGE_KEY, intent.getStringExtra(USER_IMAGE_KEY))
            putExtra(USER_YEAR_KEY, intent.getStringExtra(USER_YEAR_KEY))
            putExtra(SCHOOL_KEY, intent.getStringExtra(SCHOOL_KEY))
            putExtra(DEPARTMENT_KEY, intent.getStringExtra(DEPARTMENT_KEY))
            startActivity(this)
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

    companion object {
        const val USER_NAME_KEY = "userName"
        const val USER_ID_KEY = "userId"
    }
}