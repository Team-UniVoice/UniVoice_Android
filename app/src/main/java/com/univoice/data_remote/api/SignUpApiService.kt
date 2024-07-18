package com.univoice.data_remote.api

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.data_remote.api.ApiKeyStorage.V1
import com.univoice.data_remote.api.ApiKeyStorage.API
import com.univoice.data_remote.api.ApiKeyStorage.AUTH
import com.univoice.data_remote.api.ApiKeyStorage.CHECK_EMAIL
import com.univoice.data_remote.api.ApiKeyStorage.DEPARTMENT
import com.univoice.data_remote.api.ApiKeyStorage.SIGNUP
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY
import com.univoice.data_remote.api.ApiKeyStorage.UNIVERSITY_DATA
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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
    ): BaseResponse<Unit>

    @Multipart
    @POST("/$API/$V1/$AUTH/$SIGNUP")
    suspend fun postSignUp(
        @Part("admissionNumber") admissionNumber: RequestBody,
        @Part("name") name: RequestBody,
        @Part("studentNumber") studentNumber: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("universityName") universityName: RequestBody,
        @Part("departmentName") departmentName: RequestBody,
        @Part studentCardImage: MultipartBody.Part,
    ): BaseResponse<Unit>
}