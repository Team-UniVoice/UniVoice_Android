package com.univoice.domain.repository

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestDepartmentDto

interface DepartmentRepository {
    suspend fun getDepartments(requestDepartmentDto: RequestDepartmentDto): Result<BaseResponse<List<String>>>
}
