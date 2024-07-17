package com.univoice.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseQuickScanDto(
    @SerialName("id") val id: Int = -1,
    @SerialName("startTime") val startTime: String? = null,
    @SerialName("endTime") val endTime: String? = null,
    @SerialName("title") val title: String = "",
    @SerialName("target") val target: String? = null,
    @SerialName("writeAffiliation") val writeAffiliation: String = "",
    @SerialName("contentSummary") val contentSummary: String = "",
    @SerialName("likeCount") val likeCount: Int = 0,
    @SerialName("viewCount") val viewCount: Int = 0,
    @SerialName("category") val category: String = "",
    @SerialName("createdAt") val createdAt: String = "",
)