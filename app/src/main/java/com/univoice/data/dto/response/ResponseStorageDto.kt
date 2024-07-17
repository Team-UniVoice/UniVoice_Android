package com.univoice.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseStorageDto(
    @SerialName("id") val id: Int = -1,
    @SerialName("title") val title: String = "",
    @SerialName("viewCount") val viewCount: Int = 0,
    @SerialName("noticeLike") val noticeLike: Int = 0,
    @SerialName("category") val category: String = "",
    @SerialName("startTime") val startTime: String? = null,
    @SerialName("endTime") val endTime: String? = null,
    @SerialName("createdAt") val createdAt: String = "",
    @SerialName("updatedAt") val updatedAt: String = "",
    @SerialName("image") val image: String? = null
)