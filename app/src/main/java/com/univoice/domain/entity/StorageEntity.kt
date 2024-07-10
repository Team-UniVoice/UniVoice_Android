package com.univoice.domain.entity

data class StorageEntity(
    val id: Int,
    val title: String,
    val date: String,
    val likeCount: Int,
    val viewCount: Int,
    val avatar: String
)