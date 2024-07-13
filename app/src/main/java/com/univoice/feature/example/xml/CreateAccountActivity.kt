package com.univoice.feature.example.xml

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

    override fun initView() {
        setupToolbarClickListener(binding.ibToolbarCreateAccountIcon)
        initPwdTransformation()
        setupFocusChangeListeners()
        initEditTextIdInput()
        setupIdValidation()
        setupDuplicateCheckButton()
        setupPasswordValidation()
    }

    private fun initEditTextIdInput() {
        binding.etCreateAccountId.requestFocus()
        binding.etCreateAccountPw.isEnabled = false
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
        setFocusChangeListener(binding.etCreateAccountId, binding.viewCreateAccountIdDivider)
        setFocusChangeListener(binding.etCreateAccountPw, binding.viewCreateAccountPwDivider)
        setFocusChangeListener(
            binding.etCreateAccountPwCheck,
            binding.viewCreateAccountPwCheckDivider
        )
    }

    private fun setFocusChangeListener(editText: EditText, dividerView: View) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val colorResId = if (hasFocus) R.color.mint_400 else R.color.regular
            dividerView.backgroundTintList =
                ContextCompat.getColorStateList(this@CreateAccountActivity, colorResId)
        }
    }

    private fun setupIdValidation() {
        binding.etCreateAccountId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val id = s.toString()
                when {
                    id.isEmpty() -> {
                        binding.tvCreateAccountIdExplain.setText(R.string.tv_create_account_id)
                        binding.tvCreateAccountIdExplain.setTextColor(
                            ContextCompat.getColor(this@CreateAccountActivity, R.color.black)
                        )
                        binding.btnCreateAccountId.isEnabled = false
                        isIdValid = false
                    }
                    !isValidId(id) -> {
                        binding.tvCreateAccountIdExplain.setText(R.string.tv_create_account_id)
                        binding.tvCreateAccountIdExplain.setTextColor(
                            ContextCompat.getColor(this@CreateAccountActivity, R.color.black)
                        )
                        binding.btnCreateAccountId.isEnabled = false
                        isIdValid = false
                    }
                    else -> {
                        binding.tvCreateAccountIdExplain.setText(R.string.tv_create_account_id)
                        binding.tvCreateAccountIdExplain.setTextColor(
                            ContextCompat.getColor(this@CreateAccountActivity, R.color.blue_400)
                        )
                        binding.btnCreateAccountId.isEnabled = true
                        isIdValid = true
                    }
                }
                updateNextButtonState()
            }
        })
    }

    private fun isValidId(id: String): Boolean {
        val regex = "^[a-z0-9!@#\$%^&*]{5,20}$"
        return id.matches(regex.toRegex())
    }

    private fun setupDuplicateCheckButton() {
        binding.btnCreateAccountId.setOnClickListener {
            val id = binding.etCreateAccountId.text.toString()
            if (checkDuplicateId(id)) {
                binding.tvCreateAccountIdExplain.text = "사용 가능한 아이디입니다."
                binding.tvCreateAccountIdExplain.setTextColor(
                    ContextCompat.getColor(this@CreateAccountActivity, R.color.mint_400)
                )
                binding.btnCreateAccountId.isEnabled = false
                binding.etCreateAccountPw.isEnabled = true
                binding.etCreateAccountPw.requestFocus()
                isIdUnique = true
            } else {
                binding.tvCreateAccountIdExplain.text = "사용 불가능한 아이디입니다."
                binding.tvCreateAccountIdExplain.setTextColor(
                    ContextCompat.getColor(this@CreateAccountActivity, R.color.red)
                )
                binding.btnCreateAccountId.isEnabled = true
                isIdUnique = false
            }
            updateNextButtonState()
        }

        binding.etCreateAccountId.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tvCreateAccountIdExplain.setText(R.string.tv_create_account_id)
                binding.tvCreateAccountIdExplain.setTextColor(
                    ContextCompat.getColor(this@CreateAccountActivity, R.color.black)
                )
                binding.viewCreateAccountIdDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.mint_400)
            } else {
                binding.viewCreateAccountIdDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.regular)
            }
        }
    }

    private fun checkDuplicateId(id: String): Boolean {
        val existingIds = listOf(
            "user1", "user2", "user3", "user4", "user5"
        )
        return !existingIds.contains(id)
    }

    private fun setupPasswordValidation() {
        binding.etCreateAccountPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                if (isValidPassword(password)) {
                    binding.tvCreateAccountPwExplain.setTextColor(
                        ContextCompat.getColor(this@CreateAccountActivity, R.color.blue_400)
                    )
                    isPasswordValid = true
                } else {
                    binding.tvCreateAccountPwExplain.setTextColor(
                        ContextCompat.getColor(this@CreateAccountActivity, R.color.red)
                    )
                    isPasswordValid = false
                }
                updateNextButtonState()
            }
        })

        binding.btnCreateAccountNext.setOnClickListener {
            if (isPasswordConfirmed) {
                showBottomSheet()
            } else if (binding.etCreateAccountPwCheck.visibility == View.VISIBLE) {
                validatePasswordConfirmation()
            } else {
                binding.btnCreateAccountNext.isEnabled = false
                binding.btnCreateAccountNext.text = "다음"
                binding.etCreateAccountPwCheck.visibility = View.VISIBLE
                binding.viewCreateAccountPwCheckDivider.visibility = View.VISIBLE
                binding.tvCreateAccountPwExplain.visibility = View.INVISIBLE
                binding.etCreateAccountPwCheck.requestFocus()
            }
        }

        binding.etCreateAccountPwCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validatePasswordConfirmation()
            }
        })

        binding.etCreateAccountPw.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.viewCreateAccountPwDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.mint_400)
                binding.viewCreateAccountIdDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.regular)
            } else {
                binding.viewCreateAccountPwDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.regular)
            }
        }

        binding.etCreateAccountPwCheck.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.viewCreateAccountPwCheckDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.mint_400)
                binding.viewCreateAccountPwDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.regular)
            } else {
                binding.viewCreateAccountPwCheckDivider.backgroundTintList =
                    ContextCompat.getColorStateList(this@CreateAccountActivity, R.color.regular)
            }
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,16}$"
        return password.matches(regex.toRegex())
    }

    private fun updateNextButtonState() {
        binding.btnCreateAccountNext.isEnabled = isIdValid && isIdUnique && isPasswordValid
    }

    private fun validatePasswordConfirmation() {
        val password = binding.etCreateAccountPw.text.toString()
        val confirmPassword = binding.etCreateAccountPwCheck.text.toString()
        isPasswordConfirmed = password == confirmPassword
        binding.tvCreateAccountPwCheckExplain.visibility = if (isPasswordConfirmed) View.VISIBLE else View.GONE
        binding.btnCreateAccountNext.isEnabled = isPasswordConfirmed
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = SignupBottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}
