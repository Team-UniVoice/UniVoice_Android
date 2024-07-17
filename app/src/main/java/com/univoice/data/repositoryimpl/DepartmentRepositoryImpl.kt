package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.DepartmentDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.domain.repository.DepartmentRepository
import timber.log.Timber
import javax.inject.Inject

class DepartmentRepositoryImpl @Inject constructor(
    private val departmentDataSource: DepartmentDataSource
) : DepartmentRepository {
    override suspend fun getDepartments(requestDepartmentDto: RequestDepartmentDto): Result<BaseResponse<List<String>>> {
        return runCatching {
            val result = departmentDataSource.getDepartments(requestDepartmentDto)
            Timber.d(result.message)
            result
        }
    }
}
