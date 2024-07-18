package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PostDataSource {
    suspend fun postNotice(
        title: RequestBody,
        content: RequestBody,
        target: RequestBody?,
        startTime: RequestBody?,
        endTime: RequestBody?,
        noticeImages: List<MultipartBody.Part>?,
    ): BaseResponse<Unit>
}