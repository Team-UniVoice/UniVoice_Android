package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestDepartmentDto

interface DepartmentDataSource {
    suspend fun getDepartments(requestDepartmentDto: RequestDepartmentDto): BaseResponse<List<String>>
}
