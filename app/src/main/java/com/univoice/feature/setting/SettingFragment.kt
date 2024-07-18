package com.univoice.feature.setting

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.FragmentSettingBinding
import com.univoice.domain.entity.SettingUserEntity
import com.univoice.feature.entry.EntryActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val settingViewModel by viewModels<SettingViewModel>()

    override fun initView() {
        initLogoutBtnClickListener()
        setupUserProfileObserve()
        initServiceClickListener()
        initPersonalClickListener()
    }

    private fun initPersonalClickListener() {
        binding.layoutSettingPersonal.setOnClickListener {
            navigateToComplaintWeb("https://massive-maple-b53.notion.site/430e2c92b8694ad6a8b4497f3a3b4452?pvs=4")
        }
    }

    private fun initServiceClickListener() {
        binding.layoutSettingService.setOnClickListener {
            navigateToComplaintWeb("https://massive-maple-b53.notion.site/426578b24235447abccaae359549cdb7?pvs=4")
        }
    }

    private fun setupUserProfileObserve() {
        settingViewModel.getMyPageState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> initSettingUserProfile(it.data)
                is UiState.Empty -> Unit
                is UiState.Failure -> Timber.tag("SettingFragment").d(it.msg)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initSettingUserProfile(data: SettingUserEntity) {
        with(binding) {
            tvSettingName.text = data.name
            tvSettingCollege.text = data.collegeDepartment
            tvSettingDepartment.text = "${data.department} ${data.admissionNumber}"

            tvSettingSchool.text = data.university
            ivSettingSchool.load(data.universityLogoImage)
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.layoutSettingLogOut.setOnClickListener {
            settingViewModel.saveCheckLogin(false)

            val intent = Intent(activity, EntryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    private fun navigateToComplaintWeb(uri: String) {
        val urlIntentComplaint =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(uri),
            )
        startActivity(urlIntentComplaint)
    }
}