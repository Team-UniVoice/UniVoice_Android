package com.univoice.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseNoticeAllDto(
    @SerialName("id")
    val id: Int,
    @SerialName("startTime")
    val startTime: String? = "",
    @SerialName("endTime")
    val endTime: String? = "",
    @SerialName("title")
    val title: String,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("viewCount")
    val viewCount: Int,
    @SerialName("category")
    val category: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("image")
    val image: String? = "",
)