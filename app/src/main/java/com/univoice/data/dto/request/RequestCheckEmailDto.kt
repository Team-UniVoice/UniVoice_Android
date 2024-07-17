package com.univoice.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCheckEmailDto(
    @SerialName("email")
    val email: String
)
