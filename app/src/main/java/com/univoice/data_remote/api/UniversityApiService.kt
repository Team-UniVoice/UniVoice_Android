package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseUniversityDto
import com.univoice.data_remote.api.ApiKeyStorage.V1
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITYDATA
import retrofit2.http.POST

interface UniversityApiService {
    @POST("/$API/$V1/$UNIVERSITYDATA/$UNIVERSITY")
    suspend fun getUniversityNames(): BaseResponse<List<String>>
}
