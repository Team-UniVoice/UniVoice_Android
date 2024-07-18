package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.PostDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data_remote.api.PostApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val postApiService: PostApiService
) : PostDataSource {
    override suspend fun postNotice(
        title: RequestBody,
        content: RequestBody,
        target: RequestBody?,
        startTime: RequestBody?,
        endTime: RequestBody?,
        noticeImages: List<MultipartBody.Part>?
    ): BaseResponse<Unit> {
        return postApiService.postNotice(title, content, target, startTime, endTime, noticeImages)
    }
}