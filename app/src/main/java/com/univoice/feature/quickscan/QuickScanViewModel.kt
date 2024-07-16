package com.univoice.feature.quickscan

import androidx.lifecycle.ViewModel
import com.univoice.domain.entity.QuickScanEntity

class QuickScanViewModel : ViewModel() {
    val mockQuickScanList = listOf(
        QuickScanEntity(
            id = 1,
            councilName = "제 58대 총학생회 개화",
            time = "2024-07-10T00:08:27",
            viewCount = "20회",
            title = "6.25전쟁 기념일",
            summary = "올해는 6.25전쟁이 발발한 지 74주년이 되는해입니다. 국가를 위해 순국하신 참전 용사들의 숭고한 희생정신을 추모하며 그들의 공훈을 기립니다.",
            isBookmark = true
        ),
        QuickScanEntity(
            id = 2,
            avatar = "https://avatars.githubusercontent.com/u/173128955?s=400&u=5ea0c63681822c75fa707b1951948f5bae9410eb&v=4",
            councilName = "제 58대 총학생회 개화",
            time = "2024-07-10T00:08:27",
            viewCount = "20회",
            title = "2024 대학생 여름농활 준비 네트워크에서 주최하는 농촌봉사활동과 관련하여 학우분들의 문의가 많아 말씀드립니다.",
            summary = "최근 교내 내부 및 외부에 포스터, 현수막 등으로 홍보가 진행되었던 2024 전국 대학생 여름농활은 2024 대학생 여름농활 준비 네트워크에서 단독으로 개최하는 행사이며 홍익대학교 총학생회 및 단과대학 학생회가 주관하는 행사가 아님을 알려드립니다.",
            isBookmark = true
        ),
        QuickScanEntity(
            id = 3,
            avatar = "https://avatars.githubusercontent.com/u/173128955?s=400&u=5ea0c63681822c75fa707b1951948f5bae9410eb&v=4",
            councilName = "제 58대 총학생회 개화",
            time = "2024-07-10T00:08:27",
            viewCount = "20회",
            title = "2024학년도 제2학기 교내장학금 신청안내",
            subject = "2024-1학기 재학생",
            date = "05/21(화) 14:00 ~ 06/20(목) 22:00",
            summary = "2024학년도 2학기 교내장학금 신청 안내드립니다. 반드시 확인하고 놓치지 마시기 바랍니다.",
            isBookmark = false
        ),
        QuickScanEntity(
            id = 4,
            avatar = "https://avatars.githubusercontent.com/u/173128955?s=400&u=5ea0c63681822c75fa707b1951948f5bae9410eb&v=4",
            councilName = "제 58대 총학생회 개화",
            time = "2024-07-10T00:08:27",
            viewCount = "20회",
            title = "2024 홍익대학교 겨울잠바 디자인 공모전",
            subject = "2024-1학기 재학생",
            date = "05/21(화) 14:00 ~ 06/20(목) 22:00",
            summary = "2024학년도 홍익대학교만의 겨울잠바 디자인을 제작하기 위해 겨울잠바 디자인 공모전을 진행합니다.",
            isBookmark = false
        ),
    )

    fun updateBookmark(id: Int, isBookmark: Boolean) {
        val target = mockQuickScanList.find { it.id == id }
        target?.isBookmark = isBookmark
    }
}