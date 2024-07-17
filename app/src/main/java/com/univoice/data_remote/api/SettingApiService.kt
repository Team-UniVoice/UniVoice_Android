package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseSettingDto
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.MYPAGE
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.GET

interface SettingApiService {
    @GET("/${API}/${V1}/${MYPAGE}")
    suspend fun getMyPage(): BaseResponse<ResponseSettingDto>
}