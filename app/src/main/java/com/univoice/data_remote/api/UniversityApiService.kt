package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data_remote.api.ApiKeyStorage.V1
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY_DATA
import retrofit2.http.POST

interface UniversityApiService {
    @POST("/$API/$V1/$UNIVERSITY_DATA/$UNIVERSITY")
    suspend fun postUniversityNames(): BaseResponse<List<String>>
}
