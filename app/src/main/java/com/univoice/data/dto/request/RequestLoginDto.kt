package com.univoice.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("email")
    val email: String = "",
    @SerialName("password")
    val password: String = "",
)