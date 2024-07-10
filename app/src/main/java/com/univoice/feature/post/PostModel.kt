package com.univoice.feature.post

data class PostModel(
    val noticeIdx: Int,
    val wirteAffiliation: String?,
    val title: String,
    val target: String,
    var startTime: String,
    var endTime: String,
    val imageList: List<String>,
    val content: String,
    val createdAt: String,
    val viewCount: Int
)