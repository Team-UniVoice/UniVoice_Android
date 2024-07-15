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
            count = 0
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
                    title = "전체1 동해물과백두산이마르고닳도록하느님이보우하사우리나라만세무궁화삼천리화려강산대한사람대한으로길이보전하세",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체2",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체3 동해물과백두산이마르고닳도록하느님이보우하사우리나라만세무궁화삼천리화려강산대한사람대한으로길이보전하세 동해물과백두산이마르고닳도록하느님이보우하사우리나라만세무궁화삼천리화려강산대한사람대한으로길이보전하세",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체4",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체5",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체6",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체7",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체8",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체9",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체10",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "전체11",
                    startDate = "06/26",
                    endDate = "07/01",
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
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회2",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회3",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회4",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "총학생회5",
                    startDate = "06/26",
                    endDate = "07/01",
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
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "자연과학대학 학생회2",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = false,
                ),
                NoticeListEntity(
                    subTitle = "공지사항",
                    title = "자연과학대학 학생회3",
                    startDate = "06/26",
                    endDate = "07/01",
                    like = 10,
                    views = 8,
                    image = true,
                ),
            )
        ),
    )
}