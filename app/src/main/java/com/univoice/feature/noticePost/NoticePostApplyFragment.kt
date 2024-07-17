package com.univoice.feature.noticePost

import android.animation.Animator
import android.content.Intent
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.databinding.FragmentNoticePostApplyBinding
import com.univoice.feature.MainActivity

class NoticePostApplyFragment :
    BindingFragment<FragmentNoticePostApplyBinding>(R.layout.fragment_notice_post_apply) {
    override fun initView() {
        initLottieAnimation()
        initConfirmBtnClickListener()
    }

    private fun initLottieAnimation() {
        binding.lottieNoticePostApply.apply {
            playAnimation()
            addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    binding.btnNoticePostApplyConfirm.visibility = View.VISIBLE
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
    }

    private fun initConfirmBtnClickListener() {
        binding.btnNoticePostApplyConfirm.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(findNavController().graph.startDestinationId, true)
                .build()
            findNavController().navigate(R.id.action_fragment_notice_post_apply_to_fragment_home, null, navOptions)
        }
    }

}