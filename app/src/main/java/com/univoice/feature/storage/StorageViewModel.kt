package com.univoice.feature.storage

import androidx.lifecycle.ViewModel
import com.univoice.domain.entity.NoticeListEntity

class StorageViewModel : ViewModel() {
    val mockStorageList = listOf(
        NoticeListEntity(
            subTitle = "공지사항",
            title = "전체1",
            date = "2024-07-20T18:00:00",
            like = 10,
            views = 8,
            image = true,
        ),
        NoticeListEntity(
            subTitle = "공지사항",
            title = "전체2",
            date = "2024-07-20T18:00:00",
            like = 10,
            views = 8,
            image = false,
        ),
    )
}