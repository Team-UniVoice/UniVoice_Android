package com.univoice.feature.login

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
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

    private fun initIdFocusChangeListener() {
        with(binding) {
            etLoginId.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewIdDivider.backgroundTintList =
                        ContextCompat.getColorStateList(this@LoginActivity, R.color.mint_400)
                } else {
                    viewIdDivider.backgroundTintList =
                        ContextCompat.getColorStateList(this@LoginActivity, R.color.regular)
                }
            }
        }
    }

    private fun initPwdFocusChangeListener() {
        with(binding) {
            etLoginPwd.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewPwdDivider.backgroundTintList =
                        ContextCompat.getColorStateList(this@LoginActivity, R.color.mint_400)
                } else {
                    viewPwdDivider.backgroundTintList =
                        ContextCompat.getColorStateList(this@LoginActivity, R.color.regular)
                }
            }
        }
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
                    lifecycleScope.launch {
                        viewModel.saveUserCredentials(userId, userPwd)
                    }
                    navigateToWelcomeActivity()
                }
            }
        }
    }

    private fun navigateToWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}