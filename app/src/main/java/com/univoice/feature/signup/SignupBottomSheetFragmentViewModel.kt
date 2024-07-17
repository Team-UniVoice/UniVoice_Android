package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class SignupBottomSheetFragmentViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _signupState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val signupState: StateFlow<UiState<Unit>> = _signupState
    fun signUp(
        admissionNumber: RequestBody,
        name: RequestBody,
        studentNumber: RequestBody,
        email: RequestBody,
        password: RequestBody,
        universityName: RequestBody,
        departmentName: RequestBody,
        studentCardImage: MultipartBody.Part?
    ) {
        _signupState.value = UiState.Loading
        viewModelScope.launch {
            signUpRepository.postSignUp(
                admissionNumber,
                name,
                studentNumber,
                email,
                password,
                universityName,
                departmentName,
                studentCardImage
            ).fold(
                onSuccess = {
                    _signupState.value = UiState.Success(Unit)
                },
                onFailure = {
                    println("실패: ${it.message}")
                    _signupState.value = UiState.Failure(it.message ?: "Unknown Error")
                }
            )
        }
    }
}
