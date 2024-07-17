package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolInputViewModel @Inject constructor(
    private val universityRepository: UniversityRepository
) : ViewModel() {

    private val _schoolListState = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val schoolListState: StateFlow<UiState<List<String>>> = _schoolListState

    init {
        postUniversityNames()
    }

    private fun postUniversityNames() {
        _schoolListState.value = UiState.Loading
        viewModelScope.launch {
            universityRepository.postUniversityNames().onSuccess { response ->
                _schoolListState.value = UiState.Success(response)
            }.onFailure {
                _schoolListState.value = UiState.Failure(it.message ?: "Unknown error")
            }
        }
    }
}
