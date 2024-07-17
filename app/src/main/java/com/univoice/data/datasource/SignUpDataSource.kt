package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto

interface SignUpDataSource {
    suspend fun postUniversityNames(): BaseResponse<List<String>>
    suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): BaseResponse<List<String>>
    suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): BaseResponse<List<String>>

}
