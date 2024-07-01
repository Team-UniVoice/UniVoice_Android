package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.ExampleDataSource
import com.univoice.data.dto.response.UserDataDto
import com.univoice.data_remote.api.ExampleApiService
import javax.inject.Inject

class ExampleDataSourceImpl @Inject constructor(
    private val exampleApiService: ExampleApiService
) : ExampleDataSource {
    override suspend fun getExample(page: Int): UserDataDto {
        return exampleApiService.getListUsers(page)
    }
}