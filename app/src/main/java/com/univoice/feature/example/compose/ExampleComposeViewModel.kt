package com.univoice.feature.example.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleComposeViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository
) : ViewModel() {
    private val _getExampleState: MutableStateFlow<ExampleState> =
        MutableStateFlow(ExampleState(UiState.Empty))
    val getExampleState: StateFlow<ExampleState>
        get() = _getExampleState.asStateFlow()

    private val _getExampleSideEffect: MutableSharedFlow<ExampleSideEffect> = MutableSharedFlow()
    val getExampleSideEffect: SharedFlow<ExampleSideEffect> get() = _getExampleSideEffect.asSharedFlow()

    fun getExampleRecyclerview(page: Int) = viewModelScope.launch {
        exampleRepository.getExample(page).fold(
            {
                _getExampleState.value =
                    _getExampleState.value.copy(exampleState = UiState.Success(it))
                _getExampleSideEffect.emit(ExampleSideEffect.ExampleShowToast("성공"))
            },
            {
                _getExampleState.value =
                    _getExampleState.value.copy(exampleState = UiState.Failure(it.message.toString()))
                _getExampleSideEffect.emit(ExampleSideEffect.ExampleShowToast("실패 : ${it.message}"))
            }
        )
    }
}