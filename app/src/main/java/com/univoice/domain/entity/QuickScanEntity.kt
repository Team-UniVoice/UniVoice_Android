package com.univoice.domain.entity

data class QuickScanEntity (
    val avatar: String,
    val councilName: String,
    val time: String,
    val viewCount: String,
    val title: String,
    val subject: String?= null,
    val date: String?= null,
    val summary: String,
)