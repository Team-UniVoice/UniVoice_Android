package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SignUpDataSource {
    suspend fun postUniversityNames(): BaseResponse<List<String>>
    suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): BaseResponse<List<String>>
    suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): BaseResponse<Unit>
    suspend fun postSignUp(
        admissionNumber: RequestBody,
        name: RequestBody,
        studentNumber: RequestBody,
        email: RequestBody,
        password: RequestBody,
        universityName: RequestBody,
        departmentName: RequestBody,
        studentCardImage: MultipartBody.Part?,
    ): BaseResponse<Unit>
}
