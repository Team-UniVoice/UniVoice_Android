package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestLoginDto
import com.univoice.data.dto.response.ResponseLoginDto
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.AUTH
import com.univoice.data_remote.api.ApiKeyStorage.SIGNIN
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("/$API/$V1/$AUTH/$SIGNIN")
    suspend fun postLogin(
        @Body requestLoginDto: RequestLoginDto,
    ): BaseResponse<ResponseLoginDto>
}