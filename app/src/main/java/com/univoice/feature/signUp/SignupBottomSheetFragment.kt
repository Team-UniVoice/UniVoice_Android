package com.univoice.feature.signUp

import android.content.Intent
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.databinding.FragmentSignupBottomSheetBinding

class SignupBottomSheetFragment :
    BindingBottomSheetFragment<FragmentSignupBottomSheetBinding>(R.layout.fragment_signup_bottom_sheet) {

    override fun initView() {
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            cbBottomSheetAgreeAll.setOnClickListener { handleAgreeAllClick() }
            cbBottomSheetService.setOnClickListener { handleServiceCheckBoxClick() }
            cbBottomSheetUse.setOnClickListener { handleUseCheckBoxClick() }
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

    private fun handleUseCheckBoxClick() {
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
        startActivity(Intent(requireContext(), CheckInfoActivity::class.java))
        dismiss()
    }

    private fun updateAgreeButtonState() {
        with(binding) {
            btnBottomSheetAgree.isEnabled = cbBottomSheetService.isChecked && cbBottomSheetUse.isChecked
        }
    }
}
