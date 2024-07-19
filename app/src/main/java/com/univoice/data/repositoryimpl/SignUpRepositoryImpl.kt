package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.SignUpDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.data.repositoryimpl.util.extractErrorMessage
import com.univoice.domain.repository.SignUpRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: SignUpDataSource
) : SignUpRepository {
    override suspend fun postUniversityNames(): Result<List<String>> {
        return runCatching {
            signUpDataSource.postUniversityNames().data ?: emptyList()
        }
    }

    override suspend fun postDepartments(requestDepartmentDto: RequestDepartmentDto): Result<BaseResponse<List<String>>> {
        return runCatching {
            signUpDataSource.postDepartments(requestDepartmentDto)
        }
    }

    override suspend fun postEmail(requestCheckEmailDto: RequestCheckEmailDto): Result<BaseResponse<Unit>> {
        return runCatching {
            signUpDataSource.postEmail(requestCheckEmailDto)
        }
    }

    override suspend fun postSignUp(
        admissionNumber: String,
        name: String,
        studentNumber: String,
        email: String,
        password: String,
        universityName: String,
        departmentName: String,
        studentCardImage: File
    ): Result<Any> {
        return runCatching {

            val admissionNumberBody =
                admissionNumber.toRequestBody("text/plain".toMediaTypeOrNull())
            val nameBody = name.toRequestBody("text/plain".toMediaTypeOrNull())
            val studentNumberBody = studentNumber.toRequestBody("text/plain".toMediaTypeOrNull())
            val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
            val passwordBody = password.toRequestBody("text/plain".toMediaTypeOrNull())
            val universityNameBody = universityName.toRequestBody("text/plain".toMediaTypeOrNull())
            val departmentNameBody = departmentName.toRequestBody("text/plain".toMediaTypeOrNull())

            // 파일 데이터를 읽어와서 별도의 파트로 추가
            val filePart = studentCardImage.let {
                val requestBody = it.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("studentCardImage", it.name, requestBody)
            }

            val response = signUpDataSource.postSignUp(
                admissionNumberBody,
                nameBody,
                studentNumberBody,
                emailBody,
                passwordBody,
                universityNameBody,
                departmentNameBody,
                filePart
            )

            if (response.status in 200..299) {
                response.data ?: ""
            } else {
                response.message ?: ""
            }

        }.onFailure { throwable ->
            return when (throwable) {
                is HttpException -> Result.failure(IOException(throwable.extractErrorMessage()))
                else -> Result.failure(throwable)
            }
        }
    }
}
