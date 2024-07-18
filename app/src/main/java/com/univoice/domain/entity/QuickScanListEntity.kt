package com.univoice.domain.entity

data class QuickScanListEntity(
    val id: Int,
    val startTime: String? = null,
    val endTime: String? = null,
    val title: String = "",
    val target: String? = null,
    val writeAffiliation: String = "",
    val contentSummary: String = "",
    val likeCount: Int = 0,
    var viewCount: Int = 0,
    val category: String = "",
    val createdAt: String = "",
    val logoImage: String? = null,
    var saveCheck: Boolean = false
)