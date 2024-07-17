package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.SignUpDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.data_remote.api.SignUpApiService
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private val signUpApiService: SignUpApiService
) : SignUpDataSource {
    override suspend fun postUniversityNames(): BaseResponse<List<String>> =
        signUpApiService.postUniversityNames()

    override suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): BaseResponse<List<String>> =
        signUpApiService.postDepartments(requestDepartmentDto)
}
