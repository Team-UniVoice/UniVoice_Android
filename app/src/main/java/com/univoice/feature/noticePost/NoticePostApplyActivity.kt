package com.univoice.feature.noticePost

import android.animation.Animator
import android.content.Intent
import android.view.View
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityNoticePostApplyBinding
import com.univoice.feature.MainActivity

class NoticePostApplyActivity :
    BindingActivity<ActivityNoticePostApplyBinding>(R.layout.activity_notice_post_apply) {
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
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
        }
    }

}