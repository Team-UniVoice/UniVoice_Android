package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse

interface UniversityDataSource {
    suspend fun postUniversityNames(): BaseResponse<List<String>>
}
