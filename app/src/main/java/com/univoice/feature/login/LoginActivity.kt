package com.univoice.feature.login

import android.content.Intent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityLoginBinding
import com.univoice.feature.util.BiggerDotPasswordTransformationMethod
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun initView() {
        setupToolbar()
        initConfirmBtnClickListener()
        initConfirmBtnIsEnabled()
        setupFocusChangeListeners()
        initPwdTransformation()
        initEditTextFocus()
    }

    private fun initEditTextFocus() {
        binding.etLoginId.requestFocus()
        binding.etLoginPwd.isEnabled = false
    }

    private fun setupToolbar() {
        with(binding.toolbarLogin) {
            tvToolbarTitle.text = applicationContext.getString(R.string.login_toolbar_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initPwdTransformation() {
        binding.etLoginPwd.transformationMethod =
            BiggerDotPasswordTransformationMethod(applicationContext)
    }

    private fun initConfirmBtnIsEnabled() {
        with(binding) {
            etLoginId.addTextChangedListener {
                btnLoginConfirm.isEnabled = etLoginId.text.isNotEmpty()
            }
            etLoginPwd.addTextChangedListener {
                btnLoginConfirm.isEnabled =
                    etLoginId.text.isNotEmpty() && etLoginPwd.text.isNotEmpty()
            }
        }
    }

    private fun initConfirmBtnClickListener() {
        with(binding) {
            btnLoginConfirm.setOnClickListener {
                if (btnLoginConfirm.isEnabled) {
                    if (etLoginPwd.text.isEmpty()) {
                        etLoginPwd.isEnabled = true
                        etLoginPwd.requestFocus()
                        btnLoginConfirm.isEnabled = false
                    } else {
                        val userId = etLoginId.text.toString()
                        val userPwd = etLoginPwd.text.toString()

                        lifecycleScope.launch {
                            // 로그인 성공 여부 확인
                            if (viewModel.loginState.value == true) {
                                // 사용자 인증 정보 저장
                                viewModel.saveUserCredentials(userId, userPwd)
                                navigateToWelcomeActivity()
                            } else {
                                // 로그인 실패 시
                                showBottomSheetFragment()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToWelcomeActivity() {
        startActivity(Intent(this, WelcomeActivity::class.java))
    }

    private fun setupFocusChangeListeners() {
        setFocusChangeListener(binding.etLoginId, binding.viewLoginIdDivider)
        setFocusChangeListener(binding.etLoginPwd, binding.viewLoginPwdDivider)
    }

    private fun setFocusChangeListener(editText: EditText, dividerView: View) {
        editText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            val colorResId = if (hasFocus) R.color.mint_400 else R.color.regular
            dividerView.backgroundTintList =
                ContextCompat.getColorStateList(this@LoginActivity, colorResId)
        }
    }

    private fun showBottomSheetFragment() {
        val loginBottomSheetFragment = LoginBottomSheetFragment()
        loginBottomSheetFragment.show(supportFragmentManager, loginBottomSheetFragment.tag)
    }
}