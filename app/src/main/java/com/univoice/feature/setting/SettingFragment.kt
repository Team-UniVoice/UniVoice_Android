package com.univoice.feature.setting

import android.content.Intent
import androidx.fragment.app.viewModels
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.databinding.FragmentSettingBinding
import com.univoice.feature.entry.EntryActivity
import com.univoice.feature.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun initView() {
        initLogoutBtnClickListener()
    }

    private fun initLogoutBtnClickListener() {
        binding.btnSettingLogout.setOnClickListener {
            loginViewModel.saveCheckLogin(false)

            val intent = Intent(activity, EntryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}