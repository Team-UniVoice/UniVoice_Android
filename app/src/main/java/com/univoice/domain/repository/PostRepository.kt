package com.univoice.domain.repository

import java.io.File

interface PostRepository {
    suspend fun postSignUp(
        title: String,
        content: String,
        target: String?,
        startTime: String?,
        endTime: String?,
        noticeImages: List<File>?,
    ): Result<Any>
}