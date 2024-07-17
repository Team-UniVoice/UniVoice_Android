package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeAllDto
import com.univoice.data.dto.response.ResponseNoticeQuickScanDto
import com.univoice.data_remote.api.ApiKeyStorage.ALL
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.GET

interface HomeApiService {
    @GET("/$API/$V1/$NOTICE/${ApiKeyStorage.QUICKHEAD}")
    suspend fun getNoticeQuickScan(): BaseResponse<ResponseNoticeQuickScanDto>

    @GET("/$API/$V1/$NOTICE/$ALL")
    suspend fun getNoticeAll(): BaseResponse<List<ResponseNoticeAllDto>>
}