package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.StorageDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseStorageDto
import com.univoice.data_remote.api.StorageApiService
import javax.inject.Inject

class StorageDataSourceImpl @Inject constructor(
    private val storageApiService: StorageApiService
) : StorageDataSource {
    override suspend fun getSaves(): BaseResponse<List<ResponseStorageDto>> {
        return storageApiService.getSaves()
    }
}