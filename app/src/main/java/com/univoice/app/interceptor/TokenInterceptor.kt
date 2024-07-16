package com.univoice.app.interceptor

import com.univoice.data.datasource.UserPreferencesDataSource
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class TokenInterceptor @Inject
constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var accessToken = userPreferencesDataSource.getUserAccessToken()

        // 기존 request
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $accessToken").build()
        val response = chain.proceed(request)
        Timber.tag("interceptor").d("accessToken $accessToken")

        return response
    }
}