package com.univoice.domain.entity


data class NoticeDetailEntity(
    val id: Int,
    val title: String,
    val content: String,
    val noticeLike: Int,
    val viewCount: Int,
    val target: String,
    val startTime: String?,
    val endTime: String?,
    val category: String,
    val contentSummary: String,
    val memberId: Int,
    val writeAffiliation: String,
    val noticeImages: List<String>,
    val createdAt: String,
    val likeCheck: Boolean,
    val saveCheck: Boolean,
    val dayOfWeek: String
)