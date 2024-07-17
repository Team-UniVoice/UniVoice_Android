package com.univoice.data.datasource

import com.univoice.data.dto.response.ResponseUniversityDto

interface UniversityDataSource {
    suspend fun getUniversityNames(): ResponseUniversityDto
}
