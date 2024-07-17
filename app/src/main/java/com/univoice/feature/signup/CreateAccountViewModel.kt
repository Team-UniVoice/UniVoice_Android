package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _emailCheckState = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val emailCheckState: StateFlow<UiState<List<String>>> = _emailCheckState

    fun checkEmail(email: String) {
        _emailCheckState.value = UiState.Loading
        viewModelScope.launch {
            signUpRepository.postEmail(RequestCheckEmailDto(email)).onSuccess { response ->
                _emailCheckState.value = UiState.Success(response.data ?: emptyList())
            }.onFailure {
                _emailCheckState.value = UiState.Failure(it.message ?: "")
            }
        }
    }
}
