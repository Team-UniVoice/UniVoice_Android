package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeAllDto
import com.univoice.data.dto.response.ResponseNoticeQuickScanDto
import com.univoice.data_remote.api.ApiKeyStorage.ALL
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.COLLEGE_DEPARTMENT
import com.univoice.data_remote.api.ApiKeyStorage.DEPARTMENT
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.GET

interface HomeApiService {
    @GET("/$API/$V1/$NOTICE/${ApiKeyStorage.QUICKHEAD}")
    suspend fun getNoticeQuickScan(): BaseResponse<ResponseNoticeQuickScanDto>

    @GET("/$API/$V1/$NOTICE/$ALL")
    suspend fun getNoticeAll(): BaseResponse<List<ResponseNoticeAllDto>>

    @GET("/$API/$V1/$NOTICE/$UNIVERSITY")
    suspend fun getNoticeUniversity(): BaseResponse<List<ResponseNoticeAllDto>>

    @GET("/$API/$V1/$NOTICE/$COLLEGE_DEPARTMENT")
    suspend fun getNoticeCollege(): BaseResponse<List<ResponseNoticeAllDto>>

    @GET("/$API/$V1/$NOTICE/$DEPARTMENT")
    suspend fun getNoticeDepartment(): BaseResponse<List<ResponseNoticeAllDto>>
}