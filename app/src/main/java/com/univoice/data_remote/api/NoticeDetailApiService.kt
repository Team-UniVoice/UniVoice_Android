package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeDetailDto
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE_ID
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticeDetailApiService {
    @GET("/$API/$V1/$NOTICE/{$NOTICE_ID}")
    suspend fun getNoticeDetail(
        @Path(NOTICE_ID) noticeId: Int
    ): BaseResponse<ResponseNoticeDetailDto>
}