package com.univoice.feature.storage

import androidx.lifecycle.ViewModel
import com.univoice.domain.entity.StorageEntity

class StorageViewModel : ViewModel() {
    val mockStorageList = listOf(
        StorageEntity(
            id = 1,
            title = "명절 귀향 버스 수요 조사",
            date = "06/26 ~ 07/01",
            likeCount = 10,
            viewCount = 100,
            avatar = "https://avatars.githubusercontent.com/u/91470334?s=400&u=4a743fda141cf8a074022b515b0ce3286e6c8560&v=4"
        ),
        StorageEntity(
            id = 2,
            title = "명절 귀향 버스 수요 조사",
            date = "06/26 ~ 07/01",
            likeCount = 20,
            viewCount = 100,
            avatar = "https://avatars.githubusercontent.com/u/91470334?s=400&u=4a743fda141cf8a074022b515b0ce3286e6c8560&v=4"
        ),
        StorageEntity(
            id = 3,
            title = "명절 귀향 버스 수요 조사",
            date = "06/26 ~ 07/01",
            likeCount = 30,
            viewCount = 100,
            avatar = "https://avatars.githubusercontent.com/u/91470334?s=400&u=4a743fda141cf8a074022b515b0ce3286e6c8560&v=4"
        ),
        StorageEntity(
            id = 4,
            title = "명절 귀향 버스 수요 조사",
            date = "06/26 ~ 07/01",
            likeCount = 40,
            viewCount = 100,
            avatar = "https://avatars.githubusercontent.com/u/91470334?s=400&u=4a743fda141cf8a074022b515b0ce3286e6c8560&v=4"
        ),
        StorageEntity(
            id = 5,
            title = "명절 귀향 버스 수요 조사",
            date = "06/26 ~ 07/01",
            likeCount = 50,
            viewCount = 100,
            avatar = "https://avatars.githubusercontent.com/u/91470334?s=400&u=4a743fda141cf8a074022b515b0ce3286e6c8560&v=4"
        ),
        StorageEntity(
            id = 6,
            title = "명절 귀향 버스 수요 조사",
            date = "06/26 ~ 07/01",
            likeCount = 60,
            viewCount = 100,
            avatar = "https://avatars.githubusercontent.com/u/91470334?s=400&u=4a743fda141cf8a074022b515b0ce3286e6c8560&v=4"
        ),
    )
}