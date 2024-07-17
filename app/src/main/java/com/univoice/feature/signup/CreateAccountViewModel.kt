package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.data.dto.request.RequestCheckEmailDto
import com.univoice.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _emailCheckState = MutableSharedFlow<UiState<Unit>>(replay = 0)
    val emailCheckState: SharedFlow<UiState<Unit>> = _emailCheckState

    fun checkEmail(email: String) {
        viewModelScope.launch {
            _emailCheckState.emit(UiState.Loading)
            signUpRepository.postEmail(RequestCheckEmailDto(email)).onSuccess {
                _emailCheckState.emit(UiState.Success(Unit))
            }.onFailure {
                _emailCheckState.emit(UiState.Failure(it.message ?: ""))
            }
        }
    }
}
