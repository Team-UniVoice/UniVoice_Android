package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestQuickScanDto
import com.univoice.data.dto.response.ResponseQuickScanDto
import com.univoice.data.dto.response.ResponseStorageDto
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.QUICK
import com.univoice.data_remote.api.ApiKeyStorage.V1
import com.univoice.data_remote.api.ApiKeyStorage.VIEW_CHECK
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface QuickScanApiService {
    @POST("$API/$V1/$NOTICE/$QUICK")
    suspend fun postQuickScan(
        @Body requestQuickScanDto: RequestQuickScanDto
    ): BaseResponse<List<ResponseQuickScanDto>>

    @POST("$API/$V1/$NOTICE/$VIEW_CHECK/{noticeId}")
    suspend fun postQuickScanViewCheck(
        @Path("noticeId") noticeId: Int
    ): BaseResponse<Unit>
}