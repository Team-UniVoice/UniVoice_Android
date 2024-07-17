package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DepartmentInputViewModel @Inject constructor(
    private val signupRepository: SignUpRepository
) : ViewModel() {

    private val _departmentListState = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val departmentListState: StateFlow<UiState<List<String>>> = _departmentListState

    fun postDepartments(universityName: String) {
        _departmentListState.value = UiState.Loading
        viewModelScope.launch {
            val request = RequestDepartmentDto(universityName)
            signupRepository.postDepartments(request).onSuccess { response ->
                _departmentListState.value = UiState.Success(response.data ?: emptyList())
            }.onFailure {
                _departmentListState.value = UiState.Failure(it.message ?: "")
            }
        }
    }
}
