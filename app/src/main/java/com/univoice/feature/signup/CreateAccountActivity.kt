package com.univoice.feature.signup

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityCreateAccountBinding
import com.univoice.feature.util.BiggerDotPasswordTransformationMethod
import com.univoice.feature.util.setupToolbarClickListener

class CreateAccountActivity :
    BindingActivity<ActivityCreateAccountBinding>(R.layout.activity_create_account) {

    private var isIdValid = false
    private var isIdUnique = false
    private var isPasswordValid = false
    private var isPasswordConfirmed = false

    companion object {
        const val ID_REGEX = "^[a-z0-9!@#\$%^&*]{5,20}$"
        const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,16}$"
    }

    override fun initView() {
        initToolbar()
        initPwdTransformation()
        setupFocusChangeListeners()
        initEditTextIdInput()
        setupIdValidation()
        setupDuplicateCheckButton()
        setupPasswordValidation()
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
            id.isEmpty() -> setIdInvalid(R.string.tv_create_account_id_input, R.color.black)
            !isValidId(id) -> setIdInvalid(R.string.tv_create_account_id_input, R.color.black)
            else -> setIdValid(R.string.tv_create_account_id_explain, R.color.blue_400)
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
        }
        isIdValid = true
    }

    private fun isValidId(id: String): Boolean {
        return id.matches(ID_REGEX.toRegex())
    }

    private fun setupDuplicateCheckButton() {
        with(binding) {
            btnCreateAccountId.setOnClickListener { checkDuplicateAndProceed() }
            etCreateAccountId.setOnFocusChangeListener { _, hasFocus -> handleIdFocusChange(hasFocus) }
        }
    }

    private fun checkDuplicateAndProceed() {
        val id = binding.etCreateAccountId.text.toString()
        if (checkDuplicateId(id)) {
            setIdUnique(R.string.tv_create_account_id_explain, R.color.mint_400)
        } else {
            setIdNotUnique(R.string.tv_create_account_id_unavailable, R.color.red)
        }
        updateNextButtonState()
    }

    private fun handleIdFocusChange(hasFocus: Boolean) {
        with(binding) {
            if (hasFocus) {
                tvCreateAccountIdExplain.setText(R.string.tv_create_account_id_input)
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

    private fun checkDuplicateId(id: String): Boolean {
        val existingIds = listOf("user1", "user2", "user3", "user4", "user5")
        return !existingIds.contains(id)
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
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
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
            if (confirmPassword.isEmpty()) {
                tvCreateAccountPwCheckExplain.visibility = View.INVISIBLE
            } else if (password == confirmPassword) {
                tvCreateAccountPwCheckExplain.setText(R.string.password_match)
                tvCreateAccountPwCheckExplain.setTextColor(ContextCompat.getColor(this@CreateAccountActivity, R.color.mint_600))
                tvCreateAccountPwCheckExplain.visibility = View.VISIBLE
                isPasswordConfirmed = true
            } else {
                tvCreateAccountPwCheckExplain.setText(R.string.password_mismatch)
                tvCreateAccountPwCheckExplain.setTextColor(ContextCompat.getColor(this@CreateAccountActivity, R.color.red))
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
        return password.matches(PASSWORD_REGEX.toRegex())
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
        val bottomSheetFragment = SignupBottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}
