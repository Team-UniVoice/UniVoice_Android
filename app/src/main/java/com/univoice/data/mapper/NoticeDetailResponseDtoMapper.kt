package com.univoice.data.mapper

import com.univoice.data.dto.response.ResponseNoticeDetailDto
import com.univoice.domain.entity.NoticeDetailEntity

fun ResponseNoticeDetailDto.toNoticeDetailEntity() = NoticeDetailEntity(
    id,
    title,
    content,
    noticeLike,
    viewCount,
    target,
    startTime,
    endTime,
    category,
    contentSummary,
    memberId,
    writeAffiliation,
    noticeImages,
    createdAt,
    likeCheck,
    saveCheck,
    dayOfWeek
)