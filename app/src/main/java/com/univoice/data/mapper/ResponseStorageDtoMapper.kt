package com.univoice.data.mapper

import com.univoice.data.dto.response.ResponseStorageDto
import com.univoice.domain.entity.StorageListEntity

fun ResponseStorageDto.toStorageListEntity() = StorageListEntity(
    id,
    title,
    viewCount,
    noticeLike,
    category,
    startTime,
    endTime,
    createdAt,
    image ?: ""
)

