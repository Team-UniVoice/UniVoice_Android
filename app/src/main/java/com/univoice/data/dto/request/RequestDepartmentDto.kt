package com.univoice.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDepartmentDto(
    @SerialName("universityName")
    val universityName: String = ""
)
