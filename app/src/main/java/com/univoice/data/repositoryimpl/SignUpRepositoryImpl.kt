package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.SignUpDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.domain.repository.SignUpRepository
import timber.log.Timber
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: SignUpDataSource
) : SignUpRepository {
    override suspend fun postUniversityNames(): Result<List<String>> {
        return runCatching {
            signUpDataSource.postUniversityNames().data ?: emptyList()
        }
    }

    override suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): Result<BaseResponse<List<String>>> {
        return runCatching {
            signUpDataSource.postDepartments(requestDepartmentDto)
        }
    }

    override suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): Result<BaseResponse<List<String>>> {
        return runCatching {
            signUpDataSource.postEmail(requestCheckEmailDto)
        }
    }
}
