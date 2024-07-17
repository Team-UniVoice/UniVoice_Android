package com.univoice.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDepartmentDto(
    @SerialName("status")
    val status: Int,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: List<String>
)
