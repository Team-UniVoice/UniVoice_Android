package com.univoice.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSettingDto(
    @SerialName("id") val id: Int = -1,
    @SerialName("name") val name: String = "",
    @SerialName("collegeDepartment") val collegeDepartment: String = "",
    @SerialName("department") val department: String = "",
    @SerialName("admissionNumber") val admissionNumber: String = "",
    @SerialName("university") val university: String = "",
    @SerialName("universityLogoImage") val universityLogoImage: String = "",
)