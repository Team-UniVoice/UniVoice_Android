package com.univoice.feature.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {
    private val _getStorageState = MutableStateFlow<UiState<List<NoticeListEntity>>>(UiState.Empty)
    val getStorageState: StateFlow<UiState<List<NoticeListEntity>>> = _getStorageState

    init {
        getStorageList()
    }

    private fun getStorageList() = viewModelScope.launch {
        _getStorageState.emit(UiState.Loading)
        storageRepository.getSaves().fold(
            {
                if (it != null) _getStorageState.emit(UiState.Success(it)) else _getStorageState.emit(
                    UiState.Failure("400")
                )
            },
            { _getStorageState.emit(UiState.Failure(it.message.toString())) }
        )

    }
}