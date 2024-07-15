package com.univoice.feature.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.databinding.FragmentLoginBottomSheetBinding
import com.univoice.feature.signup.SignUpActivity

class LoginBottomSheetFragment :
    BindingBottomSheetFragment<FragmentLoginBottomSheetBinding>(R.layout.fragment_login_bottom_sheet) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetDialogFragment)
    }

    override fun initView() {
        initCloseBtnClickListener()
        initSignUpBtnClickListener()
    }

    private fun initSignUpBtnClickListener() {
        binding.btnLoginBottomSheetSignup.setOnClickListener {
            navigateToSignUp()
        }
    }

    private fun initCloseBtnClickListener() {
        binding.btnLoginBottomSheetClose.setOnClickListener {
            dismiss()
        }
    }

    private fun navigateToSignUp() {
        startActivity(Intent(requireContext(), SignUpActivity::class.java))
    }

}