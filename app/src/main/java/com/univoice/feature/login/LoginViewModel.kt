package com.univoice.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.repository.LoginRepository
import com.univoice.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {
    private val _postLoginState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val postLoginState: StateFlow<UiState<String>> = _postLoginState

    fun postLogin(email: String, password: String) = viewModelScope.launch {
        _postLoginState.emit(UiState.Loading)
        loginRepository.postLogin(email, password).fold(
            {
                if (it != null) _postLoginState.emit(UiState.Success(it)) else _postLoginState.emit(
                    UiState.Failure("400")
                )
            },
            { _postLoginState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun getUserAccessToken() = userInfoRepository.getUserAccessToken()

    fun saveUserAccessToken(accessToken: String) {
        viewModelScope.launch {
            userInfoRepository.saveUserAccessToken(accessToken)
        }
    }
}