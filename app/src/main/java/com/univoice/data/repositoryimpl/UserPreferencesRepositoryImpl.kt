package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.UserPreferencesDataSource
import com.univoice.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override suspend fun saveUserId(userId: String, userPwd: String) {
        dataSource.saveUserId(userId, userPwd)
    }

    override fun getUserId(): Flow<String?> = dataSource.getUserId()

    override fun getUserPwd(): Flow<String?> = dataSource.getUserPwd()
}