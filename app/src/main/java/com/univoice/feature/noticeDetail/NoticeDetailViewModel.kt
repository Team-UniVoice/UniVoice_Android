package com.univoice.feature.noticeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.NoticeDetailEntity
import com.univoice.domain.repository.NoticeDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeDetailViewModel @Inject constructor(
    private val noticeDetailRepository: NoticeDetailRepository
) : ViewModel() {

    private val _getNoticeDetail =
        MutableStateFlow<UiState<NoticeDetailEntity>>(UiState.Empty)
    val getNoticeDetail: StateFlow<UiState<NoticeDetailEntity>> = _getNoticeDetail

    fun getNoticeDetail(noticeId: Int) = viewModelScope.launch {
        _getNoticeDetail.emit(UiState.Loading)
        noticeDetailRepository.getNoticeDetail(noticeId).fold(
            {
                _getNoticeDetail.emit(UiState.Success(it))
            },
            { _getNoticeDetail.emit(UiState.Failure(it.message.toString())) }
        )
    }
}