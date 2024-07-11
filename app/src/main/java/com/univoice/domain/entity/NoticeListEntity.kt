package com.univoice.domain.entity

data class NoticeListEntity(
    val subTitle: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val like: Int,
    val views: Int,
    val image: Boolean,
)