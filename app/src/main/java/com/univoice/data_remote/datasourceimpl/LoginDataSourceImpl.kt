package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.LoginDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestLoginDto
import com.univoice.data.dto.response.ResponseLoginDto
import com.univoice.data_remote.api.LoginApiService
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val loginApiService: LoginApiService
) : LoginDataSource {
    override suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto> =
        loginApiService.postLogin(requestLoginDto)
}