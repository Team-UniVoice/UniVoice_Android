package com.univoice.feature.noticePost

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.databinding.FragmentNoticePostBinding

class NoticePostFragment :
    BindingFragment<FragmentNoticePostBinding>(R.layout.fragment_notice_post) {
    override fun initView() {
        initBackBtnClickListener()
        setTrackTitleTextChange()
        setTrackContentTextChange()
        setApplyBtnEnable()
        initApplyBtnClickListener()

        initTargetBtnClickListener()
        setOptionTarget()
        initOptionTargetClickListener()
        initOptionTargetDeleteBtnClickListener()
    }

    private fun setTrackTitleTextChange() {
        binding.etNoticePostTitle.doAfterTextChanged {
            setApplyBtnEnable()
        }
    }

    private fun setTrackContentTextChange() {
        binding.etNoticePostContent.doAfterTextChanged {
            setApplyBtnEnable()
        }
    }

    private fun initApplyBtnClickListener() {
        binding.btnToolbarNoticePostApply.setOnClickListener {
            findNavController().navigate(
                R.id.action_fragment_notice_post_to_fragment_home,
            )
        }
    }

    private fun setApplyBtnEnable() {
        if (binding.etNoticePostTitle.text.isNotEmpty() && binding.etNoticePostContent.text.isNotEmpty()) {
            binding.btnToolbarNoticePostApply.setBackgroundResource(R.drawable.shape_mint400_fill_20_rect)
            binding.btnToolbarNoticePostApply.isEnabled = true
        } else {
            binding.btnToolbarNoticePostApply.setBackgroundResource(R.drawable.shape_gray200_fill_20_rect)
            binding.btnToolbarNoticePostApply.isEnabled = false
        }
    }

    private fun setOptionTarget() {
        setFragmentResultListener(NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS) { _, bundle ->
            binding.layoutNoticePostOptionTarget.visibility = View.VISIBLE
            binding.tvNoticePostOptionTargetContent.text =
                bundle.getString(NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS_CONTENT)
            binding.spaceNoticePostOptionFirst.visibility = View.VISIBLE
        }
    }

    private fun initOptionTargetClickListener() {
        binding.layoutNoticePostOptionTarget.setOnClickListener {
            NoticePostTargetBottomSheet(binding.tvNoticePostOptionTargetContent.text.toString()).show(
                parentFragmentManager,
                NOTICE_POST_TARGET_BOTTOM_SHEET
            )
        }
    }

    private fun initOptionTargetDeleteBtnClickListener() {
        binding.ivNoticePostOptionTargetDelete.setOnClickListener {
            binding.layoutNoticePostOptionTarget.visibility = View.GONE
        }
    }

    private fun initBackBtnClickListener() {
        binding.ibToolbarNoticePostIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initTargetBtnClickListener() {
        binding.layoutNoticePostTargetBtn.setOnClickListener {
            NoticePostTargetBottomSheet().show(
                parentFragmentManager,
                NOTICE_POST_TARGET_BOTTOM_SHEET
            )
        }
    }

    companion object {
        const val NOTICE_POST_TARGET_BOTTOM_SHEET = "notice_post_target_bottom_sheet"
        const val NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS = "notice_post_target_bottom_sheet_args"
        const val NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS_CONTENT =
            "notice_post_target_bottom_sheet_args_content"
    }
}