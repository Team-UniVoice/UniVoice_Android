package com.univoice.domain.entity

data class NoticeListEntity(
    val subTitle: String,
    val title: String,
    val date: String,
    val like: Int,
    val views: Int,
    val image: Boolean,
)