package com.univoice.feature.noticeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.NoticeDetailEntity
import com.univoice.domain.repository.NoticeDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _postNoticeLike = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postNoticeLike: StateFlow<UiState<Unit>> = _postNoticeLike

    private val _postNoticeDelLike = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postNoticeDelLike: StateFlow<UiState<Unit>> = _postNoticeDelLike

    private val _postNoticeDetailViewCount =
        MutableSharedFlow<UiState<Unit>>()
    val postNoticeDetailViewCount: SharedFlow<UiState<Unit>> = _postNoticeDetailViewCount

    fun getNoticeDetail(noticeId: Int) = viewModelScope.launch {
        _getNoticeDetail.emit(UiState.Loading)
        noticeDetailRepository.getNoticeDetail(noticeId).fold(
            {
                _getNoticeDetail.emit(UiState.Success(it))
            },
            { _getNoticeDetail.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun postNoticeLike(noticeId: Int) = viewModelScope.launch {
        _postNoticeLike.emit(UiState.Loading)
        noticeDetailRepository.postNoticeLike(noticeId).fold(
            {
                _postNoticeLike.emit(UiState.Success(it))
            },
            { _postNoticeLike.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun postNoticeDelLike(noticeId: Int) = viewModelScope.launch {
        _postNoticeDelLike.emit(UiState.Loading)
        noticeDetailRepository.postNoticeDisLike(noticeId).fold(
            {
                _postNoticeDelLike.emit(UiState.Success(it))
            },
            { _postNoticeDelLike.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun postNoticeDetailViewCount(noticeId: Int) = viewModelScope.launch {
        noticeDetailRepository.postNoticeDetailViewCount(noticeId).fold({}, {})
    }
}