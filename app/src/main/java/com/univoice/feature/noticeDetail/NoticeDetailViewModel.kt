package com.univoice.feature.noticeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.NoticeDetailEntity
import com.univoice.domain.repository.NoticeDetailRepository
import com.univoice.feature.util.throttleFirst
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

    private val _postNoticeCancelLike = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postNoticeCancelLike: StateFlow<UiState<Unit>> = _postNoticeCancelLike

    private val _postNoticeDetailViewCount =
        MutableSharedFlow<UiState<Unit>>()
    val postNoticeDetailViewCount: SharedFlow<UiState<Unit>> = _postNoticeDetailViewCount

    private val _postNoticeDetailSave = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postNoticeDetailSave: StateFlow<UiState<Unit>> = _postNoticeDetailSave

    private val _postNoticeDetailCancel = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postNoticeDetailCancel: StateFlow<UiState<Unit>> = _postNoticeDetailCancel

    fun getNoticeDetail(noticeId: Int) = viewModelScope.launch {
        _getNoticeDetail.emit(UiState.Loading)
        noticeDetailRepository.getNoticeDetail(noticeId).fold(
            {
                _getNoticeDetail.emit(UiState.Success(it))
            },
            { _getNoticeDetail.emit(UiState.Failure(it.message.toString())) }
        )
    }

    private fun postNoticeLike(noticeId: Int) = viewModelScope.launch {
        _postNoticeLike.emit(UiState.Loading)
        noticeDetailRepository.postNoticeLike(noticeId).fold(
            {}, {}
        )
    }

    private fun postNoticeCancelLike(noticeId: Int) = viewModelScope.launch {
        _postNoticeCancelLike.emit(UiState.Loading)
        noticeDetailRepository.postNoticeCancelLike(noticeId).fold(
            {}, {}
        )
    }

    fun postNoticeDetailViewCount(noticeId: Int) = viewModelScope.launch {
        noticeDetailRepository.postNoticeDetailViewCount(noticeId).fold({}, {})
    }

    private fun postNoticeDetailSave(noticeId: Int) = viewModelScope.launch {
        _postNoticeDetailSave.emit(UiState.Loading)
        noticeDetailRepository.postNoticeDetailSave(noticeId).fold({}, {})
    }

    private fun postNoticeDetailCancel(noticeId: Int) = viewModelScope.launch {
        _postNoticeDetailCancel.emit(UiState.Loading)
        noticeDetailRepository.postNoticeDetailCancel(noticeId).fold({}, {})
    }

    fun onLikeBtnClick(noticeId: Int){
        viewModelScope.launch {
           _postNoticeLike.throttleFirst(1000L).collect {
               postNoticeLike(noticeId)
           }
        }
    }

    fun onDisLikeBtnClick(noticeId: Int) {
        viewModelScope.launch {
            _postNoticeCancelLike.throttleFirst(1000L).collect {
                postNoticeCancelLike(noticeId)
            }
        }
    }

    fun onBookmarkBtnClick(noticeId: Int) {
        viewModelScope.launch {
            _postNoticeDetailSave.throttleFirst(1000L).collect {
                postNoticeDetailSave(noticeId)
            }
        }
    }

    fun onBookmarkCancelBtnClick(noticeId: Int) {
        viewModelScope.launch {
            _postNoticeDetailCancel.throttleFirst(1000L).collect {
                postNoticeDetailCancel(noticeId)
            }
        }
    }
}