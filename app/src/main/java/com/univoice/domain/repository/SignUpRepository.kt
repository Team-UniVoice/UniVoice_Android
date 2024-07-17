package com.univoice.domain.repository

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto

interface SignUpRepository {
    suspend fun postUniversityNames(): Result<List<String>>
    suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): Result<BaseResponse<List<String>>>
    suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): Result<BaseResponse<List<String>>>

}
