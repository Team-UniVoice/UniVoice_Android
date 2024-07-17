package com.univoice.data_remote.api

import com.univoice.data.dto.response.BaseResponse
import com.univoice.data.dto.response.StorageResponseDto
import com.univoice.data_remote.api.ExampleApiService.Companion.API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface StorageApiService {
    companion object {
        const val NOTICE = "notice"
        const val V1 = "v1"
        const val SAVE = "save"
        const val ALL = "all"
    }

    @GET("$API/$V1/$NOTICE/$SAVE/$ALL")
    suspend fun getSaves(
    ): BaseResponse<StorageResponseDto>
}