package com.univoice.data.mapper

import com.univoice.data.dto.response.ResponseNoticeAllDto
import com.univoice.domain.entity.NoticeListEntity

fun ResponseNoticeAllDto.toNoticeListEntity() = NoticeListEntity(
    id,
    title,
    likeCount,
    viewCount,
    category,
    createdAt,
    image ?: ""
)