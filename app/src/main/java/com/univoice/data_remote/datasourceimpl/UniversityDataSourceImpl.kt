package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.UniversityDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data_remote.api.UniversityApiService
import javax.inject.Inject

class UniversityDataSourceImpl @Inject constructor(
    private val universityApiService: UniversityApiService
) : UniversityDataSource {
    override suspend fun postUniversityNames(): BaseResponse<List<String>> =
        universityApiService.postUniversityNames()
}
