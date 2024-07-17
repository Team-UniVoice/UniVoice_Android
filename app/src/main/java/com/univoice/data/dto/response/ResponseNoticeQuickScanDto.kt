package com.univoice.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseNoticeQuickScanDto(
    @SerialName("universityName")
    val universityName: String = "",
    @SerialName("universityNameCount")
    val universityNameCount: Int = 0,
    @SerialName("universityLogoImage")
    val universityLogoImage: String = "",
    @SerialName("collegeDepartmentName")
    val collegeDepartmentName: String = "",
    @SerialName("collegeDepartmentCount")
    val collegeDepartmentCount: Int = 0,
    @SerialName("collegeDepartmentLogoImage")
    val collegeDepartmentLogoImage: String = "",
    @SerialName("departmentName")
    val departmentName: String = "",
    @SerialName("departmentCount")
    val departmentCount: Int = 0,
    @SerialName("departmentLogoImage")
    val departmentLogoImage: String = "",
)