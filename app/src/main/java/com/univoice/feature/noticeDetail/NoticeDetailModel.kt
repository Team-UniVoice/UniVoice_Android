package com.univoice.feature.noticeDetail

data class NoticeDetailModel(
    val noticeId: Int,
    val writeAffiliation: String,
    val title: String,
    val target: String,
    val startTime: String,
    val endTime: String,
    val imageList: List<String>,
    val content: String,
    val createdAt: String,
    val viewCount: Int
)