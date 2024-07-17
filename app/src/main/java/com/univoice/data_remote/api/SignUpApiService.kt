package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.data_remote.api.ApiKeyStorage.V1
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.AUTH
import com.univoice.data_remote.api.ApiKeyStorage.CHECK_EMAIL
import com.univoice.data_remote.api.ApiKeyStorage.DEPARTMENT
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY_DATA
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApiService {
    @POST("/$API/$V1/$UNIVERSITY_DATA/$UNIVERSITY")
    suspend fun postUniversityNames(): BaseResponse<List<String>>

    @POST("/$API/$V1/$UNIVERSITY_DATA/$DEPARTMENT")
    suspend fun postDepartments(
        @Body requestDepartmentDto: RequestDepartmentDto
    ): BaseResponse<List<String>>

    @POST("/$API/$V1/$AUTH/$CHECK_EMAIL")
    suspend fun postEmail(
        @Body requestCheckEmailDto: RequestCheckEmailDto
    ): BaseResponse<List<String>>
}