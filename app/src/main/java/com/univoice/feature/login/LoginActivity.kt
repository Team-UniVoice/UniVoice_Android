package com.univoice.feature.login

import android.content.Intent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.ActivityLoginBinding
import com.univoice.feature.util.BiggerDotPasswordTransformationMethod
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun initView() {
        initToolbar()
        initConfirmBtnClickListener()
        initConfirmBtnIsEnabled()
        setupFocusChangeListeners()
        initPwdTransformation()
        initEditTextFocus()
        setupPostLoginObserve()
    }

    private fun setupPostLoginObserve() {
        loginViewModel.postLoginState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Timber.tag("here").d("로딩중!")
                is UiState.Success -> {
                    Timber.tag("here").d("성공!")
                    loginViewModel.saveUserAccessToken(it.data)
                    navigateToWelcomeActivity()
                }

                is UiState.Empty -> Timber.tag("here").d("비어있음!")
                is UiState.Failure -> {
                    Timber.tag("here").d("실패!")
                    showBottomSheetFragment()
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun initEditTextFocus() {
        binding.etLoginId.requestFocus()
        binding.etLoginPwd.isEnabled = false
    }

    private fun initToolbar() {
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
                        val userEmail = etLoginId.text.toString()
                        val userPw = etLoginPwd.text.toString()

                        loginViewModel.postLogin(userEmail, userPw)
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