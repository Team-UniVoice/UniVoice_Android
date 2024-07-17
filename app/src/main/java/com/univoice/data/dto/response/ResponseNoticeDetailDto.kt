package com.univoice.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseNoticeDetailDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("noticeLike") val noticeLike: Int,
    @SerialName("viewCount") val viewCount: Int,
    @SerialName("target") val target: String,
    @SerialName("startTime")
    val startTime: String? = null,
    @SerialName("endTime")
    val endTime: String? = null,
    @SerialName("category")
    val category: String,
    @SerialName("contentSummary")
    val contentSummary: String,
    @SerialName("memberId")
    val memberId: Int,
    @SerialName("writeAffiliation")
    val writeAffiliation: String,
    @SerialName("noticeImages")
    val noticeImages: List<String>,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("likeCheck")
    val likeCheck: Boolean,
    @SerialName("saveCheck")
    val saveCheck: Boolean,
    @SerialName("dayOfWeek")
    val dayOfWeek: String
)