package com.univoice.feature.example.xml

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
    override fun initView() {
        setupToolbarClickListener(binding.ibToolbarCreateAccountIcon)
        initPwdTransformation()
        setupFocusChangeListeners()

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
}