package com.univoice.feature.example.xml

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.UserEntity
import com.univoice.domain.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository
) : ViewModel() {
    private val _getExampleState = MutableStateFlow<UiState<List<UserEntity>>>(UiState.Empty)
    val getExampleState: StateFlow<UiState<List<UserEntity>>> = _getExampleState

    fun getExampleRecyclerview(page: Int) = viewModelScope.launch {
        _getExampleState.emit(UiState.Loading)
        exampleRepository.getExample(page).fold(
            { _getExampleState.emit(UiState.Success(it)) },
            { _getExampleState.emit(UiState.Failure(it.message.toString())) }
        )
    }
}