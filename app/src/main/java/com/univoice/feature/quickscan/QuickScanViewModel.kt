package com.univoice.feature.quickscan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.domain.repository.QuickScanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuickScanViewModel @Inject constructor(
    private val quickScanRepository: QuickScanRepository
) : ViewModel() {
    private val _postQuickScanList =
        MutableStateFlow<UiState<List<QuickScanListEntity>>>(UiState.Empty)
    val postQuickScanList: StateFlow<UiState<List<QuickScanListEntity>>> = _postQuickScanList

    private val _postQuickScanViewCheck = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postQuickScanViewCheck: StateFlow<UiState<Unit>> = _postQuickScanViewCheck

    fun postQuickScanList(writeAffiliation: String) = viewModelScope.launch {
        _postQuickScanList.emit(UiState.Loading)
        quickScanRepository.postQuickScan(writeAffiliation = writeAffiliation).fold(
            {
                if (it != null) _postQuickScanList.emit(UiState.Success(it)) else _postQuickScanList.emit(
                    UiState.Failure("400")
                )
            },
            { _postQuickScanList.emit(UiState.Failure(it.message.toString())) }
        )

    }

    fun postQuickScanViewCheck(noticeId: Int) = viewModelScope.launch {
        _postQuickScanViewCheck.emit(UiState.Loading)
        quickScanRepository.postQuickScanViewCheck(noticeId).fold(
            {
                if (it != null) _postQuickScanViewCheck.emit(UiState.Success(it)) else _postQuickScanViewCheck.emit(
                    UiState.Failure("400")
                )
            },
            { _postQuickScanViewCheck.emit(UiState.Failure(it.message.toString())) }
        )
    }

//    fun updateBookmark(id: Int, isBookmark: Boolean) {
//        val target = mockQuickScanList.find { it.id == id }
//        target?.isBookmark = isBookmark
//    }
}