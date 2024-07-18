package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.CREATE
import com.univoice.data_remote.api.ApiKeyStorage.NOTICE
import com.univoice.data_remote.api.ApiKeyStorage.V1
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PostApiService {
    @Multipart
    @POST("/$API/$V1/$NOTICE/$CREATE")
    suspend fun postNotice(
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody,
        @Part("target") target: RequestBody?,
        @Part("startTime") startTime: RequestBody?,
        @Part("endTime") endTime: RequestBody?,
        @Part noticeImages: List<MultipartBody.Part>?,
    ): BaseResponse<Unit>
}