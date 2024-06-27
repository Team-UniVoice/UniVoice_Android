package com.univoice.feature.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.UserEntity
import com.univoice.domain.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository
) : ViewModel() {
    private val _getExample = MutableStateFlow<UiState<List<UserEntity>>>(UiState.Empty)
    val getExample: StateFlow<UiState<List<UserEntity>>> = _getExample

    fun getExampleRecyclerview(page: Int) = viewModelScope.launch {
        exampleRepository.getExample(page).collectLatest {
            _getExample.value = UiState.Success(it)
        }
        _getExample.value = UiState.Loading
    }
}