package com.univoice.data.mapper

import com.univoice.data.dto.response.ResponseStorageDto
import com.univoice.domain.entity.NoticeListEntity

fun ResponseStorageDto.toStorageListEntity() = NoticeListEntity(
    id,
    title,
    viewCount,
    noticeLike,
    category,
    createdAt,
    image?: ""
)

