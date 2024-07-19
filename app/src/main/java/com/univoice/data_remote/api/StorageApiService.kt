package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseStorageDto
import com.univoice.data_remote.api.ApiKeyStorage.ALL
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.SAVE
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.GET

interface StorageApiService {
    @GET("$API/$V1/$NOTICE/$SAVE/$ALL")
    suspend fun getSaves(): BaseResponse<List<ResponseStorageDto>>
}