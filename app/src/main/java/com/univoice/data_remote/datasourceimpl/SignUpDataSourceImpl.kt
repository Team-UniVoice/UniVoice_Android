package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.SignUpDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.data_remote.api.SignUpApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private val signUpApiService: SignUpApiService
) : SignUpDataSource {
    override suspend fun postUniversityNames(): BaseResponse<List<String>> =
        signUpApiService.postUniversityNames()

    override suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): BaseResponse<List<String>> =
        signUpApiService.postDepartments(requestDepartmentDto)

    override suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): BaseResponse<Unit> =
        signUpApiService.postEmail(requestCheckEmailDto)

    override suspend fun postSignUp(
        admissionNumber: RequestBody,
        name: RequestBody,
        studentNumber: RequestBody,
        email: RequestBody,
        password: RequestBody,
        universityName: RequestBody,
        departmentName: RequestBody,
        studentCardImage: MultipartBody.Part?
    ): BaseResponse<Unit> {
        return signUpApiService.postSignUp(admissionNumber, name, studentNumber, email, password, universityName, departmentName, studentCardImage)
    }
}
