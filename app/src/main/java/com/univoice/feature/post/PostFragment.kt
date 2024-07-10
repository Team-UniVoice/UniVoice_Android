package com.univoice.feature.post

import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.databinding.FragmentPostBinding

class PostFragment : BindingFragment<FragmentPostBinding>(R.layout.fragment_post) {

    override fun initView() {
        binding.model = mockData
        initLikeBtnClickListener()
        initBookMarkBtnClickListener()
        initPostItemAdapter()
    }

    // 좋아요 버튼 클릭
    private fun initLikeBtnClickListener() {
        with(binding) {
            btnPostLike.setOnClickListener {
                btnPostLike.isSelected = !binding.btnPostLike.isSelected
            }
        }
    }

    // 북마크 버튼 클릭
    private fun initBookMarkBtnClickListener() {
        with(binding) {
            btnPostBookmark.setOnClickListener {
                btnPostBookmark.isSelected = !binding.btnPostBookmark.isSelected
            }
        }
    }

    // Adapter 설정
    private fun initPostItemAdapter() {
        val adapter = PostViewPagerAdapter(mockData.imageList)
        binding.vpPostImage.adapter = adapter
        binding.indicatorPostImage.setViewPager(binding.vpPostImage)
    }

    companion object {
        var mockData = PostModel(
            noticeIdx = 1,
            wirteAffiliation = null,
            title = "2024 대학생 여름농활 준비 네트워크에서 주최하는 농촌봉사활동과 관련하여 학우분들의 문의가 많아 말씀드립니다.",
            target = "2024-1학기 재학생",
            startTime = "05/21(화) 14:00",
            endTime = "06/20(목) 22:00",
            imageList = listOf(
                "https://img.khan.co.kr/news/2024/03/23/news-p.v1.20240323.c159a4cab6f64473adf462d873e01e43_P1.webp",
                "https://shop.peopet.co.kr/data/goods/388/2022/06/_temp_16557127733930view.jpg"
            ),
            content = "시험기간 공부하시느라 힘드시죠??\noo학생회에서 간식꾸러미를 준비했습니다!\n가나다라마바사아자차카 타파하가나다라마바사아",
            createdAt = "2024/06/22",
            viewCount = 10
        )
    }
}