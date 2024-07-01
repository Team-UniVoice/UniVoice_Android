package com.univoice.data.datasource

import com.univoice.data.dto.response.UserDataDto

interface ExampleDataSource {
    suspend fun getExample(page: Int): UserDataDto
}