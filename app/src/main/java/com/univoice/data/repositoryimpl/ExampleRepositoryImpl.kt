package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.ExampleDataSource
import com.univoice.data.mapper.toUserEntity
import com.univoice.domain.entity.UserEntity
import com.univoice.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) : ExampleRepository {
    override suspend fun getExample(page: Int): Result<List<UserEntity>> {
        return runCatching {
            exampleDataSource.getExample(page).data.map { it.toUserEntity() }
        }
    }
}