package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestLoginDto
import com.univoice.data.dto.response.ResponseLoginDto

interface LoginDataSource {
    suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto>
}