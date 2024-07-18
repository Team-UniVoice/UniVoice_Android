package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.PostDataSource
import com.univoice.data.repositoryimpl.util.extractErrorMessage
import com.univoice.domain.repository.PostRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource
) : PostRepository {
    override suspend fun postSignUp(
        title: String,
        content: String,
        target: String?,
        startTime: String?,
        endTime: String?,
        noticeImages: List<File>?
    ): Result<Any> {
        return runCatching {

            val titleBody =
                title.toRequestBody("text/plain".toMediaTypeOrNull())
            val contentBody = content.toRequestBody("text/plain".toMediaTypeOrNull())
            val targetBody = target?.toRequestBody("text/plain".toMediaTypeOrNull())
            val startTimeBody = startTime?.toRequestBody("text/plain".toMediaTypeOrNull())
            val endTimeBody = endTime?.toRequestBody("text/plain".toMediaTypeOrNull())

            // 파일 데이터를 읽어와서 별도의 파트로 추가
            val fileParts = noticeImages?.map {
                val requestBody = it.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("noticeImages", it.name, requestBody)
            }

            val response = postDataSource.postNotice(
                titleBody,
                contentBody,
                targetBody,
                startTimeBody,
                endTimeBody,
                fileParts,
            )

            if (response.status in 200..299) {
                response.data ?: ""
            } else {
                response.message ?: ""
            }

        }.onFailure { throwable ->
            return when (throwable) {
                is HttpException -> Result.failure(IOException(throwable.extractErrorMessage()))
                else -> Result.failure(throwable)
            }
        }
    }
}