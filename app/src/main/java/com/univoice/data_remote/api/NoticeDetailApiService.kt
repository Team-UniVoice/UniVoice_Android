package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeDetailDto
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.CANCEL
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE_ID
import com.univoice.data_remote.api.ApiKeyStorage.SAVE
import com.univoice.data_remote.api.ApiKeyStorage.V1
import com.univoice.data_remote.api.ApiKeyStorage.VIEW_COUNT
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoticeDetailApiService {
    @GET("/$API/$V1/$NOTICE/{$NOTICE_ID}")
    suspend fun getNoticeDetail(
        @Path(NOTICE_ID) noticeId: Int
    ): BaseResponse<ResponseNoticeDetailDto>

    @POST("$API/$V1/$NOTICE/$VIEW_COUNT/{$NOTICE_ID}")
    suspend fun postNoticeDetailViewCount(
        @Path(NOTICE_ID) noticeId: Int
    ): BaseResponse<Unit>

    @POST("$API/$V1/$NOTICE/$SAVE/{$NOTICE_ID}")
    suspend fun postNoticeDetailSave(
        @Path(NOTICE_ID) noticeId: Int
    ): BaseResponse<Unit>

    @POST("$API/$V1/$NOTICE/$SAVE/$CANCEL/{$NOTICE_ID}")
    suspend fun postNoticeDetailCancel(
        @Path(NOTICE_ID) noticeId: Int
    ): BaseResponse<Unit>
}