package com.univoice.feature.storage

import androidx.lifecycle.ViewModel
import com.univoice.domain.entity.NoticeListEntity

class StorageViewModel : ViewModel() {
    val mockStorageList = listOf(
        NoticeListEntity(
            id = 4000,
            category = "공지사항",
            title = "전체1",
            date = "2024-07-20T18:00:00",
            likeCount = 10,
            viewCount = 8,
            image = "",
        ),
        NoticeListEntity(
            id = 4001,
            category = "공지사항",
            title = "전체2",
            date = "2024-07-20T18:00:00",
            likeCount = 10,
            viewCount = 8,
            image = "",
        ),
    )
}