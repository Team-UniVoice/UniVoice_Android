package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseStorageDto

interface StorageDataSource {
    suspend fun getSaves(): BaseResponse<List<ResponseStorageDto>>
}