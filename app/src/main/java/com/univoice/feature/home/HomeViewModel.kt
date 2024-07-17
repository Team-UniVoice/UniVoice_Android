package com.univoice.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _getQuickScanState =
        MutableStateFlow<UiState<List<QuickScanListEntity>>>(UiState.Empty)
    val getQuickScanState: StateFlow<UiState<List<QuickScanListEntity>>> =
        _getQuickScanState

    private val _getNoticeContent = MutableStateFlow<UiState<List<NoticeListEntity>>>(UiState.Empty)
    val getNoticeContent: StateFlow<UiState<List<NoticeListEntity>>> = _getNoticeContent

    init {
        getQuickscan()
        getNoticeContent()
    }

    private fun getQuickscan() = viewModelScope.launch {
        _getQuickScanState.emit(UiState.Loading)
        homeRepository.getNoticeQuickScan().fold(
            {
                if (it != null) _getQuickScanState.emit(UiState.Success(it)) else _getQuickScanState.emit(
                    UiState.Failure("400")
                )
            }, {
                _getQuickScanState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun getNoticeContent() = viewModelScope.launch {
        _getNoticeContent.emit(UiState.Loading)
        homeRepository.getNoticeAll().fold(
            {
                if (it != null) _getNoticeContent.emit(UiState.Success(it)) else _getNoticeContent.emit(
                    UiState.Failure("400")
                )
            },
            { _getNoticeContent.emit(UiState.Failure(it.message.toString())) }
        )
    }
}