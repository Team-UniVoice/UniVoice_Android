package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestQuickScanDto
import com.univoice.data.dto.response.ResponseQuickScanDto
import com.univoice.data.dto.response.ResponseStorageDto
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.QUICK
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.Body
import retrofit2.http.POST

interface QuickScanApiService {
    @POST("$API/$V1/$NOTICE/$QUICK")
    suspend fun postQuickScan(
        @Body requestQuickScanDto: RequestQuickScanDto
    ): BaseResponse<List<ResponseQuickScanDto>>
}