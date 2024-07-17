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

    private val _getNoticeAllState =
        MutableStateFlow<UiState<List<NoticeListEntity>>>(UiState.Empty)
    val getNoticeAllState: StateFlow<UiState<List<NoticeListEntity>>> = _getNoticeAllState

    private val _getNoticeUniversityState =
        MutableStateFlow<UiState<List<NoticeListEntity>>>(UiState.Empty)
    val getNoticeUniversityState: StateFlow<UiState<List<NoticeListEntity>>> =
        _getNoticeUniversityState

    private val _getNoticeCollegeState =
        MutableStateFlow<UiState<List<NoticeListEntity>>>(UiState.Empty)
    val getNoticeCollegeState: StateFlow<UiState<List<NoticeListEntity>>> =
        _getNoticeCollegeState

    private val _getNoticeDepartmentState =
        MutableStateFlow<UiState<List<NoticeListEntity>>>(UiState.Empty)
    val getNoticeDepartmentState: StateFlow<UiState<List<NoticeListEntity>>> =
        _getNoticeDepartmentState

    init {
        getQuickscan()
        getNoticeAll()
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

    fun getNoticeAll() = viewModelScope.launch {
        _getNoticeAllState.emit(UiState.Loading)
        homeRepository.getNoticeAll().fold(
            {
                if (it != null) _getNoticeAllState.emit(UiState.Success(it)) else _getNoticeAllState.emit(
                    UiState.Failure("400")
                )
            },
            { _getNoticeAllState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun getNoticeUniversity() = viewModelScope.launch {
        _getNoticeUniversityState.emit(UiState.Loading)
        homeRepository.getNoticeUniversity().fold(
            {
                if (it != null) _getNoticeUniversityState.emit(UiState.Success(it)) else _getNoticeUniversityState.emit(
                    UiState.Failure("400")
                )
            },
            { _getNoticeUniversityState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun getNoticeCollege() = viewModelScope.launch {
        _getNoticeCollegeState.emit(UiState.Loading)
        homeRepository.getNoticeCollege().fold(
            {
                if (it != null) _getNoticeCollegeState.emit(UiState.Success(it)) else _getNoticeCollegeState.emit(
                    UiState.Failure("400")
                )
            },
            { _getNoticeCollegeState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun getNoticeDepartment() = viewModelScope.launch {
        _getNoticeDepartmentState.emit(UiState.Loading)
        homeRepository.getNoticeDepartment().fold(
            {
                if (it != null) _getNoticeDepartmentState.emit(UiState.Success(it)) else _getNoticeDepartmentState.emit(
                    UiState.Failure("400")
                )
            },
            { _getNoticeDepartmentState.emit(UiState.Failure(it.message.toString())) }
        )
    }
}