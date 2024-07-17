package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.DepartmentDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.data_remote.api.DepartmentApiService
import javax.inject.Inject

class DepartmentDataSourceImpl @Inject constructor(
    private val departmentApiService: DepartmentApiService
) : DepartmentDataSource {
    override suspend fun getDepartments(requestDepartmentDto: RequestDepartmentDto): BaseResponse<List<String>> =
        departmentApiService.getDepartments(requestDepartmentDto)
}
