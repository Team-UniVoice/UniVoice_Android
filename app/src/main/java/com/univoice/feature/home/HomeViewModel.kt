package com.univoice.feature.home

import androidx.lifecycle.ViewModel
import com.univoice.domain.entity.NoticeCategoryListEntity
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.QuickScanListEntity

class HomeViewModel : ViewModel() {
    val mockQuickScanList = listOf(
        QuickScanListEntity(
            name = "아주대학교\n총학생회",
            image = "",
            count = 5
        ),
        QuickScanListEntity(
            name = "소프트웨어융합대학\n학생회",
            image = "",
            count = 10
        ),
        QuickScanListEntity(
            name = "경영대학\n학생회",
            image = "",
            count = 5
        ),
        QuickScanListEntity(
            name = "인문대학\n학생회",
            image = "",
            count = 4
        ),
        QuickScanListEntity(
            name = "예술대학\n학생회",
            image = "",
            count = 1
        ),
    )

    val mockNoticeList = listOf(
        NoticeCategoryListEntity(
            name = "전체",
            noticeData = listOf(
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체1",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체2",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체3",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체4",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체5",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체6",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체7",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체8",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체9",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체10",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체11",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = false,
                ),
            )
        ),
        NoticeCategoryListEntity(
            name = "총학생회",
            noticeData = listOf(
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회1",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회2",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회3",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회4",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회5",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = false,
                ),
            )
        ),
        NoticeCategoryListEntity(
            name = "소프트웨어융합대학 학생회",
            noticeData = listOf()
        ),
        NoticeCategoryListEntity(
            name = "자연과학대학 학생회",
            noticeData = listOf(
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "자연과학대학 학생회1",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "자연과학대학 학생회2",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "자연과학대학 학생회3",
                    date = "2024/07/12",
                    like = 10,
                    views = 8,
                    image = true,
                ),
            )
        ),
    )
}