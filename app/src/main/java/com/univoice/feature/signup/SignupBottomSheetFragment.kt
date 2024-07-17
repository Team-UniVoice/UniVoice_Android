package com.univoice.feature.signup

import android.app.TaskStackBuilder
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.databinding.FragmentSignupBottomSheetBinding
import com.univoice.feature.entry.EntryActivity

class SignupBottomSheetFragment :
    BindingBottomSheetFragment<FragmentSignupBottomSheetBinding>(R.layout.fragment_signup_bottom_sheet) {

    override fun initView() {
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            cbBottomSheetAgreeAll.setOnClickListener { handleAgreeAllClick() }
            cbBottomSheetService.setOnClickListener { handleServiceCheckBoxClick() }
            cbBottomSheetUse.setOnClickListener { handleServiceCheckBoxClick() }
            btnBottomSheetAgree.setOnClickListener { handleAgreeButtonClick() }
        }
    }

    private fun handleAgreeAllClick() {
        with(binding) {
            val isChecked = cbBottomSheetAgreeAll.isChecked
            cbBottomSheetService.isChecked = isChecked
            cbBottomSheetUse.isChecked = isChecked
            updateAgreeButtonState()
        }
    }

    private fun handleServiceCheckBoxClick() {
        handleIndividualCheckBoxClick()
    }

    private fun handleIndividualCheckBoxClick() {
        with(binding) {
            val allChecked = cbBottomSheetService.isChecked && cbBottomSheetUse.isChecked
            cbBottomSheetAgreeAll.isChecked = allChecked
            updateAgreeButtonState()
        }
    }

    private fun handleAgreeButtonClick() {
        dismiss()
        Intent(requireContext(), CheckInfoActivity::class.java).apply {
            flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }

    private fun updateAgreeButtonState() {
        with(binding) {
            btnBottomSheetAgree.isEnabled =
                cbBottomSheetService.isChecked && cbBottomSheetUse.isChecked
        }
    }
}
