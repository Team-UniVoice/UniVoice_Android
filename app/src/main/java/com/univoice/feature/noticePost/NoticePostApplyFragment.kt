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
        showLoadingAnimation()
        initLottieAnimation()
        initConfirmBtnClickListener()
    }

    private fun showLoadingAnimation() {
        with(binding) {
            lottieNoticePostApplyLoading.visibility = View.VISIBLE
            lottieNoticePostApplyFinish.visibility = View.GONE
        }
    }

    private fun initLottieAnimation() {
        binding.lottieNoticePostApplyLoading.postDelayed({
            showCompleteAnimation()
        }, 2500)
    }

    private fun showCompleteAnimation() {
        with(binding) {
            lottieNoticePostApplyLoading.visibility = View.GONE
            lottieNoticePostApplyFinish.visibility = View.VISIBLE
            btnNoticePostApplyConfirm.visibility = View.VISIBLE
            lottieNoticePostApplyFinish.playAnimation()
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