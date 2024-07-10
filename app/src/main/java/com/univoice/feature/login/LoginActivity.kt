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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun initView() {
        initToolbar()
        initToolbarClickListener()
        initConfirmBtnClickListener()
        initConfirmBtnIsEnabled()
        initIdFocusChangeListener()
        initPwdFocusChangeListener()
    }

    private fun initConfirmBtnIsEnabled() {
        with(binding) {
            etLoginId.addTextChangedListener {
                btnLoginConfirm.isEnabled =
                    etLoginId.text.isNotEmpty() && etLoginPwd.text.isNotEmpty()
            }
            etLoginPwd.addTextChangedListener {
                btnLoginConfirm.isEnabled =
                    etLoginId.text.isNotEmpty() && etLoginPwd.text.isNotEmpty()
            }
        }
    }

    private fun initToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            tvToolbarTitle.text = getString(R.string.login_toolbar_title)
        }
    }

    private fun initToolbarClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initConfirmBtnClickListener() {
        with(binding) {
            btnLoginConfirm.setOnClickListener {
                if (btnLoginConfirm.isEnabled) {
                    val userId = etLoginId.text.toString()
                    val userPwd = etLoginPwd.text.toString()
                    // 로그인 성공 여부에 따라 화면 이동
                    lifecycleScope.launch {
                        // 로그인 성공한 경우
                        if (viewModel.loginState.value == true) {
                            viewModel.saveUserCredentials(userId, userPwd)
                            navigateToWelcomeActivity()
                        }
                        // 로그인 실패한 경우
                        else {
                            showBottomSheetFragment()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

    private fun initIdFocusChangeListener() {
        initFocusChangeListener(binding.etLoginId, binding.viewIdDivider)
    }

    private fun initPwdFocusChangeListener() {
        initFocusChangeListener(binding.etLoginPwd, binding.viewPwdDivider)
    }

    private fun initFocusChangeListener(editText: EditText, dividerView: View) {
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