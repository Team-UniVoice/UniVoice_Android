package com.univoice.domain.entity

data class NoticeListEntity(
    val id: Int,
    val title: String,
    val likeCount: Int,
    val viewCount: Int,
    val category: String,
    val date: String,
    val image: String,
)