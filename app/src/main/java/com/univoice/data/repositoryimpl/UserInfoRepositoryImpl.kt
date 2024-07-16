package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.UserPreferencesDataSource
import com.univoice.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val dataSource: UserPreferencesDataSource
) : UserInfoRepository {

    override suspend fun saveUserAccessToken(accessToken: String) {
        dataSource.saveUserAccessToken(accessToken)
    }

    override fun getUserAccessToken(): Flow<String?> = dataSource.getUserAccessToken()
}