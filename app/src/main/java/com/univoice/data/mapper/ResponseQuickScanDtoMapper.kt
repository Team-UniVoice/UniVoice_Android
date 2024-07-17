package com.univoice.data.mapper

import com.univoice.data.dto.response.ResponseQuickScanDto
import com.univoice.domain.entity.QuickScanListEntity

fun ResponseQuickScanDto.toQuickScanListEntity() = QuickScanListEntity(
    id,
    startTime,
    endTime,
    title,
    target,
    writeAffiliation,
    contentSummary,
    likeCount,
    viewCount,
    category,
    createdAt
)