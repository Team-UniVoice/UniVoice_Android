package com.univoice.domain.repository

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

interface SignUpRepository {
    suspend fun postUniversityNames(): Result<List<String>>
    suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): Result<BaseResponse<List<String>>>
    suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): Result<BaseResponse<Unit>>
    suspend fun postSignUp(
        admissionNumber: String,
        name: String,
        studentNumber: String,
        email: String,
        password: String,
        universityName: String,
        departmentName: String,
        studentCardImage: File
    ): Result<Any>
}
