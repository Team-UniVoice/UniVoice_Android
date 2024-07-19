package com.univoice.feature.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.ActivityCreateAccountBinding
import com.univoice.feature.signup.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.signup.InfoInputActivity.Companion.USER_ID_KEY
import com.univoice.feature.signup.InfoInputActivity.Companion.USER_NAME_KEY
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.signup.StudentCardPhotoActivity.Companion.USER_IMAGE_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.USER_YEAR_KEY
import com.univoice.feature.util.BiggerDotPasswordTransformationMethod
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateAccountActivity :
    BindingActivity<ActivityCreateAccountBinding>(R.layout.activity_create_account) {

    private val viewModel by viewModels<CreateAccountViewModel>()

    private var isIdValid = false
    private var isIdUnique = false
    private var isPasswordValid = false
    private var isPasswordConfirmed = false

    companion object {
        const val ID_REGEX = "^[a-z0-9!@#\$%^&*]{5,20}$"
        const val PASSWORD_REGEX = "^(?!.*[ㄱ-ㅎㅏ-ㅣ가-힣])(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,16}$"
        const val PASSWORD_KEY = "passWord"
        const val ID_KEY = "id"
    }

    override fun initView() {
        initToolbar()
        initDisableButton()
        setupIdValidation()
        initPwdTransformation()
        setupFocusChangeListeners()
        initEditTextIdInput()
        setupDuplicateCheckButton()
        setupPasswordValidation()
        setupIdObserve()
    }

    private fun initDisableButton() {
        binding.btnCreateAccountNext.btnSignupNext.isEnabled = false
    }

    private fun initToolbar() {
        with(binding.toolbarCreateAccount) {
            tvToolbarTitle.text = applicationContext.getString(R.string.tb_create_account)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initEditTextIdInput() {
        with(binding) {
            etCreateAccountId.requestFocus()
            etCreateAccountPw.isEnabled = false
        }
    }

    private fun initPwdTransformation() {
        with(binding) {
            etCreateAccountPw.transformationMethod =
                BiggerDotPasswordTransformationMethod(applicationContext)
            etCreateAccountPwCheck.transformationMethod =
                BiggerDotPasswordTransformationMethod(applicationContext)
        }
    }

    private fun setupFocusChangeListeners() {
        with(binding) {
            setFocusChangeListener(etCreateAccountId, viewCreateAccountIdDivider)
            setFocusChangeListener(etCreateAccountPw, viewCreateAccountPwDivider)
            setFocusChangeListener(etCreateAccountPwCheck, viewCreateAccountPwCheckDivider)
        }
    }

    private fun setFocusChangeListener(editText: EditText, dividerView: View) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val colorResId = if (hasFocus) R.color.mint_400 else R.color.regular
            updateDividerColor(dividerView, colorResId)
        }
    }

    private fun updateDividerColor(dividerView: View, colorResId: Int) {
        dividerView.backgroundTintList =
            ContextCompat.getColorStateList(this@CreateAccountActivity, colorResId)
    }

    private fun setupIdValidation() {
        binding.etCreateAccountId.addTextChangedListener(createIdTextWatcher())
    }

    private fun createIdTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateId(s.toString())
            }
        }
    }

    private fun validateId(id: String) {
        when {
            id.isBlank() -> setIdInvalid(R.string.tv_create_account_id, R.color.black)
            !isValidId(id) -> setIdInvalid(R.string.tv_create_account_id, R.color.black)
            else -> setIdValid(R.string.tv_create_account_id, R.color.blue_400)
        }
        updateNextButtonState()
    }

    private fun setIdInvalid(messageResId: Int, colorResId: Int) {
        with(binding) {
            tvCreateAccountIdExplain.setText(messageResId)
            tvCreateAccountIdExplain.setTextColor(
                ContextCompat.getColor(
                    this@CreateAccountActivity,
                    colorResId
                )
            )
            btnCreateAccountId.isEnabled = false
        }
        isIdValid = false
    }

    private fun setIdValid(messageResId: Int, colorResId: Int) {
        with(binding) {
            tvCreateAccountIdExplain.setText(messageResId)
            tvCreateAccountIdExplain.setTextColor(
                ContextCompat.getColor(
                    this@CreateAccountActivity,
                    colorResId
                )
            )
            btnCreateAccountId.isEnabled = true
            isPasswordValid = false
        }
        isIdValid = true
    }

    private fun isValidId(id: String): Boolean {
        return id.matches(ID_REGEX.toRegex())
    }

    private fun setupDuplicateCheckButton() {
        with(binding) {
            btnCreateAccountId.setOnClickListener { checkDuplicateProceed() }
            etCreateAccountId.setOnFocusChangeListener { _, hasFocus -> handleIdFocusChange(hasFocus) }
        }
    }

    private fun checkDuplicateProceed() {
        val id = binding.etCreateAccountId.text.toString()
        viewModel.checkEmail(id)
    }

    private fun handleIdFocusChange(hasFocus: Boolean) {
        with(binding) {
            if (hasFocus) {
                tvCreateAccountIdExplain.setText(R.string.tv_create_account_id)
                tvCreateAccountIdExplain.setTextColor(
                    ContextCompat.getColor(
                        this@CreateAccountActivity,
                        R.color.black
                    )
                )
                updateDividerColor(viewCreateAccountIdDivider, R.color.mint_400)
            } else {
                updateDividerColor(viewCreateAccountIdDivider, R.color.regular)
            }
        }
    }

    private fun setIdUnique(messageResId: Int, colorResId: Int) {
        with(binding) {
            tvCreateAccountIdExplain.setText(messageResId)
            tvCreateAccountIdExplain.setTextColor(
                ContextCompat.getColor(
                    this@CreateAccountActivity,
                    colorResId
                )
            )
            btnCreateAccountId.isEnabled = false
            etCreateAccountPw.isEnabled = true
            etCreateAccountPw.requestFocus()
        }
        isIdUnique = true
    }

    private fun setIdNotUnique(messageResId: Int, colorResId: Int) {
        with(binding) {
            tvCreateAccountIdExplain.setText(messageResId)
            tvCreateAccountIdExplain.setTextColor(
                ContextCompat.getColor(
                    this@CreateAccountActivity,
                    colorResId
                )
            )
            btnCreateAccountId.isEnabled = true
        }
        isIdUnique = false
    }

    private fun setupPasswordValidation() {
        with(binding) {
            etCreateAccountPw.addTextChangedListener(createPasswordTextWatcher())
            etCreateAccountPwCheck.addTextChangedListener(createPasswordConfirmationTextWatcher())
            btnCreateAccountNext.btnSignupNext.setOnClickListener { handleNextButtonClick() }
        }
        setupPasswordFocusChangeListeners()
    }

    private fun createPasswordTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                validatePassword(s.toString())
                validatePasswordConfirmation()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword(s.toString())
                validatePasswordConfirmation()
            }
            override fun afterTextChanged(s: Editable?) {
                validatePassword(s.toString())
                validatePasswordConfirmation()
            }
        }
    }

    private fun validatePassword(password: String) {
        if (isValidPassword(password)) {
            setPasswordValid(R.color.blue_400)
        } else {
            setPasswordInvalid(R.color.red)
        }
        updateNextButtonState()
    }

    private fun setPasswordValid(colorResId: Int) {
        binding.tvCreateAccountPwExplain.setTextColor(
            ContextCompat.getColor(
                this@CreateAccountActivity,
                colorResId
            )
        )
        isPasswordValid = true
    }

    private fun setPasswordInvalid(colorResId: Int) {
        binding.tvCreateAccountPwExplain.setTextColor(
            ContextCompat.getColor(
                this@CreateAccountActivity,
                colorResId
            )
        )
        isPasswordValid = false
    }

    private fun createPasswordConfirmationTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validatePasswordConfirmation()
            }
        }
    }

    private fun validatePasswordConfirmation() {
        with(binding) {
            val password = etCreateAccountPw.text.toString()
            val confirmPassword = etCreateAccountPwCheck.text.toString()
            if (confirmPassword.isBlank()) {
                tvCreateAccountPwCheckExplain.visibility = View.INVISIBLE
            } else if (password == confirmPassword) {
                tvCreateAccountPwCheckExplain.setText(R.string.password_match)
                tvCreateAccountPwCheckExplain.setTextColor(
                    ContextCompat.getColor(
                        this@CreateAccountActivity,
                        R.color.mint_600
                    )
                )
                tvCreateAccountPwCheckExplain.visibility = View.VISIBLE
                isPasswordConfirmed = true
            } else {
                tvCreateAccountPwCheckExplain.setText(R.string.password_mismatch)
                tvCreateAccountPwCheckExplain.setTextColor(
                    ContextCompat.getColor(
                        this@CreateAccountActivity,
                        R.color.red
                    )
                )
                tvCreateAccountPwCheckExplain.visibility = View.VISIBLE
                isPasswordConfirmed = false
            }
        }
        updateNextButtonState()
    }

    private fun setupPasswordFocusChangeListeners() {
        with(binding) {
            etCreateAccountPw.setOnFocusChangeListener { _, hasFocus ->
                handlePasswordFocusChange(
                    hasFocus
                )
            }
            etCreateAccountPwCheck.setOnFocusChangeListener { _, hasFocus ->
                handlePasswordCheckFocusChange(
                    hasFocus
                )
            }
        }
    }

    private fun handlePasswordFocusChange(hasFocus: Boolean) {
        with(binding) {
            if (hasFocus) {
                updateDividerColor(viewCreateAccountPwDivider, R.color.mint_400)
                updateDividerColor(viewCreateAccountIdDivider, R.color.regular)
            } else {
                updateDividerColor(viewCreateAccountPwDivider, R.color.regular)
            }
        }
    }

    private fun handlePasswordCheckFocusChange(hasFocus: Boolean) {
        with(binding) {
            if (hasFocus) {
                updateDividerColor(viewCreateAccountPwCheckDivider, R.color.mint_400)
                updateDividerColor(viewCreateAccountPwDivider, R.color.regular)
            } else {
                updateDividerColor(viewCreateAccountPwCheckDivider, R.color.regular)
            }
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = PASSWORD_REGEX.toRegex()
        val containsKorean = password.any { it in '\u1100'..'\u11FF' || it in '\uAC00'..'\uD7AF' }
        return regex.matches(password) && !containsKorean
    }

    private fun handleNextButtonClick() {
        if (isPasswordConfirmed) {
            showBottomSheet()
        } else {
            showPasswordConfirmation()
        }
    }

    private fun showPasswordConfirmation() {
        with(binding) {
            btnCreateAccountNext.btnSignupNext.isEnabled = false
            btnCreateAccountNext.btnSignupNext.text =
                getString(R.string.btn_create_account_next_twice)
            etCreateAccountPwCheck.visibility = View.VISIBLE
            viewCreateAccountPwCheckDivider.visibility = View.VISIBLE
            tvCreateAccountPwExplain.visibility = View.INVISIBLE
            etCreateAccountPwCheck.requestFocus()
        }
    }

    private fun updateNextButtonState() {
        with(binding) {
            btnCreateAccountNext.btnSignupNext.isEnabled =
                isIdValid && isIdUnique && isPasswordValid && (etCreateAccountPwCheck.visibility != View.VISIBLE || isPasswordConfirmed)
        }
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = SignupBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(USER_ID_KEY, intent.getStringExtra(USER_ID_KEY))
                putString(USER_NAME_KEY, intent.getStringExtra(USER_NAME_KEY))
                putString(USER_IMAGE_KEY, intent.getStringExtra(USER_IMAGE_KEY))
                putString(USER_YEAR_KEY, intent.getStringExtra(USER_YEAR_KEY))
                putString(SCHOOL_KEY, intent.getStringExtra(SCHOOL_KEY))
                putString(DEPARTMENT_KEY, intent.getStringExtra(DEPARTMENT_KEY))
                putString(PASSWORD_KEY, binding.etCreateAccountPw.text.toString())
                putString(ID_KEY, binding.etCreateAccountId.text.toString())
            }
        }
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun setupIdObserve() {
        lifecycleScope.launch {
            viewModel.emailCheckState.collect { state ->
                when (state) {
                    is UiState.Loading -> Unit
                    is UiState.Success -> setIdUnique(
                        R.string.tv_create_account_id_explain,
                        R.color.mint_400
                    )
                    is UiState.Failure -> setIdNotUnique(
                        R.string.tv_create_account_id_unavailable,
                        R.color.red
                    )
                    else -> Unit
                }
                updateNextButtonState()
            }
        }
    }
}
