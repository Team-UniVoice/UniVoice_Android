package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.LoginDataSource
import com.univoice.data.dto.request.RequestLoginDto
import com.univoice.domain.repository.LoginRepository
import timber.log.Timber
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override suspend fun postLogin(email: String, password: String): Result<String?> {
        return runCatching {
            val result = loginDataSource.postLogin(RequestLoginDto(email, password))
            Timber.d(result.message)
            result.data?.accessToken
        }
    }
}