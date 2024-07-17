package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseUniversityDto

interface UniversityDataSource {
    suspend fun getUniversityNames(): BaseResponse<List<String>>
}
