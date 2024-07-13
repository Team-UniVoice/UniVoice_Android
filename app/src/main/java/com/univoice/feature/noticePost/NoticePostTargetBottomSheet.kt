package com.univoice.feature.noticePost

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.core_ui.util.context.hideKeyboard
import com.univoice.databinding.BottomsheetNoticePostTargetBinding
import com.univoice.feature.noticePost.NoticePostFragment.Companion.NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS
import com.univoice.feature.noticePost.NoticePostFragment.Companion.NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS_CONTENT

class NoticePostTargetBottomSheet(private val targetText: String? = null) :
    BindingBottomSheetFragment<BottomsheetNoticePostTargetBinding>(R.layout.bottomsheet_notice_post_target) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentKeyboardBottomSheetDialogFragment)
    }

    override fun initView() {
        initContentText()
        initCheckBtnClickListener()
        initBottomSheetCloseBtnClickListener()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            setOnShowListener { dialogInterface ->
                val bottomSheet =
                    (dialogInterface as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.let { sheet ->
                    sheet.layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
                }
            }
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
            behavior.isDraggable = false
        }
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        return dialog
    }

    private fun initContentText() {
        if (!targetText.isNullOrEmpty()) {
            binding.etBottomsheetNoticePostTarget.setText(targetText)
        }
    }

    private fun initCheckBtnClickListener() {
        binding.btnBottomsheetNoticePostTargetCheck.setOnClickListener {
            binding.etBottomsheetNoticePostTarget.text.toString().also {
                if (it.isNotEmpty()) {
                    setFragmentResult(NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS, Bundle().apply {
                        putString(NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS_CONTENT, it)
                    })
                    dismiss()
                }
            }
        }
    }

    @SuppressLint("ServiceCast")
    private fun initBottomSheetCloseBtnClickListener() {
        binding.ivBottomsheetNoticePostTargetClose.setOnClickListener {
            dismiss()
            binding.root.context.hideKeyboard(binding.root)
        }
    }
}