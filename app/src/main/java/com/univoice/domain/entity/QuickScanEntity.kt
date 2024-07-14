package com.univoice.domain.entity

data class QuickScanEntity(
    val id: Int,
    val avatar: String? = null,
    val councilName: String,
    var time: String,
    var viewCount: String,
    val title: String,
    val subject: String? = null,
    val date: String? = null,
    val summary: String,
    var isBookmark: Boolean,
)