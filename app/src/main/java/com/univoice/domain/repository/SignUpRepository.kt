package com.univoice.domain.repository

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SignUpRepository {
    suspend fun postUniversityNames(): Result<List<String>>
    suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): Result<BaseResponse<List<String>>>
    suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): Result<BaseResponse<Unit>>
    suspend fun postSignUp(
        admissionNumber: RequestBody,
        name: RequestBody,
        studentNumber: RequestBody,
        email: RequestBody,
        password: RequestBody,
        universityName: RequestBody,
        departmentName: RequestBody,
        studentCardImage: MultipartBody.Part?
    ): Result<BaseResponse<Unit>>
}
