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

    override suspend fun saveCheckLogin(checkLogin: Boolean) {
        dataSource.saveCheckLogin(checkLogin)
    }

    override fun getCheckLogin(): Flow<Boolean> = dataSource.getCheckLogin()

    override suspend fun clear() = dataSource.clear()

}