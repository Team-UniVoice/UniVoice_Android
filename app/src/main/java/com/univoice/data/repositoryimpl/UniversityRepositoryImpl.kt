package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.UniversityDataSource
import com.univoice.data.dto.response.ResponseUniversityDto
import com.univoice.domain.repository.UniversityRepository
import timber.log.Timber
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityDataSource: UniversityDataSource
) : UniversityRepository {
    override suspend fun getUniversityNames(): Result<List<String>> {
        return runCatching {
            universityDataSource.getUniversityNames().data ?: emptyList()
        }
    }
}
