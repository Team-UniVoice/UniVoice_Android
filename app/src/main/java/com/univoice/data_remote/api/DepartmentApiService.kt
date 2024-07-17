package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.DEPARTMENT
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITYDATA
import com.univoice.data_remote.api.ApiKeyStorage.V1
import retrofit2.http.Body
import retrofit2.http.POST

interface DepartmentApiService {
    @POST("/$API/$V1/$UNIVERSITYDATA/$DEPARTMENT")
    suspend fun getDepartments(
        @Body requestDepartmentDto: RequestDepartmentDto
    ): BaseResponse<List<String>>
}
