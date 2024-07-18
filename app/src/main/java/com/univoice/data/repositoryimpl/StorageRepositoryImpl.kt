package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.StorageDataSource
import com.univoice.data.mapper.toStorageListEntity
import com.univoice.domain.entity.StorageListEntity
import com.univoice.domain.repository.StorageRepository
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageDataSource: StorageDataSource
) : StorageRepository {
    override suspend fun getSaves(): Result<List<StorageListEntity>?> {
        return runCatching {
            storageDataSource.getSaves().data?.map { it.toStorageListEntity() }
        }
    }
}