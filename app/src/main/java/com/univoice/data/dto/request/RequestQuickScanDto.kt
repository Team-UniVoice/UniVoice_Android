package com.univoice.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data class RequestQuickScanDto(
    @SerialName("affiliation") val affiliation: String = ""
)