package com.univoice.feature.login

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.databinding.FragmentLoginBottomSheetBinding

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
        binding.btnSheetSignup.setOnClickListener {
            navigateToSignUp()
        }
    }

    private fun initCloseBtnClickListener() {
        binding.btnSheetClose.setOnClickListener {
            dismiss()
        }
    }

    private fun navigateToSignUp() {
        // 회원가입 화면으로 이동
    }

}