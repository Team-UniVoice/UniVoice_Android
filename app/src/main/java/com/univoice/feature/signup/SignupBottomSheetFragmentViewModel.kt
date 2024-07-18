package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignupBottomSheetFragmentViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _postSignupState = MutableSharedFlow<UiState<Any>>()
    val postSignupState: SharedFlow<UiState<Any>> get() = _postSignupState.asSharedFlow()

    fun postSignUp(
        admissionNumber: String,
        name: String,
        studentNumber: String,
        email: String,
        password: String,
        universityName: String,
        departmentName: String,
        studentCardImage: File
    ) = viewModelScope.launch {
        _postSignupState.emit(UiState.Loading)
        signUpRepository.postSignUp(
            admissionNumber,
            name,
            studentNumber,
            email,
            password,
            universityName,
            departmentName,
            studentCardImage
        ).onSuccess { _postSignupState.emit(UiState.Success(it)) }
            .onFailure { _postSignupState.emit(UiState.Failure(it.message.toString())) }
    }
}
