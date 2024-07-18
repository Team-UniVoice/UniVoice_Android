package com.univoice.domain.entity

data class StorageListEntity (
    val id: Int,
    val title: String,
    val viewCount: Int,
    val noticeLike: Int,
    val category: String,
    val createdAt: String,
    val image: String?
)